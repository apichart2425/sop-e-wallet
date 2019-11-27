package sop.ewallet.transactions.controller;

import java.util.List;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sop.ewallet.transactions.model.*;
import sop.ewallet.transactions.payload.ApiResponse;
import sop.ewallet.transactions.payload.LogRequest;
import sop.ewallet.transactions.repositories.LogRepository;
import sop.ewallet.transactions.repositories.ResourceNotFoundException;

@RestController
@RequestMapping("/")
public class TransactionsController {
    @Autowired
    private LogRepository logRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${service.exchange}")
    private String exchangeUrl;

    @GetMapping("/")
    public String main() {
        return exchangeUrl;
    }

    @PostMapping("/log")
    public ResponseEntity<ApiResponse<List<Log>>> getAllLog(@Valid @RequestBody LogRequest logRequest) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, logRepository.findTransactionsByUser(logRequest.getSourceId()))
        );
    }

    @GetMapping("/log/index/{id}")
    public ResponseEntity<Log> getById(@PathVariable(value = "id") long log_id)
            throws ResourceNotFoundException {
        Log log_data = logRepository.findById(log_id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for this id :: " + log_id));
        return ResponseEntity.ok().body(log_data);
    }

    @PostMapping("/withdraw")
    public AccountWallet withdraw(@Valid @RequestBody AccountWallet obj) {
        double balance = obj.getBalance();

        long source_ID = obj.getAccountSource().getID();
        Map<String, Double> source_wallet = obj.getAccountSource().getWallets();
        String currency_source = obj.getCurrencySource();


        if (obj.getAction().equals("withdraw")) {
            if (source_wallet.get(currency_source.toLowerCase()) - balance >= 0) {
                source_wallet.put(currency_source.toLowerCase(), (Double)  source_wallet.get(currency_source.toLowerCase()) - balance);
                obj.setStatus(true);
            } else {
                obj.setStatus(false);
            }
        }

        return obj;
    }

    @PostMapping("/deposit")
    public AccountWallet deposit(@Valid @RequestBody AccountWallet obj) {
        double balance = obj.getBalance();

        long source_ID = obj.getAccountSource().getID();
        Map<String, Double> source_wallet = obj.getAccountSource().getWallets();
        String currency_source = obj.getCurrencySource();
        String currency_destination = obj.getCurrencyDestination();

        String url = exchangeUrl + "/?base=" + currency_source.toUpperCase();
        MapRate response_currency = restTemplate.getForObject(url, MapRate.class);

        double rate = response_currency.getPayload().get(currency_destination.toUpperCase());
        double new_balance = balance*rate;
        System.out.println(source_wallet);
        if (obj.getAction().equals("deposit")) {
            System.out.println("deposit");
            if (source_wallet.get(currency_source.toLowerCase()) >= 0) {
                source_wallet.put(currency_destination.toLowerCase(), (Double) source_wallet.get(currency_source.toLowerCase()) + new_balance);
                obj.setStatus(true);
            } else {
                obj.setStatus(false);
            }
        }

        return obj;
    }

    @PostMapping("/transfer")
    public AccountWallet transfer(@Valid @RequestBody AccountWallet obj) {
        double balance = obj.getBalance();

        long source_ID = obj.getAccountSource().getID();
        Map<String, Double> source_wallet = obj.getAccountSource().getWallets();
        String currency_source = obj.getCurrencySource();

        long destination_ID = obj.getAccountDestination().getID();
        Map<String, Double> destination_wallet = obj.getAccountDestination().getWallets();
        String currency_destination = obj.getCurrencyDestination();

        String url = exchangeUrl + "/?base=" + currency_source.toLowerCase();
        MapRate response_currency = restTemplate.getForObject(url, MapRate.class);

        double rate = response_currency.getPayload().get(currency_destination.toUpperCase());
        double new_balance = rate * balance;

        if (obj.getAction().equals("transfer")) {
            if ((source_wallet.get(currency_source.toLowerCase()) - balance) >= 0) {
                source_wallet.put(currency_source.toLowerCase(), source_wallet.get(currency_source.toLowerCase())
                    - balance);
                destination_wallet.put(currency_destination.toLowerCase(), destination_wallet.get(currency_destination.toLowerCase())
                    + new_balance);
                obj.setStatus(true);
            } else {
                obj.setStatus(false);
            }
        }
        return obj;
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

