package com.r3sist3nt.GoRust.Scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;


@Component
public class ServerScanTasker{
	



	@Autowired
	MasterServerUpdate masterUpdate;

	@Autowired
	ServerDataUpdate dataUpdate;

	/**
	 * Server Scan Task	 * Rescan all 5 min
	 */
	@Bean()
	public ThreadPoolTaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(2);
		return  taskScheduler;
	}

	//@Scheduled(fixedDelay = 300000)
	public void scanForNewServer(){
		System.out.println("Start Server scan...");
		masterUpdate.update();

	}

	@Scheduled(fixedDelay = 180000)
	public void serverQueryLoop(){
		System.out.println("Start Data scan...");
		dataUpdate.update();
	}


}
