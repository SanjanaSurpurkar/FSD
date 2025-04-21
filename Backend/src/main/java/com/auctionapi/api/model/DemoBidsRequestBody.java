package com.auctionapi.api.model;

import java.time.LocalDateTime;

public class DemoBidsRequestBody {

    private Long bidId;
    private Long userId;
    private Long auctionId;
    private Double bidAmount;
    private LocalDateTime bidTime;

    // Getter and Setter for bidId
    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    // Getter and Setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for auctionId
    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    // Getter and Setter for bidAmount
    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    // Getter and Setter for bidTime
    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }
}
