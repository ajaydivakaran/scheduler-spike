package com.spike.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		System.out.println("Hello World!!!!");
	}
}
