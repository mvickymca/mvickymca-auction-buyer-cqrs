package com.auction.buyer.command.api.command.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidCreateEvent {

	private String bidId;
	private String buyerId;
	private String productId;
	private Integer BidAmount;

}
