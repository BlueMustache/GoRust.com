package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.ScanLib.Masterserver;
import com.r3sist3nt.GoRust.ScanLib.model.Server;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Masterserver mServer = new Masterserver();
        LinkedList<Server> sList = mServer.requestServerList();
        System.out.println("Updating Database Records...");
        int updates=0;
        int skipped=0;

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
                updates++;
            }else{
                skipped++;
                sim = queryList.get(0);
                if(!sim.getActive()){
                    sim.setActive(true);
                    indexRepo.save(sim);
                    updates++;
                }

            }
            System.out.print("\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r");
            System.out.print("Server: "+updates +" Updated: "+skipped);
        }
        System.out.println();
        System.out.println("Update Finished! Updated "+updates+" records. Leaving "+skipped+" existing records untouched.");
    }
}
