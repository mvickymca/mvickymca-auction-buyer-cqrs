package com.auction.buyer.core.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Bid {

	@Id
	private String bidId;
	private String buyerId;
	private String productId;
	private Integer BidAmount;

}
