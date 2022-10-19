package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    @Autowired
    private GroupDAO groupDAO;

    @MessageMapping("/group/{id}")
    @SendTo("/topic/chat")
    public Message update(Group group, @DestinationVariable int id) {
        System.out.println("User name: " + id);
        System.out.println("User: " + group.getName());

        groupDAO.update(group);
        return new Message("Update Group: " + group.getName() + " successful!");
    }

    @MessageMapping("/group/delete/{id}")
    @SendTo("/topic/chat")
    public Message deleteUser(@DestinationVariable int id) {
        System.out.println("Delete Successful " + id);
        return new Message("Delete successful!" + id);
    }
}
