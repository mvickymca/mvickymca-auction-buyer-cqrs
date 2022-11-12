package com.auction.buyer.command.api.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.auction.buyer.command.api.command.CreateBuyerCommand;
import com.auction.buyer.command.api.command.DeleteBuyerCommand;
import com.auction.buyer.command.api.command.UpdateBuyerCommand;
import com.auction.buyer.command.api.command.event.BuyerCreatedEvent;
import com.auction.buyer.command.api.command.event.BuyerDeleteEvent;
import com.auction.buyer.command.api.command.event.BuyerUpdateEvent;

@Aggregate
public class BuyerAggregate {

	@AggregateIdentifier
	private String buyerId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String pin;
	private String phone;
	private String email;

	@CommandHandler
	public BuyerAggregate(CreateBuyerCommand createBuyerCommand) {

		// You can perform all the validations
		BuyerCreatedEvent buyerCreatedEvent = new BuyerCreatedEvent();

		BeanUtils.copyProperties(createBuyerCommand, buyerCreatedEvent);

		AggregateLifecycle.apply(buyerCreatedEvent);

	}
	
	@CommandHandler
	public BuyerAggregate(UpdateBuyerCommand createBuyerCommand) {

		// You can perform all the validations
		BuyerCreatedEvent buyerCreatedEvent = new BuyerCreatedEvent();

		BeanUtils.copyProperties(createBuyerCommand, buyerCreatedEvent);

		AggregateLifecycle.apply(buyerCreatedEvent);

	}
	
	
	@CommandHandler
	public String BuyerAggregateForUpdate(UpdateBuyerCommand updateBuyerCommand) {

		// You can perform all the validations
		BuyerUpdateEvent buyerUpdateEvent = new BuyerUpdateEvent();

		BeanUtils.copyProperties(updateBuyerCommand, buyerUpdateEvent);

		AggregateLifecycle.apply(buyerUpdateEvent);
		return "updted Buyer";

	}
	
	@CommandHandler
	public String BuyerAggregateForDelete(DeleteBuyerCommand deleteBuyerCommand) {

		// You can perform all the validations
		BuyerDeleteEvent buyerDeleteEvent = new BuyerDeleteEvent();

		BeanUtils.copyProperties(deleteBuyerCommand, buyerDeleteEvent);

		AggregateLifecycle.apply(buyerDeleteEvent);
		return "updted Buyer";

	}

	public BuyerAggregate() {
	}

	@EventSourcingHandler
	public void on(BuyerCreatedEvent buyerCreatedEvent) {

		this.buyerId = buyerCreatedEvent.getBuyerId();
		this.firstName = buyerCreatedEvent.getFirstName();
		this.lastName = buyerCreatedEvent.getLastName();
		this.address = buyerCreatedEvent.getAddress();
		this.city = buyerCreatedEvent.getCity();
		this.state = buyerCreatedEvent.getState();
		this.pin = buyerCreatedEvent.getPin();
		this.phone = buyerCreatedEvent.getPhone();
		this.email = buyerCreatedEvent.getEmail();

	}

	@EventSourcingHandler
	public void on(BuyerUpdateEvent buyerUpdateEvent) {

		this.buyerId = buyerUpdateEvent.getBuyerId();
		this.firstName = buyerUpdateEvent.getFirstName();
		this.lastName = buyerUpdateEvent.getLastName();
		this.address = buyerUpdateEvent.getAddress();
		this.city = buyerUpdateEvent.getCity();
		this.state = buyerUpdateEvent.getState();
		this.pin = buyerUpdateEvent.getPin();
		this.phone = buyerUpdateEvent.getPhone();
		this.email = buyerUpdateEvent.getEmail();

	}

}
