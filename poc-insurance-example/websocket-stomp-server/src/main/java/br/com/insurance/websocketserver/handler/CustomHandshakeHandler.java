package br.com.insurance.websocketserver.handler;


import br.com.insurance.websocketserver.model.StompPrincipal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @SneakyThrows
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            attributes.put("sessionId", session.getId());

            log.info("session>>> {}", session.getId() );
        }



        String uuid = UUID.randomUUID().toString();
        log.info("map>>> {}", uuid );
        log.info("body>>> {}", request.getBody().readAllBytes() );
        // generate user name by UUID
        return new StompPrincipal(uuid);
    }

    @EventListener
    private String handleSessionConnected(SessionConnectEvent event) {
        log.info("event>>> {}", SimpAttributesContextHolder.currentAttributes().getSessionId() );
        return SimpAttributesContextHolder.currentAttributes().getSessionId();
    }
}