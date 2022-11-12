package com.auction.buyer.command.api.command.event;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auction.buyer.command.api.command.CreateBidCommand;
import com.auction.buyer.core.repository.BidRepository;
import com.auction.buyer.core.repository.model.Bid;

@Component
@ProcessingGroup("bid")
public class BidEventHandler {

	@Autowired
	BidRepository bidRepository;

	@EventHandler
	public void on(CreateBidCommand createBidCommand) throws Exception {
		Bid bid = new Bid();
		BeanUtils.copyProperties(createBidCommand, bid);
		bidRepository.save(bid);
		// throw new Exception("Exception Occurred");
	}

}
