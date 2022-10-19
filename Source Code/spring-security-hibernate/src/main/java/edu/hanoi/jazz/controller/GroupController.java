package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    private final static Logger LOGGER = Logger.getLogger(GroupController.class);

    @Autowired
    private GroupDAO groupDAO;

    //Add Method
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    ModelAndView addForm() {
        return new ModelAndView("group.form", "command", new Group());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ModelAndView addNew(@Valid @ModelAttribute("command") Group group, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("group.form", "command", group);
            mv.addObject("errors", result);
            return mv;
        }
        if (group.getId() > 0) {
            groupDAO.update(group);
            LOGGER.info("Update new group ------> " + group.getName());
        } else {
            groupDAO.insert(group);
            LOGGER.info("Add new group ------> " + group.getName());
        }

        return new ModelAndView("redirect:/group/list");
    }

    //Display List
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "q", required = false)String pattern) {
        ModelAndView mv = new ModelAndView("group.list");
        mv.addObject("groups", groupDAO.list(pattern));
        return mv;
    }

    //Delete
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        System.out.println(id);
        if (id == null) return new ModelAndView("redirect:/group/list");
        groupDAO.delete(id);
        return new ModelAndView("redirect:/group/list");
    }

    //Update
    @RequestMapping(value = "/update")
    public ModelAndView updateForm(@RequestParam(value = "id", required = true) Integer id) {
        Group group = groupDAO.getGroup(id);
        System.out.println(group);
        if (group == null) return new ModelAndView("redirect:/group/list");
        return new ModelAndView("group.form", "command", group);
    }
}
