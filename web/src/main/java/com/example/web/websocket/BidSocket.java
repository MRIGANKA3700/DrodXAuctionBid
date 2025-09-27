package com.example.web.websocket;

import com.example.ee.core.websocket.BidBroadcaster;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;



@ServerEndpoint("/bidSocket")
public class BidSocket {

    @OnOpen
    public void onOpen(Session session) {
        BidBroadcaster.register(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        BidBroadcaster.unregister(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket Error:");
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }



}
