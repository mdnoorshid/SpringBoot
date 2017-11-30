package com.reactiveworks.learning.listner;
/**
 * Defining the Listener class that is executed when the job is finished
 */
import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListner extends JobExecutionListenerSupport {
	static Logger logger= Logger.getLogger(JobCompletionListner.class);
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus()== BatchStatus.COMPLETED){
			logger.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
