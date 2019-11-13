package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountWallet {
    private boolean status;
    private String action;
    private long balance;
    private String currencySource;
    private String currencyDestination;
    private Wallets accountSource;
    private Wallets accountDestination;


    @JsonProperty("status")
    public boolean getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(boolean value) { this.status = value; }

    @JsonProperty("action")
    public String getAction() { return action; }
    @JsonProperty("action")
    public void setAction(String value) { this.action = value; }

    @JsonProperty("balance")
    public long getBalance() { return balance; }
    @JsonProperty("balance")
    public void setBalance(long value) { this.balance = value; }

    @JsonProperty("currency_source")
    public String getCurrencySource() { return currencySource; }
    @JsonProperty("currency_source")
    public void setCurrencySource(String value) { this.currencySource = value; }

    @JsonProperty("currency_destination")
    public String getCurrencyDestination() { return currencyDestination; }
    @JsonProperty("currency_destination")
    public void setCurrencyDestination(String value) { this.currencyDestination = value; }

    @JsonProperty("account_source")
    public Wallets getAccountSource() { return accountSource; }
    @JsonProperty("account_source")
    public void setAccountSource(Wallets value) { this.accountSource = value; }


    @JsonProperty("account_destination")
    public Wallets getAccountDestination() { return accountDestination; }
    @JsonProperty("account_destination")
    public void setAccountDestination(Wallets value) { this.accountDestination = value; }
}
