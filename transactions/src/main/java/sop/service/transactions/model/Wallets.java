package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Wallets {
    private long id;
    private Map<String, Double> wallets;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("wallets")
    public Map<String, Double> getWallets() { return wallets; }
    @JsonProperty("wallets")
    public void setWallets(Map<String, Double> value) { this.wallets = value; }
}
