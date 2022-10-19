package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/account")
public class UserController {
    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/add")
    ModelAndView addForm() {
        ModelAndView mv = new ModelAndView("user.form", "command", new User());
        mv.addObject("groups", toGroupMap(groupDAO.list(null)));
        return mv;
    }

    //Map to Group
    private Map<Integer, String> toGroupMap(List<Group> groups) {
        Map<Integer, String> map = new HashMap<>();
        groups.forEach(group -> map.put(group.getId(), group.getName()));
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("command") User user, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("user.form", "command", new User());
            mv.addObject("groups", toGroupMap(groupDAO.list(null)));
            return mv;
        }

        userDAO.insert(user);
        LOGGER.info("Add New User ===> " + user);
        return new ModelAndView("redirect:/account/add");
    }

    @RequestMapping(value = "/listAll")
    public ModelAndView listAll() {
        ModelAndView mv = new ModelAndView("user.list");
//        mv.addObject("users", userDAO.listUser()); //List All By HQL
        mv.addObject("average", userDAO.avarageAge());
//        mv.addObject("users", userDAO.page(1)); //List Paging
        mv.addObject("users", userDAO.listUserByNativeSQL()); //List All By SQL
        return mv;
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "group", required = false)Integer group) {
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users", userDAO.listFilter(group));
        return mv;
    }

    @RequestMapping(value = "/detail/{username}")
    public ModelAndView accountDetail(@PathVariable String username){
        ModelAndView mv = new ModelAndView("user.detail");
        mv.addObject("user", userDAO.getUser(username));
        return mv;
    }

    //Delete User
    @RequestMapping(value = "/delete/{username}")
    public String delete(@PathVariable String username){
        System.out.println(username);
        userDAO.deleteUser(username);
        return "redirect:/account/listAll";
    }

    //Add many to user
    @RequestMapping(value = "/add-many")
    public String addRandom(){
        userDAO.addBatch();
        return "redirect:/account/listAll";
    }
}
