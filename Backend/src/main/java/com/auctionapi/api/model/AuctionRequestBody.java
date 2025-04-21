package com.auctionapi.api.model;

import java.util.Date;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;


public class AuctionRequestBody {
 
	private int AuctionId;

	 
	private String AuctionName;
	 
	private int parentAuctionId;
	  
	private Date createdAt;

	public int getAuctionId() {
		return AuctionId;
	}

	public void setAuctionId(int AuctionId) {
		this.AuctionId = AuctionId;
	}

	public String getAuctionName() {
		return AuctionName;
	}

	public void setAuctionName(String AuctionName) {
		this.AuctionName = AuctionName;
	}

	public int getParentAuctionId() {
		return parentAuctionId;
	}

	public void setParentAuctionId(int parentAuctionId) {
		this.parentAuctionId = parentAuctionId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
 
 

}
