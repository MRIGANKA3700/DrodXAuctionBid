package com.example.ee.core.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Validate {

    public static List<AutoBid> sortBidConfigs(List<AutoBid> autoBidders) {
        return autoBidders.stream()
                .sorted(Comparator
                        .comparing(AutoBid::getRegisteredAt)
                        .thenComparing(AutoBid::getUserId))
                .collect(Collectors.toList());
    }
}
