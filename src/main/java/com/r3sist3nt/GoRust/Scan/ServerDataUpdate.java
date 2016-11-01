package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.ScanLib.ServerPacket;
import com.r3sist3nt.GoRust.ScanLib.model.Rules;
import com.r3sist3nt.GoRust.ScanLib.model.Server;
import com.r3sist3nt.GoRust.ScanLib.model.ServerInfo;
import com.r3sist3nt.GoRust.database.ServerDataModel;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Created by Julian on 01.11.2016.
 */

@Component
public class ServerDataUpdate {

    public static final int  REFRESH_INTERVAL=300;

    @Autowired
    ServerDataRepository dataRepo;
    @Autowired
    ServerIndexRepository indexRepo;

    /**
     * Method Searches in database for Servers which aren't renewed within the REFRESH_INTERVAL constant
     *
     * 1. get a list of current active servers
     * 2. Pick Server and query Stats
     * 3. Check all data_hash of the picked server for changes with the query result.
     *
     */
    public void update(){
        List<ServerIndexModel> serverList = indexRepo.findByActive(true);
        System.out.println("Active: "+serverList.size());
        for(ServerIndexModel sim : serverList){
            List<ServerDataModel> dataList = dataRepo.findByServeridOrderByEntrydateDesc(sim.getId(), new PageRequest(0, 1));
            if(dataList.size()>0){

            }else{
                Server server = new Server(sim.getIpaddress(),sim.getPort());

                /**
                 * Obtaining Server Info and if successful the Rules
                 * .getServerInfo() and .getRules() return null on packet timeout.
                 */
                ServerInfo sInfo = server.getServerInfo();
                if(sInfo!=null){
                    Rules r = server.getRules();
                    if(r!=null){
                        ServerDataModel sdm = new ServerDataModel();
                        sdm.setData_lastscan(new Date(System.currentTimeMillis()));
                        sdm.setServer_build(r.getRule("build"));
                        sdm.setEntrydate(new Date(System.currentTimeMillis()));
                        sdm.setServer_descr1(r.getRule("description_00"));
                        sdm.setServer_descr2(r.getRule("description_01"));
                        sdm.setServer_descr3(r.getRule("description_02"));
                        sdm.setServer_descr4(r.getRule("description_03"));
                        sdm.setServer_logo(r.getRule("headerimage"));
                        sdm.setServer_name(sInfo.getName());
                        try{
                            sdm.setServer_mapsize(Integer.parseInt(r.getRule("world.size")));
                            sdm.setServer_seed(Integer.parseInt(r.getRule("world.seed")));
                        }catch(NumberFormatException e){

                        }

                        sdm.setServer_hash(r.getRule("hash"));
                        sdm.setServerID(sim.getId());
                        sdm.setServer_maxplayer(sInfo.getMaxplayer());
                        dataRepo.save(sdm);
                    }

                }else{
                    /**
                     * Mark Server as inactive
                     */
                    System.out.println("Server Inactive: "+sim.getIpaddress()+":"+sim.getPort());
                    sim.setActive(false);
                    indexRepo.save(sim);
                }





            }


        }


        //List<ServerDataModel> list = dataRepo.lessThenOrEqualToData_lastScan(new Date(System.currentTimeMillis()-REFRESH_INTERVAL*1000));

    }


    public int generateQueryHash(ServerDataModel sim){
        String s=sim.getServer_build()+sim.getServer_descr1()+sim.getServer_logo()+sim.getServer_name()+sim.getServer_mapsize()+sim.getServer_maxplayer();

        return s.hashCode();
    }
}
