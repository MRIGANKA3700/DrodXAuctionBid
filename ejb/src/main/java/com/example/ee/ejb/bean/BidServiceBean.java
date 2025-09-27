package com.example.ee.ejb.bean;



import com.example.ee.core.model.AutoBid;
import com.example.ee.core.model.Bid;
import com.example.ee.ejb.remote.BiddingService;
import jakarta.annotation.Resource;
import jakarta.ejb.Remote;
import jakarta.ejb.Singleton;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Remote(BiddingService.class)
public class BidServiceBean implements BiddingService {

    @Resource(lookup = "jms/MyTopic")
    private Topic bidTopic;

    @Resource(lookup = "jms/MyConnectionFactory")
    private ConnectionFactory connectionFactory;

    private final Map<Long, List<Bid>> bidMap = new ConcurrentHashMap<>();

    @Override
    public void addBid(Bid bid) {

        bidMap.computeIfAbsent(bid.getProductId(),
                        k -> Collections.synchronizedList(new ArrayList<>()))
                .add(bid);

        try (JMSContext jmsContext = connectionFactory.createContext()) {

            String text = bid.getProductId() + ":" + bid.getAmount() + ":" + bid.getUsername();
            jmsContext.createProducer().send(bidTopic, text);
        }
    }

    @Override
    public List<Bid> getBidsForProduct(Long productId) {
        return bidMap.getOrDefault(productId, Collections.emptyList());
    }


    private final Map<Integer, List<AutoBid>> autoBidMap = new ConcurrentHashMap<>();

    @Override
    public void registerAutoBid(AutoBid autoBid) {
        autoBidMap.computeIfAbsent(autoBid.getProductId(),
                        k -> Collections.synchronizedList(new ArrayList<>()))
                .add(autoBid);
    }

    @Override
    public List<AutoBid> getAutoBidsForProduct(Long productId) {
        return autoBidMap.getOrDefault(productId, Collections.emptyList());
    }


}
