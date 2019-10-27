package sop.ewallet.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RequestMapping
@RestController
public class AccountApplication {
	private Account a1;
	private Account a2;
	//	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "Welcome to You Wallet";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<Wallet> createDefault(){
		a1 = new Account(1);
		return new ResponseEntity<Wallet>(a1.getInfo(), HttpStatus.OK);
	}
	@RequestMapping(value = "/create2", method = RequestMethod.GET)
	public ResponseEntity<Wallet> createDefault2(){
		a2 = new Account(2);
		return new ResponseEntity<Wallet>(a2.getInfo(), HttpStatus.OK);
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public ResponseEntity<Account[]> deposit(){
		a1.deposit(1000);
		a2.deposit(500);
		Account[] acct = {a1, a2};
		return new ResponseEntity<Account[]>(acct, HttpStatus.OK);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ResponseEntity<Wallet> info(){
		return new ResponseEntity<Wallet>(a1.getInfo(), HttpStatus.OK);
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public ResponseEntity<RequestAction> transfer(){
		RequestAction rac = new RequestAction("WD", 1000, "USD", "USD", a1, a2);
		return new ResponseEntity<RequestAction>(rac, HttpStatus.OK);
	}


}
