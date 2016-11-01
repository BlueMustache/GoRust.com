package com.r3sist3nt.GoRust.Scan;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.r3sist3nt.GoRust.ScanLib.Masterserver;
import com.r3sist3nt.GoRust.ScanLib.model.Server;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ServerScanTasker{
	


	/**
	 * Server Scan Task	 * Rescan all 5 min
	 */
	@Autowired
	MasterServerUpdate masterUpdate;

	@Scheduled(fixedDelay = 300000)
	public void scanForNewServer(){

		masterUpdate.update();

	}

	
}
