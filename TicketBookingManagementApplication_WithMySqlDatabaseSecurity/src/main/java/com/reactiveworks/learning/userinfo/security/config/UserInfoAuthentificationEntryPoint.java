package com.reactiveworks.learning.userinfo.security.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * Entry point of Authentication for UserInfo
 * @author Md Noorshid
 *
 */
@Component
public class UserInfoAuthentificationEntryPoint extends BasicAuthenticationEntryPoint {
	
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException{
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
	}
public void afterPropertiesSet(){
	setRealmName("UserInfo Security application Realm");
}
}
