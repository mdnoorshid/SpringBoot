package com.reactiveworks.learning.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job processJob;

	@RequestMapping("/invokejob")
	public String handle() {

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		try {
			jobLauncher.run(processJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
		} catch (JobRestartException e) {
		} catch (JobInstanceAlreadyCompleteException e) {
		} catch (JobParametersInvalidException e) {
		}
		return "Batch Job has been invoked";
	}

}
