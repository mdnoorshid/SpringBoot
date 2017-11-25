package com.reactiveworks.learning.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reactiveworks.learning.entity.UserInfo;
import com.reactiveworks.learning.service.imp.UserServiceImpl;

/**
 * Contoller class for UserInfo
 * Base URL for this Class is admin basically to indicate that
 * these URLs will be accessed by ADMIN level only
 * @author Md Noorshid
 *
 */
@RestController
@RequestMapping(value="/admin")
public class UserController {

@Autowired	
UserServiceImpl userService;

/**
 * URL access to create the User	
 * @param user
 * @return
 */
@PostMapping(value="/createuser")	
public UserInfo createUser(@RequestBody UserInfo user){
	return userService.createUser(user);
}

/**
 * URL access to see all Users
 */
@GetMapping(value="/allusers")
public List<UserInfo> getAllUsers(){
	return userService.getAllUsers();
}

@DeleteMapping(value="/deletuser/{id}")
public String deleteUser(@PathVariable("id") Integer id){
	String userName=getUserNameById(id);
	userService.deleteUser(id);
	
	return "User having id "+id+" and by name "+userName+ " deleted Succesfully";
}

@PutMapping(value="/updateuser/{id}")
public UserInfo updateUser(@PathVariable("id") Integer id,@RequestBody UserInfo userInfo){
	BCryptPasswordEncoder byBCryptPasswordEncoder=new BCryptPasswordEncoder();
     String userName = userInfo.getUserName();
	 String password = userInfo.getPassword();
	 password=byBCryptPasswordEncoder.encode(password);
	 String roleAssigned = userInfo.getRoleAssigned(); 
	 userInfo=new UserInfo(userName, password, roleAssigned);
	 userInfo.setUserId(id);
	 return userService.updateUser(userInfo, id);
	
}

public String getUserNameById(Integer id){
   String  userName=userService.getUserById(id).getUserName();
   return userName;
}
	
}
