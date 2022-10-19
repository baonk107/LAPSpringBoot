package edu.hanoi.service.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestServiceController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private GroupDAO groupDAO;

    //List All Users
    @RequestMapping(value = "/list/users")
//    @PreAuthorize("hasRole('ADMIN')") //Check Role ADMIN trước khi truy cập
    @PreAuthorize("hasRole('USER')")
//    @PostFilter("filterObject.username == 'username-random15'") //Filter with username
    @PostFilter("hasPermission(filterObject, 'read')")
    public List<User> listUser() {
        //Check ROLE ADMIN -- Create HttpServletRequest for parameter
//        if (!request.isUserInRole("ROLE_ADMIN")) {
//            throw new RuntimeException("Access Denied!");
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("\n ==========> " + auth.getAuthorities()); //Return Role
        return userDAO.list();
    }

    //List All Groups
    @RequestMapping(value = "/list/groups")
    public Group[] listGroups() {
        return groupDAO.list().toArray(new Group[0]);
    }

    //Add user
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        return userDAO.insert(user);
    }

    //Get User
    @RequestMapping(value = "/get/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name) {
        return userDAO.getUser(name);
    }

    //Delete User By User Name
    @RequestMapping(value = "/delete/user/{name}", method = RequestMethod.GET)
    public void delUser(@PathVariable String name) {
        userDAO.delete(name);
    }

    //Update User By User Name
    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userDAO.update(user);
    }


}
