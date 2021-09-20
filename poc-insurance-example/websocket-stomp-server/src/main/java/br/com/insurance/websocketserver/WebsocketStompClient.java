package br.com.insurance.websocketserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class WebsocketStompClient {

    @Autowired
    private WebsocketStompClient stompClient;

    StompSessionHandler sessionHandler;
}
