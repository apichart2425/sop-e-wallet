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
    public Optional<Account> info(@PathVariable int id){
        return accountRepositories.findById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Account createDefault(){
        return accountRepositories.save(new Account());
    }
//
    @PostMapping(value = "/deposit")
    public Account deposit(@RequestBody UserRequest ur){
//        RequestAction re = new RequestAction("DP", ur.getBalance(), ur.getCurrency_origin(), accountRepositories.getOne((long) ur.getId()));
        induct(ur, ur.getAccount_source().getId());
        return accountRepositories.getOne(ur.getAccount_source().getId());
    }
//
    @PostMapping(value = "/withdraw")
    public Account withdraw(@RequestBody UserRequest ur) {
        deduct(ur);
        return accountRepositories.getOne(ur.getAccount_source().getId());
    }
//
    @PostMapping (value = "/transfer")
    public Optional[] transfer(@RequestBody UserRequest ur){
        if(true){
            deduct(ur);
            induct(ur, ur.getAccount_destination().getId());
        }
        return new Optional[]{accountRepositories.findById(ur.getAccount_source().getId()), accountRepositories.findById(ur.getAccount_destination().getId())};
    }
//
////    @GetMapping(value = "/query/{id}")
////    public double test(@PathVariable int id){
////        return accountRepositories.cruMoney(id);
////    }
//
    private void induct(UserRequest ur, int id){
        accountRepositories.findById(id)
                .map(account -> {
                    switch (ur.getCurrency_origin().toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(account.getWallet().getUSD() + ur.getBalance());
                            break;
                        case "cny":
                            account.getWallet().setCNY(account.getWallet().getCNY() + ur.getBalance());
                            break;
                        case "thb":
                            account.getWallet().setTHB(account.getWallet().getTHB() + ur.getBalance());
                            break;
                        case "eur":
                            account.getWallet().setEUR(account.getWallet().getEUR() + ur.getBalance());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(account.getWallet().getJPY() + ur.getBalance());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(account.getWallet().getSGD() + ur.getBalance());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getAccount_source().getId()));
    }

    private void deduct(UserRequest ur) {
        accountRepositories.findById(ur.getAccount_source().getId())
                .map(account -> {
                    switch (ur.getCurrency_origin().toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(account.getWallet().getUSD() - ur.getBalance());
                            break;
                        case "cny":
                            account.getWallet().setCNY(account.getWallet().getCNY() - ur.getBalance());
                            break;
                        case "thb":
                            account.getWallet().setTHB(account.getWallet().getTHB() - ur.getBalance());
                            break;
                        case "eur":
                            account.getWallet().setEUR(account.getWallet().getEUR() - ur.getBalance());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(account.getWallet().getJPY() - ur.getBalance());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(account.getWallet().getSGD() - ur.getBalance());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getAccount_source().getWallet()));
    }



}

