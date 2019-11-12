package sop.service.transactions.model;

public class Wallet {
        private double USD;
        private double THB;
        private double EUR;
        private double JPY;
        private double CNY;
        private double SGD;

    public Wallet() {
    }

    public Wallet(double USD, double THB, double EUR, double JPY, double CNY, double SGD) {
        this.USD = USD;
        this.THB = THB;
        this.EUR = EUR;
        this.JPY = JPY;
        this.CNY = CNY;
        this.SGD = SGD;
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

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
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

    public double getSGD() {
        return SGD;
    }

    public void setSGD(double SGD) {
        this.SGD = SGD;
    }
}
