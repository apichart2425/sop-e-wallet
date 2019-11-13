package sop.ewallet.account.model;

public class UserRequest {
    private Boolean status;
    private String action;
    private double balance;
    private String currency_origin;
    private String currency_dest;
    private Account account_source;
    private Account account_destination;

    public UserRequest(){}

    public UserRequest(Boolean status, String action, double balance, String currency_origin, String currency_dest, Account account_source) {
        this.status = status;
        this.action = action;
        this.balance = balance;
        this.currency_origin = currency_origin.toLowerCase();
        this.currency_dest = currency_dest.toLowerCase();
        this.account_source = account_source;
    }

    public UserRequest(Boolean status, String action, double balance, String currency_origin, String currency_dest, Account account_source, Account account_destination) {
        this.status = status;
        this.action = action;
        this.balance = balance;
        this.currency_origin = currency_origin.toLowerCase();
        this.currency_dest = currency_dest.toLowerCase();
        this.account_source = account_source;
        this.account_destination = account_destination;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
}
