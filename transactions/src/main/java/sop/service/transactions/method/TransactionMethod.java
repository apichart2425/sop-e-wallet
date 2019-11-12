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
        System.out.println(account_wallet.getTHB());
        System.out.println("--------------------------");

        if (jobj.getAction().equals("deposit")){
            System.out.println(currency_destination.toLowerCase());
            switch (currency_destination.toLowerCase()){
                case "usd":
                    System.out.println("wallet usd");
                    System.out.println(account_wallet.getUSD());
                    account_wallet.setUSD(account_wallet.getUSD() + (balance*rate));
                    System.out.println("New wallet usd");
                    System.out.println(account_wallet.getUSD());
                    jobj.setStatus(true);
                    break;
                case "thb":
                    System.out.println("thai");
                    System.out.println("wallet thai");
                    System.out.println(account_wallet.getTHB());
                    account_wallet.setTHB(account_wallet.getTHB() + (balance*rate));
                    System.out.println("New wallet thai");
                    System.out.println(account_wallet.getTHB());
                    jobj.setStatus(true);
                    break;
                case "eur":
                    System.out.println("eur");
                    System.out.println("wallet eur");
                    System.out.println(account_wallet.getEUR());
                    account_wallet.setEUR(account_wallet.getEUR() + (balance*rate));
                    System.out.println("New wallet eur");
                    System.out.println(account_wallet.getEUR());
                    jobj.setStatus(true);
                    break;
                case "jpy":
                    System.out.println("jpy");
                    System.out.println("wallet jpy");
                    System.out.println(account_wallet.getJPY());
                    account_wallet.setJPY(account_wallet.getJPY() + (balance*rate));
                    System.out.println("New wallet jpy");
                    System.out.println(account_wallet.getJPY());
                    jobj.setStatus(true);
                    break;
                case "cny":
                    System.out.println("cny");
                    System.out.println("wallet cny");
                    System.out.println(account_wallet.getCNY());
                    account_wallet.setCNY(account_wallet.getCNY() + (balance*rate));
                    System.out.println("New wallet cny");
                    System.out.println(account_wallet.getCNY());
                    jobj.setStatus(true);
                    break;
                case "sgd":
                    System.out.println("sgd");
                    System.out.println("wallet sgd");
                    System.out.println(account_wallet.getSGD());
                    account_wallet.setSGD(account_wallet.getSGD() + (balance*rate));
                    System.out.println("New wallet sgd");
                    System.out.println(account_wallet.getSGD());
                    jobj.setStatus(true);
                    break;
                default:
                    jobj.setStatus(false);
            }}
        else
            jobj.setStatus(false);
        return jobj;
    }
}
