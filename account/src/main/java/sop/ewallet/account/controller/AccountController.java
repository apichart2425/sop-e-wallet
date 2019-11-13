package sop.ewallet.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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

    private  RestTemplate restTemplate = new RestTemplate();
//    public AccountController(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    private UserRequest sentRequest(UserRequest ur){
        ur.getAccount_source().setWallet(accountRepositories.getOne(ur.getAccount_source().getId()).getWallet());
        System.out.println(ur.getAccount_source().getId());
        System.out.println(ur.getAccount_source().getWallet());
        String url;
        switch (ur.getAction()){
            case "deposit":
                url = "http://localhost:9634/service/transaction/deposit";
                break;
            case "withdraw":
                url = "http://localhost:9634/service/transaction/withdraw";
                break;
            case "transfer":
//                ur.getAccount_destination().setWallet(accountRepositories.getOne(ur.getAccount_destination().getId()).getWallet());
                url = "http://localhost:9634/service/transaction/transfer";
                break;
            default:
                url = "http://localhost:9634/service/transaction";
        }

        UserRequest response = this.restTemplate.postForObject(url, ur,UserRequest.class);
//        System.out.println(response);
        return response;
    }

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
        UserRequest nur = sentRequest(ur);
        induct(nur, nur.getAccount_source().getId());
        return nur.getAccount_source();
    }
//
    @PostMapping(value = "/withdraw")
    public Account withdraw(@RequestBody UserRequest ur) {
        UserRequest nur = sentRequest(ur);
        deduct(nur);
        return nur.getAccount_source();
    }
//
    @PostMapping (value = "/transfer")
    public Optional[] transfer(@RequestBody UserRequest ur){
            deduct(ur);
            induct(ur, ur.getAccount_destination().getId());
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
                    switch (ur.getCurrency_destination().toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(ur.getAccount_source().getWallet().getUSD());
                            break;
                        case "cny":
                            account.getWallet().setCNY(ur.getAccount_source().getWallet().getCNY());
                            break;
                        case "thb":
                            account.getWallet().setTHB(ur.getAccount_source().getWallet().getTHB());
                            break;
                        case "eur":
                            account.getWallet().setEUR(ur.getAccount_source().getWallet().getEUR());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(ur.getAccount_source().getWallet().getJPY());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(ur.getAccount_source().getWallet().getSGD());
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
                    switch (ur.getCurrency_source().toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(ur.getAccount_source().getWallet().getUSD());
                            break;
                        case "cny":
                            account.getWallet().setCNY(ur.getAccount_source().getWallet().getCNY());
                            break;
                        case "thb":
                            account.getWallet().setTHB(ur.getAccount_source().getWallet().getTHB());
                            break;
                        case "eur":
                            account.getWallet().setEUR(ur.getAccount_source().getWallet().getEUR());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(ur.getAccount_source().getWallet().getJPY());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(ur.getAccount_source().getWallet().getSGD());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + ur.getAccount_source().getWallet()));
    }



}

