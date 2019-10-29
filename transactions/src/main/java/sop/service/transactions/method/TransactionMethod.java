package sop.service.transactions.method;

import sop.service.transactions.model.Account;
import sop.service.transactions.model.ActionTransaction;
import sop.service.transactions.model.Wallet;

public class TransactionMethod {

    public ActionTransaction withdraw(ActionTransaction jobj){
        long account_sourceID = jobj.getAccount_source().getId();
        Wallet account_wallet = jobj.getAccount_source().getWallets();
        double balance = jobj.getBalance();
        String currency  = jobj.getCurrency();
        switch (currency){
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

        }
        return jobj;
    }
}
