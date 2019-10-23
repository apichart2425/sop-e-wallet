package sop.ewallet.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping
@RestController
public class AccountApplication {
//	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	Account a1 = new Account();
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "Welcome to You Wallet";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<Wallet> createDefault(){
		a1.addDefaultBalance("USD", 2000);
		return new ResponseEntity<Wallet>(a1.getInfo(), HttpStatus.OK);
	}


	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ResponseEntity<Wallet> info(){
		return new ResponseEntity<Wallet>(a1.getInfo(), HttpStatus.OK);
	}


}
