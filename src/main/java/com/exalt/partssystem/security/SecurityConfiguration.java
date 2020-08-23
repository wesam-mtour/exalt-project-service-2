package com.exalt.partssystem.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.session.ConcurrentSessionFilter;

/*
The @EnableWebSecurity annotation is important if we disable the default security configuration.
the application will fail to start. The annotation is only optional if we're just overriding
the default behavior using a WebSecurityConfigurerAdapter.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
       // http.httpBasic().and()..antMatcher("/**").p
    }
}