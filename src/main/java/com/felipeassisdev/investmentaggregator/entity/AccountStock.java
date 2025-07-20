package com.felipeassisdev.investmentaggregator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_accountstocks")
public class AccountStock {
    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "stock_id")
    @MapsId("stockId")
    private Stock stock;

    @Column(name = "quantity")
    private int quantity;

    public AccountStock() {

    }

    public AccountStock(AccountStockId id, Account account, Stock stock, int quantity) {
        this.id = id;
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
    }

    public AccountStockId getId() {
        return id;
    }

    public void setId(AccountStockId id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
