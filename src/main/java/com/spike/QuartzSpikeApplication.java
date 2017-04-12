package com.spike;

import com.spike.config.AutowiringSpringBeanJobFactory;
import com.spike.job.SampleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.io.IOException;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SpringBootApplication
public class QuartzSpikeApplication {

	private ApplicationContext appContext;

	public QuartzSpikeApplication(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(SampleJob.class)
				.storeDurably()
				.withIdentity("Qrtz_Job_Detail")
				.withDescription("Invoke Sample Job service...")
				.build();
	}

	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job)
				.withIdentity("Qrtz_Trigger")
				.withDescription("Sample trigger")
				.withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(10))
				.build();
	}

	@Bean
	public Scheduler scheduler(Trigger trigger, JobDetail job) throws SchedulerException, IOException {
		StdSchedulerFactory factory = new StdSchedulerFactory();
		factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

		Scheduler scheduler = factory.getScheduler();
		scheduler.setJobFactory(springBeanJobFactory());
		scheduler.scheduleJob(job, trigger);

		scheduler.start();
		return scheduler;
	}

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(appContext);
		return jobFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuartzSpikeApplication.class, args);
	}
}

