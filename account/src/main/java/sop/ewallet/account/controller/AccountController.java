package sop.ewallet.account.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sop.ewallet.account.model.Account;
import sop.ewallet.account.model.UserRequest;
import sop.ewallet.account.repositories.AccountRepositories;
import sop.ewallet.account.repositories.ResourceNotFoundException;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
//    Repositories
    @Autowired
    private AccountRepositories accountRepositories;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String hello() {
        return "Welcome to You Wallet";
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public Optional<Account> test(@PathVariable int id){
        return accountRepositories.findById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Account createDefault(){
        return accountRepositories.save(new Account());
    }

    @PostMapping(value = "/deposit")
    public Account deposit(@RequestBody UserRequest ur){
//        RequestAction re = new RequestAction("DP", ur.getBalance(), ur.getCurrency_origin(), accountRepositories.getOne((long) ur.getId()));
        return accountRepositories.findById(ur.getId_origin())
                .map(account -> {
                    switch (ur.getCurrency_origin().toLowerCase()){
                        case "usd":
                            account.setUSD(account.getUSD() + ur.getBalance());
                            break;
                        case "cny":
                            account.setCNY(account.getCNY() + ur.getBalance());
                            break;
                        case "thb":
                            account.setTHB(account.getTHB() + ur.getBalance());
                            break;
                        case "eur":
                            account.setEUR(account.getEUR() + ur.getBalance());
                            break;
                        case "jpy":
                            account.setJPY(account.getJPY() + ur.getBalance());
                            break;
                        case "sgd":
                            account.setSGD(account.getSGD() + ur.getBalance());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getId_origin()));
    }

    @PostMapping(value = "withdraw")
    public Account widthdraw(@RequestBody UserRequest ur) {
        return accountRepositories.findById(ur.getId_origin())
                .map(account -> {
                    switch (ur.getCurrency_origin().toLowerCase()){
                        case "usd":
                            account.setUSD(account.getUSD() - ur.getBalance());
                            break;
                        case "cny":
                            account.setCNY(account.getCNY() - ur.getBalance());
                            break;
                        case "thb":
                            account.setTHB(account.getTHB() - ur.getBalance());
                            break;
                        case "eur":
                            account.setEUR(account.getEUR() - ur.getBalance());
                            break;
                        case "jpy":
                            account.setJPY(account.getJPY() - ur.getBalance());
                            break;
                        case "sgd":
                            account.setSGD(account.getSGD() - ur.getBalance());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getId_origin()));
    }

    @PostMapping (value = "/transfer")
    public Optional[] transfer(@RequestBody UserRequest ur){
        return new Optional[]{accountRepositories.findById(ur.getId_origin()), accountRepositories.findById(ur.getId_dest())};
    }


}
