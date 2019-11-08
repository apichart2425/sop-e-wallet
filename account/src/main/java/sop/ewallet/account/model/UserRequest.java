package sop.ewallet.account.model;

public class UserRequest {
    private int id_origin;
    private int id_dest;
    private double balance;
    private String currency_origin;
    private String currency_dest;

    public UserRequest(){}
    public UserRequest(int id_origin, double balance, String currency_origin) {
        this.id_origin = id_origin;
        this.balance = balance;
        this.currency_origin = currency_origin;
    }

    public UserRequest(int id_origin, int id_dest, double balance, String currency_origin, String currency_dest) {
        this.id_origin = id_origin;
        this.id_dest = id_dest;
        this.balance = balance;
        this.currency_origin = currency_origin;
        this.currency_dest = currency_dest;
    }

    public int getId_origin() {
        return id_origin;
    }

    public void setId_origin(int id_origin) {
        this.id_origin = id_origin;
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

    public int getId_dest() { return id_dest; }

    public void setId_dest(int id_dest) { this.id_dest = id_dest; }

}
