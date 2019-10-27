package sop.ewallet.account;

public class Account {
    private int ac_id;
    private Wallet ac_wallet;

    Account(int ac_id){
        ac_wallet = new Wallet();
        this.ac_id = ac_id;
    }

    public Account() {
        ac_wallet = new Wallet();
//        this.ac_id = ac_id;
    }

    public Wallet getInfo(){
        return ac_wallet;
    }

    public Wallet transfer(Account traget){
//        Add action
        return ac_wallet;
    }
    public Wallet withdraw(double amount){
//        Add action
        return ac_wallet;
    }
    public Wallet deposit(double amount){
//        Add action
        this.ac_wallet.setUSD(amount);
        return ac_wallet;
    }
}
