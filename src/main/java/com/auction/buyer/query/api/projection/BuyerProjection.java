package com.auction.buyer.query.api.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auction.buyer.core.model.BuyerAPIResponse;
import com.auction.buyer.core.repository.BuyerRepository;
import com.auction.buyer.core.repository.model.Buyer;
import com.auction.buyer.query.api.queries.GetBuyersQuery;

@Component
public class BuyerProjection {

	@Autowired
	private BuyerRepository buyerRepository;

	@QueryHandler
	public List<BuyerAPIResponse> handle(GetBuyersQuery getBuyerQuery) {
		List<Buyer> buyers = buyerRepository.findAll();

		List<BuyerAPIResponse> productRestModels = buyers.stream()
				.map(buyer -> BuyerAPIResponse.builder().firstName(buyer.getFirstName()).lastName(buyer.getLastName())
						.address(buyer.getAddress()).buyerId(buyer.getBuyerId()).email(buyer.getEmail())
						.phone(buyer.getPhone()).pin(buyer.getPin()).state(buyer.getState()).city(buyer.getCity())

						.build())
				.collect(Collectors.toList());

		return productRestModels;
	}

}
