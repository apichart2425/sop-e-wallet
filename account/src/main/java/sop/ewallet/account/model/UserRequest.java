package sop.ewallet.account.model;

import org.apache.catalina.User;

import javax.validation.constraints.Null;

public class UserRequest {
    private int id;
    private double balance;
    private String currency_origin;
    private String currency_dest;

    public UserRequest(){}
    public UserRequest(int id, double balance, String currency_origin) {
        this.id = id;
        this.balance = balance;
        this.currency_origin = currency_origin;
    }

    public UserRequest(int id, double balance, String currency_origin, String currency_dest) {
        this.id = id;
        this.balance = balance;
        this.currency_origin = currency_origin;
        this.currency_dest = currency_dest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
