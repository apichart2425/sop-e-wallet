package sop.ewallet.transactions.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sop.ewallet.transactions.model.*;
import sop.ewallet.transactions.repositories.LogRepository;

import java.util.Map;

@Component
public class TransactionMethod {

    @Value("${service.exchange}")
    private String exchangeUrl;

    @Autowired
    private LogRepository logRepository;

    RestTemplate restTemplate = new RestTemplate();

    private Log new_log = new Log();

    public AccountWallet withdraw(AccountWallet jobj){
        double balance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();


        if (jobj.getAction().equals("withdraw")) {
            if (source_wallet.get(currency_source.toLowerCase()) - balance >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_source.toLowerCase(), (Double)  source_wallet.get(currency_source.toLowerCase()) - balance);
                System.out.println("New wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                jobj.setStatus(true);
            } else {
                jobj.setStatus(false);
            }
        }
        return jobj;
    }

    public AccountWallet deposit(AccountWallet jobj){
        double balance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();
        String currency_destination = jobj.getCurrencyDestination();

        String url = exchangeUrl + "/?base=" + currency_source.toUpperCase();
        System.out.println(url);
        MapRate response_currency = restTemplate.getForObject(url, MapRate.class);

        double rate = response_currency.getPayload().get(currency_destination.toUpperCase());
        double new_balance = balance*rate;
        System.out.println(source_wallet);
        if (jobj.getAction().equals("deposit")) {
            System.out.println("deposit");
            if (source_wallet.get(currency_source.toLowerCase()) >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_destination.toLowerCase(), (Double) source_wallet.get(currency_source.toLowerCase()) + new_balance);
                System.out.println("New wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_destination.toLowerCase()));
                jobj.setStatus(true);
            } else {
                jobj.setStatus(false);
            }
        }
        return jobj;
    }

    public AccountWallet transfer(AccountWallet jobj) {

        double balance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();

        long destination_ID = jobj.getAccountDestination().getID();
        Map<String, Double> destination_wallet = jobj.getAccountDestination().getWallets();
        String currency_destination = jobj.getCurrencyDestination();

        String url = exchangeUrl + "/?base=" + currency_source.toLowerCase();
        MapRate response_currency = restTemplate.getForObject(url, MapRate.class);

        double rate = response_currency.getPayload().get(currency_destination.toUpperCase());
        double new_balance = rate * balance;

        if (jobj.getAction().equals("transfer")) {
            if ((source_wallet.get(currency_source.toLowerCase()) - balance) >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_source.toLowerCase(), (Double) source_wallet.get(currency_source.toLowerCase()) - balance);
                destination_wallet.put(currency_destination.toLowerCase(), (Double) destination_wallet.get(currency_destination.toLowerCase()) + new_balance);
                System.out.println("New wallet :" + currency_source.toLowerCase());
                System.out.println(destination_wallet.get(currency_destination.toLowerCase()));
                jobj.setStatus(true);
            } else {
                jobj.setStatus(false);
            }
        }
        return jobj;
    }
}