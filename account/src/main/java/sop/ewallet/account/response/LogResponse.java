package sop.ewallet.account.response;

import java.util.Date;
import java.util.List;

class Log {

  private Long id;

  private long account_source;

  private long account_destination;

  private double balance;

  private String service;

  private String currency_source;

  private String currency_destination;

  private Date created_on;

  public Log() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getAccount_source() {
    return account_source;
  }

  public void setAccount_source(long account_source) {
    this.account_source = account_source;
  }

  public long getAccount_destination() {
    return account_destination;
  }

  public void setAccount_destination(long account_destination) {
    this.account_destination = account_destination;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getCurrency_source() {
    return currency_source;
  }

  public void setCurrency_source(String currency_source) {
    this.currency_source = currency_source;
  }

  public String getCurrency_destination() {
    return currency_destination;
  }

  public void setCurrency_destination(String currency_destination) {
    this.currency_destination = currency_destination;
  }

  public Date getCreated_on() {
    return created_on;
  }

  public void setCreated_on(Date created_on) {
    this.created_on = created_on;
  }
}

public class LogResponse {

  private boolean success;
  private List<Log> payload;

  public LogResponse() {
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public List<Log> getPayload() {
    return payload;
  }

  public void setPayload(List<Log> payload) {
    this.payload = payload;
  }
}
