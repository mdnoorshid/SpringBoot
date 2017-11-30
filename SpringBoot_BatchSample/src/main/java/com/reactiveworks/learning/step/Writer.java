package com.reactiveworks.learning.step;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {
	static Logger logger=Logger.getLogger(Writer.class);

	@Override
	public void write(List<? extends String> messages) throws Exception {
        for(String msg: messages){
        	logger.info("Writing the data "+msg);
        }
		
	}

}
