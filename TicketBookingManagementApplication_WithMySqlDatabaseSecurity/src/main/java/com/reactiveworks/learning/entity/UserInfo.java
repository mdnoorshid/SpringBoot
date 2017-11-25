package com.reactiveworks.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the entity class for  User Creation
 * @author Md Noorshid
 *
 */
@Entity
@Table(name="User_Details",schema="TicketSecurityDB")
public class UserInfo {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	@Column(name="user_name" ,length=30,nullable=false)
	private String  userName;
	@Column(name="password",nullable=false)
	private String  password;
	@Column(name="role_assigned",length=10,nullable=false)
	private String roleAssigned;
	public UserInfo(String userName, String password, String roleAssigned) {
		this.userName = userName;
		this.password = password;
		this.roleAssigned = roleAssigned;
	}
	
	public UserInfo() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleAssigned() {
		return roleAssigned;
	}

	public void setRoleAssigned(String roleAssigned) {
		this.roleAssigned = roleAssigned;
	}
	
	
}
