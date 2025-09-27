package com.example.ee.ejb.bean;

import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Bid;
import com.example.ee.core.model.Validate;
import com.example.ee.core.websocket.BidBroadcaster;

import com.example.ee.ejb.remote.BiddingService;
import com.example.ee.ejb.remote.ProductService;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.List;

/**
 * MDB that listens on jms/BidTopic.
 * When a new bid message arrives, it invokes the WebSocket broadcaster.
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName  = "destinationLookup", propertyValue = "jms/MyTopic"),
                @ActivationConfigProperty(propertyName  = "destinationType",   propertyValue = "jakarta.jms.Topic")
        }
)
public class BidReceiverBean implements MessageListener {

    @EJB
    private ProductService productService;

    @EJB
    private BiddingService biddingService;
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String payload = ((TextMessage) message).getText();
                String[] parts = payload.split(":");
                if (parts.length == 3) {
                    Long productId = Long.parseLong(parts[0]);
                    double amount = Double.parseDouble(parts[1]);
                    String username = parts[2];

                    BidBroadcaster.broadcastBidUpdate(productId, amount, username);

                    List<AutoBid> autoBidders = productService.getAutoBiddersConfigerForProducts(productId.intValue());

                    for (AutoBid config : Validate.sortBidConfigs(autoBidders)) {

                        double nextBid = amount + 10;

                        if (nextBid <= config.getMaxBid()) {
                            Bid bid = new Bid();
                            bid.setProductId(productId);
                            bid.setAmount(nextBid);
                            bid.setUsername("AutoBid_User_" + config.getUserId());
                            bid.setTimestamp(new java.util.Date());

                            biddingService.addBid(bid);

                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

