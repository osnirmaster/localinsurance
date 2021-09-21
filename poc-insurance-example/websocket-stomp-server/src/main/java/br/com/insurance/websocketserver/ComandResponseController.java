package br.com.insurance.websocketserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
public class ComandResponseController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/multi/offers")
    @SendTo("/topic/offers/response")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        log.info("payload {}", message);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/secured/room")
    public void sendSpecific(Message msg,  @Header("simpSessionId") String sessionId) throws Exception {
        String out = msg.getText();
        log.info("Trace Message: {} {}", sessionId, msg.getTo());
        System.out.println("testes");
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/secured/user/queue/specific-user", out);

    }
}
