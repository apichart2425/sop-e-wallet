package sop.service.transactions.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sop.service.transactions.model.ActionTransaction;
import sop.service.transactions.model.MapRate;
import sop.service.transactions.model.Wallet;

import java.io.IOException;

public class TransactionMethod {
    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper mapper = new ObjectMapper();

    public ActionTransaction withdraw(ActionTransaction jobj){
        long account_sourceID = jobj.getAccount_source().getId();
        Wallet account_wallet = jobj.getAccount_source().getWallets();
        double balance = jobj.getBalance();
        String currency_source  = jobj.getCurrency_source().toLowerCase();

        if (jobj.getAction().equals("withdraw")){
        switch (currency_source){
            case "usd":
                if(account_wallet.getUSD() >= balance){
                    account_wallet.setUSD(account_wallet.getUSD() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            case "thb":
                if(account_wallet.getTHB() >= balance){
                    account_wallet.setTHB(account_wallet.getTHB() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            case "eur":
                if(account_wallet.getEUR() >= balance){
                    account_wallet.setEUR(account_wallet.getEUR() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            case "jpy":
                if(account_wallet.getJPY() >= balance){
                    account_wallet.setJPY(account_wallet.getJPY() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            case "cny":
                if(account_wallet.getCNY() >= balance){
                    account_wallet.setCNY(account_wallet.getCNY() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            case "sgd":
                if(account_wallet.getSGD() >= balance){
                    account_wallet.setSGD(account_wallet.getSGD() - balance);
                    jobj.setStatus(true);
                }
                else
                    jobj.setStatus(false);
                break;
            default:
                jobj.setStatus(false);

        }}
        else
            jobj.setStatus(false);
        return jobj;
    }

    public ActionTransaction deposit(ActionTransaction jobj) throws IOException {

        String currency = "EUR";
        long account_sourceID = jobj.getAccount_source().getId();
        Wallet account_wallet = jobj.getAccount_source().getWallets();
        double balance = jobj.getBalance();
        String currency_source  = jobj.getCurrency_source();
        String currency_destination = jobj.getCurrency_destination();

        String exchangeUrl = "http://localhost:3000/services/exchange?base="+ jobj.getCurrency_source().toUpperCase();
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);
        System.out.println(response_currency.getRates().get(currency_destination.toUpperCase()));

        double rate = response_currency.getRates().get(currency_destination.toUpperCase());
        System.out.println("--------------------------");
        System.out.println(balance*rate);
        System.out.println("--------------------------");

        if (jobj.getAction().equals("deposit")){
            System.out.println("If case");
            System.out.println(currency_destination.toLowerCase());
            switch (currency_destination.toLowerCase()){
                case "usd":
                    if(account_wallet.getUSD() <= 0){
                        System.out.println("add blance case");
                        account_wallet.setUSD(account_wallet.getUSD() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                case "thb":
                    if(account_wallet.getTHB() <= 0){
                        account_wallet.setTHB(account_wallet.getTHB() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                case "eur":
                    if(account_wallet.getEUR() <= 0){
                        account_wallet.setEUR(account_wallet.getEUR() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                case "jpy":
                    if(account_wallet.getJPY() <= 0){
                        account_wallet.setJPY(account_wallet.getJPY() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                case "cny":
                    if(account_wallet.getCNY() <= 0){
                        account_wallet.setCNY(account_wallet.getCNY() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                case "sgd":
                    if(account_wallet.getSGD() <= 0){
                        account_wallet.setSGD(account_wallet.getSGD() + (balance*rate));
                        jobj.setStatus(true);
                    }
                    else
                        jobj.setStatus(false);
                    break;
                default:
                    jobj.setStatus(false);

            }}
        else
            jobj.setStatus(false);
        return jobj;
    }
}
