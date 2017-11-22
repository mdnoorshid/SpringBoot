package com.reactiveworks.learning.app.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorConttroller implements ErrorController {
     static final String PATH="/error";
     
     @RequestMapping(value=PATH , method=RequestMethod.GET)
     public String defualtErrorHandling(){
    	return "Resource is not available!!!!!!!" ;
     }
	@Override
	public String getErrorPath() {
		return PATH;
	}

}
