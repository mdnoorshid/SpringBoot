package com.reactiveworks.learning.ticket.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.reactiveworks.learning.service.imp.TicketDetailServiceImpl;
import com.reactiveworks.learning.service.imp.UserDetailServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class TicketSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	TicketDetailServiceImpl ticketDetailServiceImpl;
    @Autowired
    TicketAuthenticationEntryPoint ticketAuthenticationEntryPoint;
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
	 http.csrf().disable().authorizeRequests().antMatchers("/user/**").hasAnyRole("ADMIN","USER").and()
	 .httpBasic().realmName("Ticket Security application Realm").authenticationEntryPoint(ticketAuthenticationEntryPoint);
	}
	
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
    	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    	authenticationManagerBuilder.userDetailsService(ticketDetailServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }
    
	
}
