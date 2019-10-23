package sop.ewallet.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static void main(String[] args) {
//		SpringApplication.run(AccountApplication.class, args);
	}


}
