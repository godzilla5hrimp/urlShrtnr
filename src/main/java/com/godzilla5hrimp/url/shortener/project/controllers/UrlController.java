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
     * @return
     * @throws RuntimeException - throws RuntimeException to trigger error page.
     */
    @RequestMapping(path = "/generate-url", method = RequestMethod.POST)
    public ModelAndView generateUrl(@ModelAttribute(value = "name") String name) {
        if(urlValidator.validateURL(name)) {
            ModelAndView mav = new ModelAndView("url_info");
            String resultUrl = urlProcessingService.generateUrl(name);
            mav.addObject("generatedUrl", resultUrl);
            mav.addObject("timesVisited", urlProcessingService.getTimesWasVisited(resultUrl));
            mav.addObject("timesGenerated", urlProcessingService.getTimesWasShortened(resultUrl));
            return mav;
        } else {
            throw new RuntimeException("Please enter a valid URL!");
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


}
