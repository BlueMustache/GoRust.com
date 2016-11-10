package com.r3sist3nt.GoRust.Scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class ServerScanTasker{
	
	@Autowired
	MasterServerUpdate masterUpdate;

	@Autowired
	ServerDataUpdate dataUpdate;

	/**
	 * Server Scan Task
	 * -> Rescan all 10 min
	 */

	@Scheduled(fixedDelay = 600000)
	public void scanForNewServer(){
		System.out.println("Start Server scan...");
		masterUpdate.update();

	}

	/**
	 * Server Query Task
	 * -> Query all Server. Pause for 5 min after complete.
	 */

	@Scheduled(fixedDelay = 300000)
	public void serverQuery(){
		System.out.println("Start Data scan...");
		dataUpdate.update();
	}

	@Scheduled(fixedDelay = 10)
	public void consoleTest(){
		Scanner in = new Scanner(System.in);
		String b = in.nextLine();
		if(b.startsWith("look")){
			String[] split = b.split(" ");
			if(split.length==2){
				String ip = split[1].split(":")[0];
				String port = split[1].split(":")[1];
				System.out.println("Looking for: "+ip+":"+port);

			}else{
				System.out.println("Usage: look <ip:port>");
			}
		}
	}

	/**
	 * Setting ThreadPool size to 2
	 * -> needed to deal with both scan and query tasks at the same time.
	 */
	@Bean()
	public ThreadPoolTaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(3);
		return  taskScheduler;
	}

}
