package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.database.ServerDataModel;
import com.r3sist3nt.GoRust.database.ServerEventModel;
import com.r3sist3nt.GoRust.database.ServerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Julian on 02.11.2016.
 */
@Component
public class ServerChangeEvent {
    /**
     * Class for detecting exact change
     *      Detected changes will be logged in server_event table.
     */
    @Autowired
    ServerEventRepository eventRepo;

    public void newEvent(ServerDataModel oldModel, ServerDataModel newModel){
        boolean namechanged=false,seedchanged=false,mapsizechanged=false,maxplayerchanged=false,buildchanged=false;
        ServerEventModel sem = new ServerEventModel();
        sem.setServerid(newModel.getServerid());
        if(newModel.getServer_name()==null || newModel.getServer_build()==null){
            return;
        }
        if(!oldModel.getServer_name().equals(newModel.getServer_name())){
            namechanged=true;
            sem.setNameto(newModel.getServer_name());

        }
        if(oldModel.getServer_seed()!=newModel.getServer_seed()){
            seedchanged=true;
            sem.setSeedto(newModel.getServer_seed());
        }
        if(oldModel.getServer_mapsize()!=newModel.getServer_mapsize()){
            mapsizechanged=true;
            sem.setMapsizeto(newModel.getServer_mapsize());
        }
        if(oldModel.getServer_maxplayer()!=newModel.getServer_maxplayer()){
            maxplayerchanged=true;
            sem.setSeedto(newModel.getServer_maxplayer());
        }
        if(!oldModel.getServer_build().equals(newModel.getServer_build())){
            buildchanged=true;
            sem.setBuildto(newModel.getServer_build());
        }

        sem.setName(namechanged);
        sem.setMapsize(mapsizechanged);
        sem.setSeed(seedchanged);
        sem.setSlots(maxplayerchanged);
        sem.setBuild(buildchanged);

        sem.setTime(new Timestamp(System.currentTimeMillis()));

        eventRepo.save(sem);
    }
}
