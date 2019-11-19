package sop.ewallet.account.model;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class Wallet implements Serializable {

    private double USD;
    private double THB;
    private double JPY;
    private double CNY;
    private double EUR;
    private double SGD;

    public Wallet(){

    }
    public Wallet(double USD, double THB, double JPY, double CNY, double EUR, double SGD) {
        this.USD = USD;
        this.THB = THB;
        this.JPY = JPY;
        this.CNY = CNY;
        this.EUR = EUR;
        this.SGD = SGD;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "USD='" + USD + "'" +
                ", THB='" + THB + "'" +
                ", JPY='" + JPY + "'" +
                ", CNY='" + CNY + "'" +
                ", EUR='" + EUR + "'" +
                ", SGD='" + SGD + "'" +
                '}';
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
