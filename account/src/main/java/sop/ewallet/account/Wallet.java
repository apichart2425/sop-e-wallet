package sop.ewallet.account;

public class Wallet {
    private double USD;
    private double THB;
    private double JPY;
    private double CNY;
    private double EUR;
    private double SGD;

    Wallet(){}
    Wallet(double USD, double THB, double EUR, double JPY, double CNY, double SGD){
        this.USD = USD;
        this.THB = THB;
        this.EUR = EUR;
        this.CNY = CNY;
        this.JPY = JPY;
        this.SGD = SGD;
    }
    public void inductBalance(String currency, double amount){
        if(currency.equals("USD")){
            USD += amount;
        }else if(currency.equals("THB")){
            THB += amount;
        }else if(currency.equals("EUR")){
            EUR += amount;
        }else if(currency.equals("CNY")){
            CNY += amount;
        }else if(currency.equals("JPY")){
            JPY += amount;
        }else if(currency.equals("SGD")){
            SGD += amount;
        }
    }

    public void deductBalance(String currency, double amount){
        if(currency.equals("USD")){
            USD -= amount;
        }else if(currency.equals("THB")){
            THB -= amount;
        }else if(currency.equals("EUR")){
            EUR -= amount;
        }else if(currency.equals("CNY")){
            CNY -= amount;
        }else if(currency.equals("JPY")){
            JPY -= amount;
        }else if(currency.equals("SGD")){
            SGD -= amount;
        }
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
