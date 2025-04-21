package com.auctionapi.api.entity;

import java.util.Date;
import javax.persistence.*;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
@Entity
@Table(name = "demo_bids")
public class DemoBidsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false)
    private Long bidId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "auction_id", nullable = false)
    private int auctionId;

    @Column(name = "bid_amount", nullable = false)
    private double bidAmount;

    @Column(name = "bid_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTime;

    // Getters and Setters
    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }
}