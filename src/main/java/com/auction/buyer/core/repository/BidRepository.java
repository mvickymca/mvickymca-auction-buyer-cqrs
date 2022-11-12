package com.auction.buyer.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.buyer.core.repository.model.Bid;

public interface BidRepository extends JpaRepository<Bid, String> {

}
