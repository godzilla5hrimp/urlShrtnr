package com.godzilla5hrimp.url.shortener.project.controllers;

import com.godzilla5hrimp.url.shortener.project.service.UrlProcessingService;
import com.godzilla5hrimp.url.shortener.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling business-logic of an application.
 */
@Controller
public class ServiceController {

    @Autowired
    UrlProcessingService urlProcessingService;

    @Autowired
    UserService userService;

    /**
     * Controller for Home Page interaction.
     * @param model
     * @return
     */
    @GetMapping(value = "/")
    public ModelAndView getMainPage(Model model) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name", "");
        mav.addObject("url", "");
        return mav;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping(value = "/register")
    public ModelAndView postRegisterNewUser(@ModelAttribute(value = "userName") String userName, @ModelAttribute(value = "password") String password) {
        ModelAndView mav = new ModelAndView("register_finished");
        userService.registerUser(userName, password);
        mav.addObject("userName",userName);
        return mav;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView postLoginUser(@ModelAttribute(value = "userName") String userName, @ModelAttribute(value = "password") String password) {
        ModelAndView mav = new ModelAndView("index");
        userService.loginUser(userName, password);
        mav.addObject("userName", userName);
        return mav;
    }

}
