package com.auction.buyer.command.api.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.auction.buyer.command.api.command.CreateBidCommand;
import com.auction.buyer.command.api.command.event.BidCreateEvent;

@Aggregate
public class BidAggregate {
	@AggregateIdentifier
	private String bidId;
	private String buyerId;
	private String productId;
	private Integer BidAmount;
	
	@CommandHandler
	public BidAggregate(CreateBidCommand createBidCommand) {

		// You can perform all the validations
		BidCreateEvent bidCreateEvent = new BidCreateEvent();

		BeanUtils.copyProperties(createBidCommand, bidCreateEvent);

		AggregateLifecycle.apply(bidCreateEvent);

	}
	
	@EventSourcingHandler
	public void on(BidCreateEvent bidCreateEvent) {

		this.bidId=bidCreateEvent.getBidId();
		this.buyerId = bidCreateEvent.getBuyerId();
		this.productId=bidCreateEvent.getProductId();
		this.BidAmount=bidCreateEvent.getBidAmount();
	}

}
