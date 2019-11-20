package sop.ewallet.transactions.controller;

//https://www.javaguides.net/2019/01/springboot-postgresql-jpa-hibernate-crud-restful-api-tutorial.html

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sop.ewallet.transactions.method.TransactionMethod;
import sop.ewallet.transactions.model.*;
import sop.ewallet.transactions.repositories.LogRepository;
import sop.ewallet.transactions.repositories.ResourceNotFoundException;

@RestController
@RequestMapping("/")
public class TransactionsController {
    @Autowired
    private LogRepository logRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private TransactionMethod transactionMethod = new TransactionMethod() ;

    @GetMapping("/log")
    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    @GetMapping("/log/index/{id}")
    public ResponseEntity<Log> getById(@PathVariable(value = "id") long log_id)
            throws ResourceNotFoundException {
        Log log_data = logRepository.findById(log_id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for this id :: " + log_id));
        return ResponseEntity.ok().body(log_data);
    }

    @GetMapping("/log/profile/{id}")
    public List<Log> getProfileById(@PathVariable(value = "id") long log_id){
        return logRepository.findTransectionProfileById(log_id);
    }

    @PostMapping("/withdraw")
    public AccountWallet withdraw(@Valid @RequestBody AccountWallet obj) {
        return transactionMethod.withdraw(obj);
    }

    @PostMapping("/deposit")
    public AccountWallet deposit(@Valid @RequestBody AccountWallet obj) {
        return transactionMethod.deposit(obj);
    }

    @PostMapping("/transfer")
    public AccountWallet transfer(@Valid @RequestBody AccountWallet obj) {
        return transactionMethod.transfer(obj);
    }

    @PostMapping("/saveLog")
    public AccountWallet save(@Valid @RequestBody AccountWallet obj) {

        if(obj.getStatus() && obj.getAction().equals("transfer")){
            Log log = new Log();
            log.setBalance(obj.getBalance());
            log.setAccount_source(obj.getAccountSource().getID());
            log.setAccount_destination(obj.getAccountDestination().getID());
            log.setCurrency_source(obj.getCurrencySource().toUpperCase());
            log.setCurrency_destination(obj.getCurrencyDestination().toUpperCase());
            log.setService(obj.getAction());
            logRepository.save(log);
            return  obj;
        }
        else if (obj.getStatus() && obj.getAction().equals("withdraw")){
            Log log = new Log();
            log.setBalance(obj.getBalance());
            log.setAccount_source(obj.getAccountSource().getID());
            log.setAccount_destination(obj.getAccountSource().getID());
            log.setCurrency_source(obj.getCurrencySource().toUpperCase());
            log.setService(obj.getAction());
            logRepository.save(log);
        }
        else if (obj.getStatus() && obj.getAction().equals("deposit")){
            Log log = new Log();
            log.setBalance(obj.getBalance());
            log.setAccount_source(obj.getAccountSource().getID());
            log.setAccount_destination(obj.getAccountSource().getID());
            log.setCurrency_source(obj.getCurrencySource().toUpperCase());
            log.setCurrency_destination(obj.getCurrencyDestination().toUpperCase());
            log.setService(obj.getAction());
            logRepository.save(log);
        }
        return null;
    }
}

