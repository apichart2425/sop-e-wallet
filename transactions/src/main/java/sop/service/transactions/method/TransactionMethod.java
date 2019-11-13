package sop.service.transactions.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import sop.service.transactions.model.*;

import java.io.IOException;
import java.util.Map;

public class TransactionMethod {
    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper mapper = new ObjectMapper();

    public ActionTransaction withdraw(ActionTransaction jobj) {
        long account_sourceID = jobj.getAccount_source().getId();
        Wallet account_wallet = jobj.getAccount_source().getWallets();
        double balance = jobj.getBalance();
        String currency_source = jobj.getCurrency_source().toLowerCase();

        if (jobj.getAction().equals("withdraw")) {
            switch (currency_source) {
                case "usd":
                    if (account_wallet.getUSD() >= balance) {
                        account_wallet.setUSD(account_wallet.getUSD() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                case "thb":
                    if (account_wallet.getTHB() >= balance) {
                        account_wallet.setTHB(account_wallet.getTHB() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                case "eur":
                    if (account_wallet.getEUR() >= balance) {
                        account_wallet.setEUR(account_wallet.getEUR() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                case "jpy":
                    if (account_wallet.getJPY() >= balance) {
                        account_wallet.setJPY(account_wallet.getJPY() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                case "cny":
                    if (account_wallet.getCNY() >= balance) {
                        account_wallet.setCNY(account_wallet.getCNY() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                case "sgd":
                    if (account_wallet.getSGD() >= balance) {
                        account_wallet.setSGD(account_wallet.getSGD() - balance);
                        jobj.setStatus(true);
                    } else
                        jobj.setStatus(false);
                    break;
                default:
                    jobj.setStatus(false);

            }
        } else
            jobj.setStatus(false);
        return jobj;
    }

    public ActionTransaction deposit(ActionTransaction jobj) throws IOException {

        long account_sourceID = jobj.getAccount_source().getId();
        Wallet account_wallet = jobj.getAccount_source().getWallets();
        double balance = jobj.getBalance();
        String currency_source = jobj.getCurrency_source();
        String currency_destination = jobj.getCurrency_destination();

        String exchangeUrl = "http://localhost:3000/services/exchange?base=" + jobj.getCurrency_source().toUpperCase();
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);
        System.out.println(response_currency.getRates().get(currency_destination.toUpperCase()));

        double rate = response_currency.getRates().get(currency_destination.toUpperCase());
        double new_blance = balance * rate;
        if (jobj.getAction().equals("deposit")) {
            System.out.println(currency_destination.toLowerCase());
            switch (currency_destination.toLowerCase()) {
                case "usd":
                    System.out.println("wallet usd");
                    System.out.println(account_wallet.getUSD());
                    account_wallet.setUSD(account_wallet.getUSD() + new_blance);
                    System.out.println("New wallet usd");
                    System.out.println(account_wallet.getUSD());
                    jobj.setStatus(true);
                    break;
                case "thb":
                    System.out.println("thai");
                    System.out.println("wallet thai");
                    System.out.println(account_wallet.getTHB());
                    account_wallet.setTHB(account_wallet.getTHB() + new_blance);
                    System.out.println("New wallet thai");
                    System.out.println(account_wallet.getTHB());
                    jobj.setStatus(true);
                    break;
                case "eur":
                    System.out.println("eur");
                    System.out.println("wallet eur");
                    System.out.println(account_wallet.getEUR());
                    account_wallet.setEUR(account_wallet.getEUR() + new_blance);
                    System.out.println("New wallet eur");
                    System.out.println(account_wallet.getEUR());
                    jobj.setStatus(true);
                    break;
                case "jpy":
                    System.out.println("jpy");
                    System.out.println("wallet jpy");
                    System.out.println(account_wallet.getJPY());
                    account_wallet.setJPY(account_wallet.getJPY() + new_blance);
                    System.out.println("New wallet jpy");
                    System.out.println(account_wallet.getJPY());
                    jobj.setStatus(true);
                    break;
                case "cny":
                    System.out.println("cny");
                    System.out.println("wallet cny");
                    System.out.println(account_wallet.getCNY());
                    account_wallet.setCNY(account_wallet.getCNY() + new_blance);
                    System.out.println("New wallet cny");
                    System.out.println(account_wallet.getCNY());
                    jobj.setStatus(true);
                    break;
                case "sgd":
                    System.out.println("sgd");
                    System.out.println("wallet sgd");
                    System.out.println(account_wallet.getSGD());
                    account_wallet.setSGD(account_wallet.getSGD() + new_blance);
                    System.out.println("New wallet sgd");
                    System.out.println(account_wallet.getSGD());
                    jobj.setStatus(true);
                    break;
                default:
                    jobj.setStatus(false);
            }
        } else
            jobj.setStatus(false);
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

        String exchangeUrl = "http://localhost:3000/services/exchange?base=" + currency_source.toUpperCase();
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);

        double rate = response_currency.getRates().get(currency_destination.toUpperCase());
        double new_blance = rate * blance;

        if (jobj.getAction().equals("transfer")) {
            System.out.println("save if");
            if ((source_wallet.get(currency_source.toUpperCase()) - blance) >= 0) {
                System.out.println("wallet :" + currency_source.toUpperCase());
                System.out.println(source_wallet.get(currency_source.toUpperCase()));
                source_wallet.put(currency_source.toUpperCase(), (Double) source_wallet.get(currency_source.toUpperCase()) - blance);
                destination_wallet.put(currency_destination.toUpperCase(), (Double) destination_wallet.get(currency_destination.toUpperCase())+new_blance);
                System.out.println("New wallet :" + currency_source.toUpperCase());
                System.out.println(destination_wallet.get(currency_destination.toUpperCase()));
                jobj.setStatus(true);
            } else {
                jobj.setStatus(false);
            }
        }
        return jobj;
    }
}