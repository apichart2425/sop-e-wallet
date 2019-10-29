package sop.ewallet.authentication.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
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

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    var signingKey = jwtSecret.getBytes();

    return Jwts.builder()
        .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
        .setSubject(Long.toString(userPrincipal.getId()))
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .compact();
  }

  public Long getUserIdFromJWT(String token) {
    var signingKey = jwtSecret.getBytes();

    Claims claims = Jwts.parser()
        .setSigningKey(signingKey)
        .parseClaimsJws(token)
        .getBody();

    return Long.parseLong(claims.getSubject());
  }

  public boolean validateToken(String authToken) {
    try {
      var signingKey = jwtSecret.getBytes();

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
    }
    return false;
  }
}