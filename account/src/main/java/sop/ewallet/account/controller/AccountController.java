package sop.ewallet.account.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sop.ewallet.account.model.Account;
import sop.ewallet.account.model.UserRequest;
import sop.ewallet.account.model.Wallet;
import sop.ewallet.account.response.RequestAction;

import java.util.ArrayList;

@RestController
@RequestMapping("/account")
public class AccountController {
//    just for test
    private int ac_id = 0;
    private ArrayList<Account> bank = new ArrayList<Account>();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String hello() {
        return "Welcome to You Wallet";
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public Account test(@PathVariable int id){

        return bank.get(id-1);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<Wallet> createDefault(){
        ac_id += 1;
        bank.add(new Account(ac_id));
        return new ResponseEntity<Wallet>(bank.get(ac_id - 1).getInfo(), HttpStatus.OK);
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<RequestAction> deposit(@RequestBody UserRequest ur){
        bank.get(ur.getId()-1).deposit(ur.getCurrency_origin(), ur.getBalance());
        RequestAction re = new RequestAction("DP", ur.getBalance(), ur.getCurrency_origin(), bank.get(ur.getId()-1));
        return new ResponseEntity<RequestAction>(re, HttpStatus.OK);
    }



//    @GetMapping(value = "/info/{id}")
//    public ResponseEntity<Wallet> info(){
//        return new ResponseEntity<Wallet>(a1.getInfo(), HttpStatus.OK);
//    }
//
//    @GetMapping (value = "/transfer")
//    public ResponseEntity<RequestAction> transfer(){
//        RequestAction rac = new RequestAction("WD", 1000, "USD", "USD", a1, a2);
//        return new ResponseEntity<RequestAction>(rac, HttpStatus.OK);
//    }


}
