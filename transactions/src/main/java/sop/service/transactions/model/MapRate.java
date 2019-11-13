package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MapRate {
    private Map<String, Double> payload;
    private String status;

    @JsonProperty("payload")
    public Map<String, Double> getPayload() { return payload; }
    @JsonProperty("payload")
    public void setPayload(Map<String, Double> value) { this.payload = value; }

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

}
