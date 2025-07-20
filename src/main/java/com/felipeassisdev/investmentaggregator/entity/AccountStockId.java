package com.felipeassisdev.investmentaggregator.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable  // Indica ao Hibernate que essa entidade pode ser um campo (chave composta)
public class AccountStockId {
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private UUID stockId;

    public AccountStockId() {

    }

    public AccountStockId(UUID accountId, UUID stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }


}
