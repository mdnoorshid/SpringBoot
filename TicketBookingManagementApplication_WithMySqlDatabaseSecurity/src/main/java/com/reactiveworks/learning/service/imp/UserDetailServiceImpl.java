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
/**
 * This is user detail class which implements userDetailService 
 * This class is meant to load the user by user name
 * @author Niyamat
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserInfoDAO userInfoDAO;
   
	/**
	 * This method is to load the user by username
	 * It will create User based on user name,password and role assigned
	 * Then it will put in UserDetails and return the same
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo userInfo=userInfoDAO.findByUserName(userName);
		GrantedAuthority authority=new SimpleGrantedAuthority(userInfo.getRoleAssigned());
		
		User user=new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
		
		UserDetails userDetails=(UserDetails)user;
		
		return userDetails;
	}

}
