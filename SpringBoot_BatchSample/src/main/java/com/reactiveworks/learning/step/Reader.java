 package com.reactiveworks.learning.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
  * Defining the Reader class implementing the spring batch ItemReader interface
  * @author Md Noorshid
  *
  */
public class Reader implements ItemReader<String> {
	
	private String[] messages={ "javainuse.com",
			"Welcome to Spring Batch Example",
			"We use MySql Database for this example" };
	private int count=0;
	

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(count < messages.length){
			return messages[count++];
		}else{
			count=0;
		}
		return null;
	}

}
