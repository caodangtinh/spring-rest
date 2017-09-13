package com.tinhcao.model;

import java.util.Currency;

public class Account {
    private long customerId;
    private String customerName;
    private double amount;
    private Currency currency;

    public Account() {
    }

    public Account(long customerId, String customerName, double amount, Currency currency) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.amount = amount;
        this.currency = currency;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
