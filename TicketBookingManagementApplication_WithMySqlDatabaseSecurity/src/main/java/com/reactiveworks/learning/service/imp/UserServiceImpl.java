package com.reactiveworks.learning.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.UserInfoDAO;
import com.reactiveworks.learning.entity.UserInfo;
import com.reactiveworks.learning.service.IUserService;

/**
 * Service Layer for User
 * @author Niyamat
 *
 */
@Service
public class UserServiceImpl implements IUserService {
    
	@Autowired
    UserInfoDAO userDao;	
	public UserInfo createUser(UserInfo user) {
		BCryptPasswordEncoder byBCryptPasswordEncoder=new BCryptPasswordEncoder();
		String userName =user.getUserName();
		String password =user.getPassword();
		password=byBCryptPasswordEncoder.encode(password);
		String roleAssigned =user.getRoleAssigned();
		user=new UserInfo(userName, password, roleAssigned);
		return userDao.save(user);
	}

	public List<UserInfo> getAllUsers() {
		List<UserInfo> allUserList=new ArrayList<UserInfo>();
		userDao.findAll().forEach(allUserList::add);
		return allUserList;
	}

	public void deleteUser(Integer id) {
		userDao.delete(id);
	}
	public UserInfo getUserById(Integer id){
		return userDao.findOne(id);
	}

	@Override
	public UserInfo updateUser(UserInfo userInfo,Integer id) {
		return userDao.save(userInfo);
	}

}
