package com.example.ee.core.websocket;


import jakarta.websocket.Session;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BidBroadcaster {
    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    public static void register(Session session) {
        sessions.add(session);
    }
    public static void unregister(Session session) {
        sessions.remove(session);
    }


    // Public method to broadcast updated bid
    public static void broadcastBidUpdate(Long productId, double amount, String username) {
        String message = productId + ":" + amount + ":" + username;
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                System.err.println("Error broadcasting: " + e.getMessage());
            }
        }
    }




}
