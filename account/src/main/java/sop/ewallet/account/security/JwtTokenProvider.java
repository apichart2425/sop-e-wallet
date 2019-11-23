package sop.ewallet.account.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  public String objectToJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();

    String result = "";
    try {
      result = mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return result;
  }

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    byte[] signingKey = jwtSecret.getBytes();

    UserDetail userDetail = new UserDetail(userPrincipal.getId(), userPrincipal.getName(),
        userPrincipal.getUsername(), userPrincipal.getEmail());

    return Jwts.builder()
        .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
        .setSubject(objectToJson(userDetail))
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .compact();
  }

  public Long getUserIdFromJWT(String token) {
    byte[] signingKey = jwtSecret.getBytes();

    Claims claims = Jwts.parser()
        .setSigningKey(signingKey)
        .parseClaimsJws(token)
        .getBody();

    UserDetail userDetail = new UserDetail();
    try {
      userDetail = new ObjectMapper().readValue(claims.getSubject(), UserDetail.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return userDetail.getId();
  }

  public boolean validateToken(String authToken) {
    try {
      byte[] signingKey = jwtSecret.getBytes();

      Jwts.parser().setSigningKey(signingKey).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    } catch (SignatureException ex) {
      logger.error("JWT signature does not match.");
    }
    return false;
  }
}