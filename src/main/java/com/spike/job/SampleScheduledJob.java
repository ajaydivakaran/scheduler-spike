package com.spike.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SampleScheduledJob {

	@Scheduled(fixedDelay = 1000)
	public void hello() {
		System.out.println("hello from scheduled job");
	}
}
