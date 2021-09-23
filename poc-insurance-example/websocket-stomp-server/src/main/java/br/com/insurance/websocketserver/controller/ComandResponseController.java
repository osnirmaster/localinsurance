package br.com.insurance.websocketserver.controller;

import br.com.insurance.websocketserver.Greeting;
import br.com.insurance.websocketserver.HelloMessage;
import br.com.insurance.websocketserver.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Slf4j
@Controller
public class ComandResponseController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/quota/response")
    public void sendSpecific(Message msg, @Header("simpSessionId") String sessionId, Principal principal) throws Exception {
        String out = msg.getText();
        log.info("Session: {} Para: {} Text: {}", sessionId, msg.getTo(), principal.getName());
        System.out.println("testes");
        simpMessagingTemplate.convertAndSendToUser(msg.getTo() , "/queue/response/completed"
                , out);
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                .create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();

    }
}
