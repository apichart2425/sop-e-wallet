package sop.service.transactions.controller;

//https://www.javaguides.net/2019/01/springboot-postgresql-jpa-hibernate-crud-restful-api-tutorial.html

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sop.service.transactions.method.TransactionMethod;
import sop.service.transactions.model.*;
import sop.service.transactions.repositories.LogRepository;
import sop.service.transactions.repositories.ResourceNotFoundException;

//import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
//import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
//import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;

@RestController
@RequestMapping("/service/transaction")
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
        AccountWallet data = transactionMethod.withdraw(obj);
        if(data.getStatus()){
            Log log = new Log();
            log.setBalance(data.getBalance());
            log.setAccount_source(data.getAccountSource().getID());
            log.setAccount_destination(data.getAccountSource().getID());
            log.setCurrency_source(data.getCurrencySource().toUpperCase());
            log.setService(data.getAction());
            logRepository.save(log);
        }
        return data;
    }

    @PostMapping("/deposit")
    public AccountWallet deposit(@Valid @RequestBody AccountWallet obj) throws IOException {
        AccountWallet data = transactionMethod.deposit(obj);
        if(data.getStatus()){
            Log log = new Log();
            log.setBalance(data.getBalance());
            log.setAccount_source(data.getAccountSource().getID());
            log.setAccount_destination(data.getAccountSource().getID());
            log.setCurrency_source(data.getCurrencySource().toUpperCase());
            log.setCurrency_destination(data.getCurrencyDestination().toUpperCase());
            log.setService(data.getAction());
            logRepository.save(log);
        }
        return data;
    }

    @PostMapping("/transfer")
    public AccountWallet transfer(@Valid @RequestBody AccountWallet obj) throws IOException {
        AccountWallet data =   transactionMethod.transfer(obj);
        if(data.getStatus()){
            Log log = new Log();
            log.setBalance(data.getBalance());
            log.setAccount_source(data.getAccountSource().getID());
            log.setAccount_destination(data.getAccountDestination().getID());
            log.setCurrency_source(data.getCurrencySource().toUpperCase());
            log.setCurrency_destination(data.getCurrencyDestination().toUpperCase());
            log.setService(data.getAction());
            logRepository.save(log);
        }
        return  data;

    }
}

