package com.godzilla5hrimp.url.shortener.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

}
