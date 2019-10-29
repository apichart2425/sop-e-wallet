package sop.service.transactions.model;

import java.util.List;

public class Account {
    private long id;
    Wallet wallets;

    public Account() {
    }

    public Account(long id, Wallet wallets) {
        this.id = id;
        this.wallets = wallets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wallet getWallets() {
        return wallets;
    }

    public void setWallets(Wallet wallets) {
        this.wallets = wallets;
    }
}
