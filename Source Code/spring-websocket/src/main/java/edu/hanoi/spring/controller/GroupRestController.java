package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupRestController {

    @Autowired
    private GroupDAO groupDAO;
    //List User
    @RequestMapping(value = "/groups")
    public List<Group> list() {
        return groupDAO.list();
    }

    //Get Group by Id
    @RequestMapping(value = "/group/{id}")
    public Group getGroup(@PathVariable int id) {
        return groupDAO.getGroup(id);
    }

}
