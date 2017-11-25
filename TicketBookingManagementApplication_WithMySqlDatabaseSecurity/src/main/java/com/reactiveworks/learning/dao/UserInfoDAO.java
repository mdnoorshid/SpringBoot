package com.reactiveworks.learning.dao;
import org.springframework.data.repository.CrudRepository;
import com.reactiveworks.learning.entity.UserInfo;

/**
 * Dao layer for UserInfo
 * @author Md Noorshid
 *
 */
public interface UserInfoDAO extends CrudRepository<UserInfo, Integer> {
	public UserInfo findByUserName(String userName);

}
