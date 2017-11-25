package com.reactiveworks.learning.userinfo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.reactiveworks.learning.service.imp.UserDetailServiceImpl;
import com.reactiveworks.learning.service.imp.UserServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(99)
public class UserInfoSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;
	@Autowired
	UserInfoAuthentificationEntryPoint authentificationEntryPoint;

	/**
	 * Method to assign the role using URL patterns
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().httpBasic()
				.realmName("UserInfo Security application Realm").authenticationEntryPoint(authentificationEntryPoint);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		authenticationManagerBuilder.userDetailsService(userDetailServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}
}
