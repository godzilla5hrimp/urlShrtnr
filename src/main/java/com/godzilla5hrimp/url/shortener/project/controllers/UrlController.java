package com.godzilla5hrimp.url.shortener.project.controllers;

import com.godzilla5hrimp.url.shortener.project.service.UrlProcessingService;
import com.godzilla5hrimp.url.shortener.project.utils.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for URL specific handling.
 */
@Controller
public class UrlController {

    @Autowired
    UrlProcessingService urlProcessingService;

    @Autowired
    UrlValidator urlValidator;

    /**
     * Generate shortened URL for user and show him info page.
     * @param name - original URL.
     * @param userId - userId that generated URL.
     * @return
     */
    @RequestMapping(path = "/generate-url", method = RequestMethod.POST)
    public ModelAndView generateUrl(@ModelAttribute(value = "name") String name, @RequestParam(value = "userId") Long userId) {
        //TODO: Need to configure using JWT for logged in users
        if(urlValidator.validateURL(name)) {
            ModelAndView mav = new ModelAndView("generated");
            if(userId == null)
                mav.addObject("urlGen", urlProcessingService.generateUrl(name, null));
            else
                mav.addObject("urlGen", urlProcessingService.generateUrl(name, userId));
            return mav;
        } else {
            throw new RuntimeException("Please enter a valid URL! Current input:" + name);
        }
    }


    /**
     * Redirect user with specific shorten URL to original one.
     * @param url - shortened URL.
     * @return
     */
    @GetMapping(value = "/{url}")
    public String getRedirected(@PathVariable String url) {
        String originalUrl = urlProcessingService.getOriginalUrl(url);
        urlProcessingService.addVisited(url);
        return "redirect:".concat(originalUrl);
    }

    /**
     * Give user statistic on generated link.
     * @param url - shortened URL.
     * @return
     */
    @GetMapping(value = "/{url}/info")
    public ModelAndView getUrlInfo(@PathVariable String url) {
        ModelAndView mav = new ModelAndView("url_info");
        mav.addObject("generatedUrl", url);
        mav.addObject("timesVisited", urlProcessingService.getTimesWasVisited(url));
        mav.addObject("timesGenerated", urlProcessingService.getTimesWasShortened(url));
        return mav;
    }

}
