package sop.ewallet.account.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sop.ewallet.account.model.Account;
import sop.ewallet.account.model.UserRequest;
import sop.ewallet.account.model.Wallet;
import sop.ewallet.account.repositories.AccountRepositories;
import sop.ewallet.account.repositories.ActionNotMatchingException;
import sop.ewallet.account.repositories.ResourceNotFoundException;
import sop.ewallet.account.response.ApiResponse;
import sop.ewallet.account.security.CurrentUser;
import sop.ewallet.account.security.UserDetail;
import sop.ewallet.account.security.UserPrincipal;

@RestController
@RequestMapping("/")
public class AccountController {
    @Value("${service.transaction}")
    private String transactionUrl;

    @Autowired
    private AccountRepositories accountRepositories;

    private  RestTemplate restTemplate = new RestTemplate();

    private void saveLog(UserRequest ur){
        System.out.println("Save Transaction");
        String url = transactionUrl + "/saveLog";
        this.restTemplate.postForObject(url, ur,UserRequest.class);
    }

    private UserRequest sentRequest(UserRequest ur, String action){
        ur.getAccount_source().setWallet(accountRepositories.getOne(ur.getAccount_source().getId()).getWallet());
        System.out.println(ur.getAccount_source().getId());
        System.out.println(ur.getAccount_source().getWallet());
        String url;
        switch (ur.getAction()){
            case "deposit":
                url = transactionUrl + "/deposit";
                break;
            case "withdraw":
                url = transactionUrl + "/withdraw";
                break;
            case "transfer":
                ur.getAccount_destination().setWallet(accountRepositories.getOne(ur.getAccount_destination().getId()).getWallet());
                url = transactionUrl + "/transfer";
                break;
            default:
                url = transactionUrl + "/transaction";
        }

        return this.restTemplate.postForObject(url, ur,UserRequest.class);
    }

    @GetMapping("/")
    public String index() {
        return "Account Service";
    }

    @GetMapping("/me")
    public ResponseEntity<Long> me(@CurrentUser UserPrincipal currentUser) {
        System.out.println(transactionUrl);
        return ResponseEntity.ok(currentUser.getId());
    }

    @GetMapping("/account")
    public Optional<Account> getAccount(@CurrentUser UserPrincipal currentUser) {
        return accountRepositories.findAccountByUserId(currentUser.getId());
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public Optional<Account> info(@PathVariable int id){
        return accountRepositories.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createDefault(@CurrentUser UserPrincipal currentUser){
        Optional<Account> exist = accountRepositories.findAccountByUserId(currentUser.getId());
        if (exist.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(false, "This user has already created account"));
        }

        Account newAccount = accountRepositories.save(new Account(currentUser.getId()));

        return ResponseEntity.ok(new ApiResponse(true, "Created Account Successfully"));
    }

    @PostMapping(value = "/deposit")
    public Account deposit(@RequestBody UserRequest ur) throws ActionNotMatchingException {
        if(!ur.getAction().equals("deposit")){
            ur.setStatus(false);
            throw new ActionNotMatchingException();
        }
        UserRequest nur = sentRequest(ur, "deposit");
        if (nur.getStatus() && nur.getAction().equals("deposit")){
            induct(nur.getAccount_source().getWallet(), ur.getCurrency_destination(), ur.getAccount_source().getId());
            saveLog(nur);
        }
        return nur.getAccount_source();
    }

    @PostMapping(value = "/withdraw")
    public Account withdraw(@RequestBody UserRequest ur) throws ActionNotMatchingException {
        if(!ur.getAction().equals("withdraw")){
            ur.setStatus(false);
            throw new ActionNotMatchingException();
        }
        UserRequest nur = sentRequest(ur, "withdraw");
        if (nur.getStatus() && nur.getAction().equals("withdraw")) {
            deduct(nur.getAccount_source().getWallet(), ur.getCurrency_source(), ur.getAccount_source().getId());
            saveLog(nur);
        }
        return nur.getAccount_source();
    }

    @PostMapping (value = "/transfer")
    public Account[] transfer(@RequestBody UserRequest ur) throws ActionNotMatchingException {
        if(!ur.getAction().equals("transfer")){
            ur.setStatus(false);
            throw new ActionNotMatchingException();
        }
        UserRequest nur = sentRequest(ur,"transfer");
        if (nur.getStatus() && nur.getAction().equals("transfer")) {
            deduct(nur.getAccount_source().getWallet(), nur.getCurrency_source(), nur.getAccount_source().getId());
            induct(nur.getAccount_destination().getWallet(), nur.getCurrency_destination(), nur.getAccount_destination().getId());
            saveLog(nur);
        }
        return new Account[]{nur.getAccount_source(), nur.getAccount_destination()};
    }

    private void induct(Wallet wd, String currency, int id){
        accountRepositories.findById(id)
                .map(account -> {
                    switch (currency.toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(wd.getUSD());
                            break;
                        case "cny":
                            account.getWallet().setCNY(wd.getCNY());
                            break;
                        case "thb":
                            account.getWallet().setTHB(wd.getTHB());
                            break;
                        case "eur":
                            account.getWallet().setEUR(wd.getEUR());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(wd.getJPY());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(wd.getSGD());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    }

    private void deduct(Wallet wd, String currency, int id) {
        accountRepositories.findById(id)
                .map(account -> {
                    switch (currency.toLowerCase()){
                        case "usd":
                            account.getWallet().setUSD(wd.getUSD());
                            break;
                        case "cny":
                            account.getWallet().setCNY(wd.getCNY());
                            break;
                        case "thb":
                            account.getWallet().setTHB(wd.getTHB());
                            break;
                        case "eur":
                            account.getWallet().setEUR(wd.getEUR());
                            break;
                        case "jpy":
                            account.getWallet().setJPY(wd.getJPY());
                            break;
                        case "sgd":
                            account.getWallet().setSGD(wd.getSGD());
                            break;
                        default:
                            throw new ResourceNotFoundException("Wrong currency");
                    }
                    return accountRepositories.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    }

}

