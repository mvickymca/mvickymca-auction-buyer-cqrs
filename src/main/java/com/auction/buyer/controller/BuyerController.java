package com.auction.buyer.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.buyer.command.api.command.CreateBidCommand;
import com.auction.buyer.command.api.command.CreateBuyerCommand;
import com.auction.buyer.command.api.command.DeleteBuyerCommand;
import com.auction.buyer.command.api.command.UpdateBuyerCommand;
import com.auction.buyer.core.model.BuyerAPIResponse;
import com.auction.buyer.query.api.queries.GetBuyersQuery;

@RestController
@RequestMapping("/e-auction/api/v1/buyer/")
public class BuyerController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryGateway queryGateway;

	@PostMapping("/addBuyer")
	public String addProduct(@RequestBody BuyerAPIResponse buyer) {
		CreateBuyerCommand createBuyerCommand = CreateBuyerCommand.builder().buyerId(UUID.randomUUID().toString())
				.firstName(buyer.getFirstName()).lastName(buyer.getLastName()).address(buyer.getAddress())
				.city(buyer.getCity()).email(buyer.getEmail()).pin(buyer.getPin()).state(buyer.getState())
				.phone(buyer.getPhone()).email(buyer.getEmail()).build();

		String result = commandGateway.sendAndWait(createBuyerCommand);

		return result;
	}

	@PostMapping("/place-bid")
	public String placeBid(@RequestBody BuyerAPIResponse buyer) {
		CreateBuyerCommand createBuyerCommand = CreateBuyerCommand.builder().buyerId(UUID.randomUUID().toString())
				.firstName(buyer.getFirstName()).lastName(buyer.getLastName()).address(buyer.getAddress())
				.city(buyer.getCity()).email(buyer.getEmail()).pin(buyer.getPin()).state(buyer.getState())
				.phone(buyer.getPhone()).email(buyer.getEmail()).build();

		CreateBidCommand createBidCommand = CreateBidCommand.builder().bidId(UUID.randomUUID().toString())
				.buyerId(createBuyerCommand.getBuyerId()).bidAmount(buyer.getBidAmount()).productId(buyer.getProductId()).build();

		commandGateway.sendAndWait(createBuyerCommand);
		commandGateway.sendAndWait(createBidCommand);

		return commandGateway.sendAndWait(createBidCommand);
	}

	@PutMapping("/updateBuyer")
	public BuyerAPIResponse updateProduct(@RequestBody BuyerAPIResponse buyer) {
		UpdateBuyerCommand updateBuyerCommand = UpdateBuyerCommand.builder().buyerId(buyer.getBuyerId())
				.firstName(buyer.getFirstName()).lastName(buyer.getLastName()).address(buyer.getAddress())
				.city(buyer.getCity()).email(buyer.getEmail()).pin(buyer.getPin()).state(buyer.getState())
				.phone(buyer.getPhone()).email(buyer.getEmail()).build();

		commandGateway.sendAndWait(updateBuyerCommand);

		return buyer;
	}

	@PutMapping("/deleteBuyer")
	public String deleteProduct(@RequestBody BuyerAPIResponse buyer) {
		DeleteBuyerCommand deletedBuyerCommand = DeleteBuyerCommand.builder().buyerId(buyer.getBuyerId()).build();

		commandGateway.sendAndWait(deletedBuyerCommand);

		return "success";
	}

	@GetMapping("/getAllBuyerDetails")
	public List<BuyerAPIResponse> getAllBuyers() {

		GetBuyersQuery getBuyersQuery = new GetBuyersQuery();

		List<BuyerAPIResponse> productRestModels = queryGateway
				.query(getBuyersQuery, ResponseTypes.multipleInstancesOf(BuyerAPIResponse.class)).join();
		return productRestModels;

	}

}
