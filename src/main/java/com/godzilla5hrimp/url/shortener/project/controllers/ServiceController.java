package com.godzilla5hrimp.url.shortener.project.controllers;

import com.godzilla5hrimp.url.shortener.project.service.UrlProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling business-logic of an application.
 */
@Controller
public class ServiceController {

    @Autowired
    UrlProcessingService urlProcessingService;

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

}
