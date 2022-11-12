package com.auction.buyer.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.buyer.core.repository.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, String> {

}
