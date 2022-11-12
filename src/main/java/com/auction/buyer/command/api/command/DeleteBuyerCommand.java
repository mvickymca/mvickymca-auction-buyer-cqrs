package com.auction.buyer.command.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBuyerCommand {
	
	@TargetAggregateIdentifier
	private String buyerId;

}
