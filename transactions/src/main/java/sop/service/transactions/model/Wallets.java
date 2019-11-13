package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Wallets {
    private long id;
    private Map<String, Double> wallet;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("wallet")
    public Map<String, Double> getWallets() { return wallet; }
    @JsonProperty("wallet")
    public void setWallets(Map<String, Double> value) { this.wallet = value; }
}