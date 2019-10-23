package sop.ewallet.account;

public class Account {
    private int ac_id;
    private Wallet ac_wallet;

    Account(){
        ac_wallet = new Wallet();
        ac_id = 1;
    }

    public Wallet addDefaultBalance(String currency, double balance){
        ac_wallet.addBalance(currency, balance);
        return ac_wallet;
    }

    public double getUSDBalance(){
        return ac_wallet.getUSD();
    }

    public Wallet getInfo(){
        return ac_wallet;
    }
}
