package com.reactiveworks.learning.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
    
	/**
	 * Maps an Object  into JSON String, using a Jackson ObjectMapper
	 * @return
	 * @throws JsonProcessingException 
	 */
	public String mapToJson(Object object) throws JsonProcessingException{
      ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
}
