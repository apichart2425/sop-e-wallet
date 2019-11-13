package sop.service.transactions.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import sop.service.transactions.model.*;

import java.io.IOException;
import java.util.Map;

public class TransactionMethod {
    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper mapper = new ObjectMapper();

    public AccountWallet withdraw(AccountWallet jobj){
        double blance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();


        if (jobj.getAction().equals("withdraw")) {
            if (source_wallet.get(currency_source.toLowerCase()) - blance >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_source.toLowerCase(), (Double)  source_wallet.get(currency_source.toLowerCase()) - blance);
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
        double blance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();
        String currency_destination = jobj.getCurrencyDestination();

        String exchangeUrl = "http://localhost:3000/services/exchange?base=" + currency_source.toUpperCase();
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);

        double rate = response_currency.getRates().get(currency_destination.toUpperCase());
        double new_blance = blance*rate;
        System.out.println(source_wallet);
        if (jobj.getAction().equals("deposit")) {
            System.out.println("deposit");
            if (source_wallet.get(currency_source.toLowerCase()) >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_destination.toLowerCase(), (Double) source_wallet.get(currency_source.toLowerCase()) + new_blance);
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

        double blance = jobj.getBalance();

        long source_ID = jobj.getAccountSource().getID();
        Map<String, Double> source_wallet = jobj.getAccountSource().getWallets();
        String currency_source = jobj.getCurrencySource();

        long destination_ID = jobj.getAccountDestination().getID();
        Map<String, Double> destination_wallet = jobj.getAccountDestination().getWallets();
        String currency_destination = jobj.getCurrencyDestination();

        String exchangeUrl = "http://localhost:3000/services/exchange?base=" + currency_source.toLowerCase();
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);

        double rate = response_currency.getRates().get(currency_destination.toUpperCase());
        double new_blance = rate * blance;

        if (jobj.getAction().equals("transfer")) {
            if ((source_wallet.get(currency_source.toLowerCase()) - blance) >= 0) {
                System.out.println("wallet :" + currency_source.toLowerCase());
                System.out.println(source_wallet.get(currency_source.toLowerCase()));
                source_wallet.put(currency_source.toUpperCase(), (Double) source_wallet.get(currency_source.toLowerCase()) - blance);
                destination_wallet.put(currency_destination.toLowerCase(), (Double) destination_wallet.get(currency_destination.toLowerCase())+new_blance);
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