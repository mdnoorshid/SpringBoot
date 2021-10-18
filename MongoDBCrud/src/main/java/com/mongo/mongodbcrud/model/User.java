package com.mongo.mongodbcrud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private long empId;
    private String userName;
    private String emailId;
    private long phoneNumber;

    public User() {
        super();
    }

    public User(long empId, String userName, String emailId, long phoneNumber) {
        super();
        this.empId = empId;
        this.userName = userName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "empId=" + empId +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
