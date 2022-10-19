package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserDAO userDAO;

    //Get Group by Id
    @RequestMapping(value = "/users")
    public List<User> getUser() {
        return userDAO.list();
    }

    @RequestMapping(value = "/user/{username}")
    public User getUser(@PathVariable String username) {
        return userDAO.getUser(username);
    }
}
