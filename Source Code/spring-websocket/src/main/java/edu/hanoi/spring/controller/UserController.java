package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @MessageMapping("/user")
    @SendTo("/topic/chat")
    public Message add(User user) {
        System.out.println("User: " + user.getUsername() + " - email: " + user.getEmail());

        String status = userDAO.insert(user);
        System.out.println("Status: " + status);
        return new Message("Create new " + user.getUsername() + " successful!");
    }

    @MessageMapping("/user/{username}")
    @SendTo("/topic/chat")
    public Message update(User user, @DestinationVariable String username) {
        System.out.println("User name: " + username);
        System.out.println("User: " + user.getUsername() + " - email: " + user.getEmail());

        userDAO.update(user);
        return new Message("Update user: " + user.getUsername() + " successful!");
    }

    @MessageMapping("/user/delete/{username}")
    @SendTo("/topic/chat")
    public Message deleteUser(@DestinationVariable String username) {
        System.out.println("username: " + username);
        userDAO.delete(username);
        return new Message("Delte successful!");
    }
}
