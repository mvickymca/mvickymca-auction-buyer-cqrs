package com.auction.buyer.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyerAPIResponse {

	private String buyerId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String pin;
	private String phone;
	private String email;
	private String productId;
	private Integer bidAmount;

}
