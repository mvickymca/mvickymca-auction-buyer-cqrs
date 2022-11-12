package com.auction.buyer.command.api.command.event;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auction.buyer.core.repository.BuyerRepository;
import com.auction.buyer.core.repository.model.Buyer;

@Component
@ProcessingGroup("buyer")
public class BuyerEventHandler {

	@Autowired
	private BuyerRepository buyerRepository;

	@EventHandler
	public void on(BuyerCreatedEvent buyerEvent) throws Exception {
		Buyer buyer = new Buyer();
		BeanUtils.copyProperties(buyerEvent, buyer);
		buyerRepository.save(buyer);
		// throw new Exception("Exception Occurred");
	}

	@EventHandler
	public void on(BuyerUpdateEvent buyerEvent) throws Exception {
		Buyer buyer = new Buyer();
		BeanUtils.copyProperties(buyerEvent, buyer);
		buyerRepository.save(buyer);
		// throw new Exception("Exception Occurred");
	}

	@EventHandler
	public void on(BuyerDeleteEvent buyerEvent) throws Exception {

		buyerRepository.deleteById(buyerEvent.getBuyerId());
		;
	}


//    @ExceptionHandler
//    public void handle(Exception exception) throws Exception {
//        throw exception;
//    }

}
