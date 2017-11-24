package com.reactiveworks.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Config class
 * @author Md Noorshid
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().authorizeRequests().antMatchers("/api/tickets/**").hasAnyRole("admin","user").and().formLogin();
		http.csrf().disable().authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("admin").and().formLogin();*/
		
		//merging both the configuration
		http.csrf().disable().authorizeRequests().antMatchers("/api/tickets/**").hasAnyRole("admin","user").and().authorizeRequests()
		.antMatchers("/api/admin/**").hasAnyRole("admin").and().formLogin();
		
		
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication().withUser("user").password("user").roles("user");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("user","admin");*/
		//Merging both authentication builder 
		auth.inMemoryAuthentication().withUser("noorshid").password("noorshid").roles("user").and().
		withUser("md").password("md").roles("user","admin");
	}

}
