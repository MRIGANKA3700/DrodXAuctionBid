package com.example.ee.ejb.remote;

import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Bid;
import jakarta.ejb.Remote;

import java.util.List;

@Remote

public interface BiddingService {

    void addBid(Bid bid);
    List<Bid> getBidsForProduct(Long productId);


    void registerAutoBid(AutoBid autoBid);
    List<AutoBid> getAutoBidsForProduct(Long productId);
}
