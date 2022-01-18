package com.godzilla5hrimp.url.shortener.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.httpBasic().disable();
        security.csrf().disable();
    }

}
