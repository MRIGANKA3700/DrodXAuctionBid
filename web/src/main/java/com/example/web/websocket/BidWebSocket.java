//package com.example.web.websocket;
//
//
//import jakarta.websocket.OnClose;
//import jakarta.websocket.OnMessage;
//import jakarta.websocket.OnOpen;
//import jakarta.websocket.Session;
//import jakarta.websocket.server.ServerEndpoint;
//
//
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@ServerEndpoint("/bidSocket")
//public class BidWebSocket {
//    private static final Map<String, Set<Session>> productSessions = new ConcurrentHashMap<>();
//
//    private Session session;
//    private String productId;
//
//    @OnOpen
//    public void onOpen(Session session) {
//        this.session = session;
//        System.out.println("WebSocket opened: " + session.getId());
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) throws IOException {
//        String[] parts = message.split(":");
//        if (parts.length == 2) {
//            productId = parts[0];
//            String bidAmount = parts[1];
//
//            productSessions.putIfAbsent(productId, new HashSet<>());
//            productSessions.get(productId).add(session);
//
//            for (Session s : productSessions.get(productId)) {
//                if (s.isOpen()) {
//                    s.getBasicRemote().sendText(bidAmount);
//                }
//            }
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        System.out.println("WebSocket closed: " + session.getId());
//        if (productId != null && productSessions.containsKey(productId)) {
//            productSessions.get(productId).remove(session);
//        }
//    }
//}
