package com.reactiveworks.learning.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.reactiveworks.learning.entity.UserInfo;

public interface IUserService {
	@Secured({"ROLE_ADMIN"})
	public UserInfo createUser(UserInfo user);
	@Secured({"ROLE_ADMIN"})
	public List<UserInfo> getAllUsers();
	@Secured({"ROLE_ADMIN"})
	public void deleteUser(Integer id);
	@Secured({"ROLE_ADMIN"})
	public UserInfo getUserById(Integer id);
	@Secured({"ROLE_ADMIN"})
	public UserInfo updateUser(UserInfo userInfo,Integer id);
}
