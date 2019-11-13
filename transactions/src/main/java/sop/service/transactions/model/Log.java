package sop.service.transactions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
//import javafx.beans.DefaultProperty;
//import jdk.internal.jline.internal.Nullable;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class Log {
    //    @Id @GeneratedValue val id: Long ? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "account_source is required")
    public long account_source;

//    @NotNull(message = "account_destination is required")
    public long account_destination;

    @NotNull(message = "balance is required")
    @Min(0)
    public double balance;

    @NotNull(message = "service is required")
    public String service;


    @NotNull(message = "currency_source is required")
    public String currency_source;

//    @NotNull(message = "currency_destination is required")
    public String currency_destination;

//    @NotNull(message = "Date(created_on) is required")
    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date created_on;

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

