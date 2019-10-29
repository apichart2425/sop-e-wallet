package sop.service.transactions.model;

public class ActionTransaction {

    private  boolean status;
    private String action;
    private double balance;
    private String currency;
    Account account_source;
    Account account_destination;

    public ActionTransaction() {
    }

    public ActionTransaction(String action, double balance, String currency, Account account_source) {
        this.action = action;
        this.balance = balance;
        this.currency = currency;
        this.account_source = account_source;
    }

    public ActionTransaction(String action, double balance, String currency, Account account_source, Account account_destination) {
        this.action = action;
        this.balance = balance;
        this.currency = currency;
        this.account_source = account_source;
        this.account_destination = account_destination;
    }

    public ActionTransaction(boolean status, String action, double balance, String currency, Account account_source) {
        this.status = status;
        this.action = action;
        this.balance = balance;
        this.currency = currency;
        this.account_source = account_source;
    }

    public ActionTransaction(boolean status, String action, double balance, String currency, Account account_source, Account account_destination) {
        this.status = status;
        this.action = action;
        this.balance = balance;
        this.currency = currency;
        this.account_source = account_source;
        this.account_destination = account_destination;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Account getAccount_source() {
        return account_source;
    }

    public void setAccount_source(Account account_source) {
        this.account_source = account_source;
    }

    public Account getAccount_destination() {
        return account_destination;
    }

    public void setAccount_destination(Account account_destination) {
        this.account_destination = account_destination;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
