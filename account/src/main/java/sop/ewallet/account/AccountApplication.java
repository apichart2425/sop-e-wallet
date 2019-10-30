package sop.ewallet.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sop.ewallet.account.model.Account;
import sop.ewallet.account.model.Wallet;
import sop.ewallet.account.response.RequestAction;


@SpringBootApplication
public class AccountApplication {

	//	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
