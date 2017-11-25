package com.reactiveworks.learning.service.imp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.UserInfoDAO;
import com.reactiveworks.learning.entity.UserInfo;

@Service
public class TicketDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserInfoDAO userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo=userInfoDAO.findByUserName(username);
		GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(userInfo.getRoleAssigned());
		User user=new User(username, userInfo.getPassword(), Arrays.asList(grantedAuthority));
		UserDetails userDetails=(UserDetails)user;
		return userDetails;
	}
	

}
