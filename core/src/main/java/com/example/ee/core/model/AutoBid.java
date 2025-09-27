package com.example.ee.core.model;

import java.io.Serializable;
import java.util.Date;

public class AutoBid implements Serializable {
    private int productId;
    private long userId;
    private double maxBid;
    private Date registeredAt;
    private double lastBidPlaced;

    // constructors, getters, setters


    public AutoBid() {
    }

    public AutoBid(int productId, long userId, double maxBid, Date registeredAt, double lastBidPlaced) {
        this.productId = productId;
        this.userId = userId;
        this.maxBid = maxBid;
        this.registeredAt = registeredAt;
        this.lastBidPlaced = lastBidPlaced;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(double maxBid) {
        this.maxBid = maxBid;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public double getLastBidPlaced() {
        return lastBidPlaced;
    }

    public void setLastBidPlaced(double lastBidPlaced) {
        this.lastBidPlaced = lastBidPlaced;
    }
}