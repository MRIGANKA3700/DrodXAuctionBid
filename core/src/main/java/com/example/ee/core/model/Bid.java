package com.example.ee.core.model;


import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
    private Long productId;
    private String username;
    private double amount;
    private Date timestamp;

    public Bid() {}

    public Bid(Long productId, String username, double amount, Date timestamp) {
        this.productId = productId;
        this.username = username;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
