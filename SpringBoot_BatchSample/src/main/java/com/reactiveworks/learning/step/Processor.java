package com.reactiveworks.learning.step;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

/**
 * Defining Processor class implementing the spring batch ItemProcessor interface
 * @author Md Noorshid
 *
 */
public class Processor implements ItemProcessor<String,String> {
	static Logger logger=Logger.getLogger(Processor.class);

	@Override
	public String process(String data) throws Exception {
		logger.info("=====> data::: ====> "+data);;
		return data.toUpperCase();
	}

}
