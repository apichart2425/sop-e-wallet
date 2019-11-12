package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MapRate {
    private Map<String, Double> rates;
    private String base;
    private String date;

    @JsonProperty("rates")
    public Map<String, Double> getRates() { return rates; }
    @JsonProperty("rates")
    public void setRates(Map<String, Double> value) { this.rates = value; }

    @JsonProperty("base")
    public String getBase() { return base; }
    @JsonProperty("base")
    public void setBase(String value) { this.base = value; }

    @JsonProperty("date")
    public String getDate() { return date; }
    @JsonProperty("date")
    public void setDate(String value) { this.date = value; }

}
