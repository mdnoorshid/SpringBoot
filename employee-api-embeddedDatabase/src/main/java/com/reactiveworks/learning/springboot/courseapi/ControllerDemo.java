package com.reactiveworks.learning.springboot.courseapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDemo {
	
	@RequestMapping("/hello")
	public String getHello(){
		return "Hello";
	}
	
	@RequestMapping("/")
   public String greetMessage(){
	   return "WELCOME";
   }
}
