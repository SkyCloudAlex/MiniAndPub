package com.eroad.project.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Async
public class ScheduledService {

	@Scheduled(cron = "0 0 0 * * ?")
	public void sche1() {
		
	}
	
}
