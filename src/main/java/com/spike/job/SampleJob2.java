package com.spike.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob2 implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		System.out.println("bye");
	}
}
