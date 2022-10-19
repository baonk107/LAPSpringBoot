package edu.hanoi.spring.controller;

import edu.hanoi.spring.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping(value = "/message")
    @SendTo("/topic/chat")
    public Message say(Message message, Principal principal) throws InterruptedException {
        System.out.println("From " + principal.getName() + ": " + message.getContent());
        Thread.sleep(100);

        return new Message(principal.getName() + ": " + message.getContent() + "!");
    }
}
