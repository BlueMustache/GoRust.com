package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.ScanLib.Masterserver;
import com.r3sist3nt.GoRust.ScanLib.model.Masterserver_WebAPI;
import com.r3sist3nt.GoRust.ScanLib.model.Server;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Julian on 01.11.2016.
 * This task retrieves a Server List from the Steam Master Server and
 * adds them to the server_index table in the database.
 * Old records, marked as inactive will be reactivated again.
 */
@Component
public class MasterServerUpdate {

    @Autowired
    ServerDataRepository dataRepo;
    @Autowired
    ServerIndexRepository indexRepo;
    private static final Logger log = LoggerFactory.getLogger(ServerScanTasker.class);

    public void update(){
        log.info("[MasterServerUpdate]Updating Database from Masterserver...");
        Masterserver_WebAPI mServer = new Masterserver_WebAPI();
        LinkedList<Server> sList = mServer.requestServerList();
        log.info("[MasterServerUpdate]Retrieved "+sList.size()+" Servers from the Steam Web Api. Staring processing...");
        int newserver=0;
        int updated=0;

        for(Server s : sList){
            String[] pat = s.getUri().split(":");
            ServerIndexModel sim;
            List<ServerIndexModel> queryList = indexRepo.findByIpaddressAndPort(pat[0], Integer.parseInt(pat[1]));
            if(queryList.size()==0){
                sim = new ServerIndexModel();
                sim.setIpaddress(pat[0]);
                sim.setPort(Integer.parseInt(pat[1]));
                sim.setActive(true);
                indexRepo.save(sim);
                newserver++;
            }else{
                updated++;
                sim = queryList.get(0);
                if(!sim.getActive()){
                    sim.setActive(true);
                    indexRepo.save(sim);
                }

            }
            System.out.print("\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r");
            System.out.print("NewServer: "+newserver +" Updated: "+updated);
        }
        System.out.println();
        log.info("[MasterServerUpdate]Update Finished! Found "+newserver+" new Server! Updated "+updated+" existing ones.");
    }
}
