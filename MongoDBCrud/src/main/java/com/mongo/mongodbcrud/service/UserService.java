package com.mongo.mongodbcrud.service;

import com.mongo.mongodbcrud.model.User;
import com.mongo.mongodbcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
     return userRepository.findAll();
    }

    public Optional<User> getUsersById(long id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
       return userRepository.save(user);
    }

}
