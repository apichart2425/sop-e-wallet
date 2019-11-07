package sop.ewallet.account.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ac_id;

    @NotNull(message = "balance is required")
    @Min(0)
    private double USD;
    @NotNull(message = "balance is required")
    @Min(0)
    private double THB;
    @NotNull(message = "balance is required")
    @Min(0)
    private double JPY;
    @NotNull(message = "balance is required")
    @Min(0)
    private double CNY;
    @NotNull(message = "balance is required")
    @Min(0)
    private double EUR;
    @NotNull(message = "balance is required")
    @Min(0)
    private double SGD;


//constructor
    public Account(int ac_id){
        this.ac_id = ac_id;
        this.USD = 0;
        this.THB = 0;
        this.EUR = 0;
        this.CNY = 0;
        this.JPY = 0;
        this.SGD = 0;
    }

    public Account() {

    }



    public int getAc_id() {
        return ac_id;
    }

    public void setAc_id(int ac_id) {
        this.ac_id = ac_id;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getTHB() {
        return THB;
    }

    public void setTHB(double THB) {
        this.THB = THB;
    }

    public double getJPY() {
        return JPY;
    }

    public void setJPY(double JPY) {
        this.JPY = JPY;
    }

    public double getCNY() {
        return CNY;
    }

    public void setCNY(double CNY) {
        this.CNY = CNY;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public double getSGD() {
        return SGD;
    }

    public void setSGD(double SGD) {
        this.SGD = SGD;
    }
}
