package com.spike.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

	@Autowired
	private StubService stubService;

	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		System.out.println(stubService.hello());
	}
}

