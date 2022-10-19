package edu.hanoi.jazz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Hello Java Clazz");
        return mv;
    }

    @RequestMapping(value = "/nguoi-dung")
    ModelAndView forUser(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "This is protected page!");
        return mv;
    }

    @RequestMapping(value = "/dang-nhap")
    ModelAndView login(@RequestParam(value = "error", required = false)String error){
        ModelAndView mv = new ModelAndView("login");
        if(error != null) mv.addObject("error", error);
        return mv;
    }

}
