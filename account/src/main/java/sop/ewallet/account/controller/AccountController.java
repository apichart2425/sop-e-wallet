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
        induct(ur);
        return accountRepositories.getOne(ur.getAccount_source().getAc_id());
    }
//
//    @PostMapping(value = "/withdraw")
//    public Account withdraw(@RequestBody UserRequest ur) {
//        if(deductible(ur)){
//            deduct(ur.getId_origin(), ur.getCurrency_origin(), ur.getBalance());
//        }
//        return accountRepositories.getOne(ur.getId_origin());
//    }
//
//    @PostMapping (value = "/transfer")
//    public Optional[] transfer(@RequestBody UserRequest ur){
//        if(deductible(ur)){
//            deduct(ur.getId_origin(), ur.getCurrency_origin(), ur.getBalance());
//            induct(ur.getId_dest(), ur.getCurrency_dest(), ur.getBalance());
//        }
//        return new Optional[]{accountRepositories.findById(ur.getId_origin()), accountRepositories.findById(ur.getId_dest())};
//    }
//
////    @GetMapping(value = "/query/{id}")
////    public double test(@PathVariable int id){
////        return accountRepositories.cruMoney(id);
////    }
//
    private void induct(UserRequest ur){
        accountRepositories.findById(ur.getAccount_source().getAc_id())
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
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getAccount_source().getAc_id()));
    }

//    private void deduct(int id, String currency, double balance) {
//        accountRepositories.findById(id)
//                .map(account -> {
//                    switch (currency.toLowerCase()){
//                        case "usd":
//                            account.setUSD(account.getUSD() - balance);
//                            break;
//                        case "cny":
//                            account.setCNY(account.getCNY() - balance);
//                            break;
//                        case "thb":
//                            account.setTHB(account.getTHB() - balance);
//                            break;
//                        case "eur":
//                            account.setEUR(account.getEUR() - balance);
//                            break;
//                        case "jpy":
//                            account.setJPY(account.getJPY() - balance);
//                            break;
//                        case "sgd":
//                            account.setSGD(account.getSGD() - balance);
//                            break;
//                        default:
//                            throw new ResourceNotFoundException("Wrong currency");
//                    }
//                    return accountRepositories.save(account);
//                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
//    }
//
//
//    private boolean deductible(UserRequest ur){
//        AtomicBoolean state = new AtomicBoolean(false);
//        accountRepositories.findById(ur.getId_origin())
//                .map(account -> {
//                    switch (ur.getCurrency_origin().toLowerCase()){
//                        case "usd":
//                            state.set(account.getUSD() > ur.getBalance());
//                            break;
//                        case "cny":
//                            state.set(account.getCNY() > ur.getBalance());
//                            break;
//                        case "thb":
//                            state.set(account.getTHB() > ur.getBalance());
//                            break;
//                        case "eur":
//                            state.set(account.getEUR() > ur.getBalance());
//                            break;
//                        case "jpy":
//                            state.set(account.getJPY() > ur.getBalance());
//                            break;
//                        case "sgd":
//                            state.set(account.getJPY() > ur.getBalance());
//                            break;
//                        default:
//                            throw new ResourceNotFoundException("Wrong currency");
//                    }
//                    return accountRepositories.save(account);
//                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getId_origin()));
//        if(state.get()) {
//            return true;
//        }else{
//            return false;
//        }
//    }
}

