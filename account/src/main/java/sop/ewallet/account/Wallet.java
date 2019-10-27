package sop.ewallet.account;

/*
Action -> widthdraw = WD
          deposit = DP
          transfer = TF
 */

public class Wallet {
    private double USD;
    private double THB;
    private double JPY;
    private double CNY;
    private double EUR;
    private double SGD;

    Wallet(){
        this.USD = 0;
        this.THB = 0;
        this.EUR = 0;
        this.CNY = 0;
        this.JPY = 0;
        this.SGD = 0;
    }


    public void addBalance(String currency, double balance){
        if(currency.equals("USD")){
            setUSD(balance);
        }else if(currency.equals("THB")){
            setTHB(balance);
        }else if(currency.equals("EUR")){
            setEUR(balance);
        }else if(currency.equals("CNY")){
            setCNY(balance);
        }else if(currency.equals("JPY")){
            setJPY(balance);
        }else if(currency.equals("SGD")){
            setSGD(balance);
        }
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
