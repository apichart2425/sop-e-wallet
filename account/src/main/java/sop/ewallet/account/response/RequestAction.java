package sop.ewallet.account.response;

import sop.ewallet.account.model.Account;

/*
actions =>  DP = deposit
            WD = withdraw
            TF = transfer
 */

public class RequestAction {
    private boolean status;
    private String action;
    private double balance;
    private String currency_origin;
    private String currency_dest;
    private Account origin;
    private Account dest;

    public RequestAction() {}

    public RequestAction(String action, double balance, String currency_origin, Account origin) {
        this.action = action;
        this.balance = balance;
        this.currency_origin = currency_origin;
        this.origin = origin;
    }

    public RequestAction(String action, double balance, String currency_origin, String currency_dest, Account origin) {
        this.action = action;
        this.balance = balance;
        this.currency_origin = currency_origin;
        this.currency_dest = currency_dest;
        this.origin = origin;
    }

    public RequestAction(String action, double balance, String currency_origin, String currency_dest, Account origin, Account dest) {
        this.action = action;
        this.balance = balance;
        this.currency_origin = currency_origin;
        this.currency_dest = currency_dest;
        this.origin = origin;
        this.dest = dest;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency_origin() {
        return currency_origin;
    }

    public void setCurrency_origin(String currency_origin) {
        this.currency_origin = currency_origin;
    }

    public String getCurrency_dest() {
        return currency_dest;
    }

    public void setCurrency_dest(String currency_dest) {
        this.currency_dest = currency_dest;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDest() {
        return dest;
    }

    public void setDest(Account dest) {
        this.dest = dest;
    }
}
