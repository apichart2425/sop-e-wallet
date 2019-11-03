package sop.ewallet.account.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ac_id;

    @NotNull(message = "account_source is required")

    @JoinColumn(name = "ac_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    private Wallet ac_wallet;



    public Account(int ac_id){
        ac_wallet = new Wallet(ac_id);
        this.ac_id = ac_id;
    }

    public Account() {
        ac_wallet = new Wallet(1);
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
    public Wallet deposit(String currency, double amount){
//        Add action
        this.ac_wallet.addBalance(currency, amount);
        return ac_wallet;
    }
}
