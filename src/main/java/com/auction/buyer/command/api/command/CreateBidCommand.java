package com.auction.buyer.command.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBidCommand {
	
	@TargetAggregateIdentifier
	private String bidId;
	private String buyerId;
	private String productId;
	private Integer bidAmount;

}
