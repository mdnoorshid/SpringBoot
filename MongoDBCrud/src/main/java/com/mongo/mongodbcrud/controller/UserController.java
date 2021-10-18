package com.mongo.mongodbcrud.controller;

import com.mongo.mongodbcrud.model.User;
import com.mongo.mongodbcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("mongo")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findallusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUsersById(id).get().toString(),HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User "+user.getUserName()+" created successfully", HttpStatus.CREATED);
    }

}
