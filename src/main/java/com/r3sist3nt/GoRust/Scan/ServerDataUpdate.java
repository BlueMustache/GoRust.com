package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.ScanLib.model.Rules;
import com.r3sist3nt.GoRust.ScanLib.model.Server;
import com.r3sist3nt.GoRust.ScanLib.model.ServerInfo;
import com.r3sist3nt.GoRust.database.ServerDataModel;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Julian on 01.11.2016.
 */

@Component
public class ServerDataUpdate {

    public static final int  THREAD_POOL_SIZE=64;

    private int changed=0;
    private int scanned=0;
    private int inactive=0;
    private int serverListIndex=0;
    private List<ServerIndexModel> serverList;

    @Autowired
    ServerIndexRepository indexRepo;
    @Autowired
    ServerDataRepository dataRepo;

    private static final Logger log = LoggerFactory.getLogger(ServerDataUpdate.class);
    /**
     * Method Searches in database for Servers which aren't renewed within the REFRESH_INTERVAL constant
     *
     * 1. get a list of current active servers
     * 2. Pick Server and query Stats
     * 3. Check all data_hash of the picked server for changes with the query result.
     * -> Logic outsourced in QueryTask.class for Threading.
     *
     */
    public void update(){
        serverList = indexRepo.findByActive(true);
        serverListIndex=0;
        changed=0;
        inactive=0;

        log.info("[ServerDataUpdate] Starting Server Query... [THREAD_POOL_SIZE]="+THREAD_POOL_SIZE);
        log.info("[ServerDataUpdate] Found "+serverList.size()+" active servers.");
        QueryTask[] pool = new QueryTask[THREAD_POOL_SIZE];

        /**
         * Creating Thread Pool with size of THREAD_POOL_SIZE which scan the servers.
         */
        for(int k=0;k<pool.length;k++){
            pool[k] = new QueryTask(this,dataRepo,indexRepo);
            pool[k].start();
        }

        for(int k=0;k<pool.length;k++){
            try{
                pool[k].join();
            }catch(InterruptedException ie){

            }

        }
        log.info("[ServerDataUpdate] Finished Server query. Scanned: "+scanned + " Detected Changes: "+changed+ " offline: "+inactive);
        log.info("[ServerDataUpdate] Waiting for next Scan task...");
    }

    /**
     * Stats reporting from Threads
     */

    public synchronized void detectedChange(){
        this.changed++;
    }
    public synchronized void detectedOffline(){
        this.inactive++;
    }
    public synchronized void scanned(){
        this.scanned++;
    }

    /**
     *  Builds a new ServerDataModel from the query result.
     *  Timestamps are not set!
     *
     * @param sim Server InfoModel provided by ServerDataRepository
     * @return ServerDataModel build from Server Query, ready to be saved in db.
     */

    public ServerDataModel buildNewServerDataModel(ServerIndexModel sim){
        ServerDataModel sdm = null;
        Server server = new Server(sim.getIpaddress(),sim.getPort());

        /**
         * Obtaining Server Info and if successful the Rules
         * .getServerInfo() and .getRules() return null on packet timeout.
         */
        ServerInfo sInfo = server.getServerInfo();
        if(sInfo!=null) {
            Rules r = server.getRules();
            if (r != null) {
                /**
                 * On success fill Object with query info.
                 */
                sdm = new ServerDataModel();
                sdm.setServer_build(r.getRule("build"));
                sdm.setServer_descr1(r.getRule("description_00"));
                sdm.setServer_descr2(r.getRule("description_01"));
                sdm.setServer_descr3(r.getRule("description_02"));
                sdm.setServer_descr4(r.getRule("description_03"));
                sdm.setServer_logo(r.getRule("headerimage"));
                sdm.setServer_name(sInfo.getName());

                //Try casting
                try {
                    sdm.setServer_mapsize(Integer.parseInt(r.getRule("world.size")));
                    sdm.setServer_seed(Integer.parseInt(r.getRule("world.seed")));
                } catch (NumberFormatException e) {

                }

                sdm.setServer_hash(r.getRule("hash"));
                sdm.setServerID(sim.getId());
                sdm.setServer_maxplayer(sInfo.getMaxplayer());
                sdm.setData_hash(generateQueryHash(sdm));
            }
        }

        return sdm;
    }

    /**
     * Server queue for Query Threads
     * @return next Queued ServerIndexModel
     */
    public synchronized ServerIndexModel getNextQueuedServer(){
        if(serverListIndex<serverList.size()){
            ServerIndexModel sim = serverList.get(serverListIndex);
            serverListIndex++;
            return sim;
        }
        return null;
    }
    public int generateQueryHash(ServerDataModel sdm){
        String s=sdm.getServer_build()+sdm.getServer_descr1()+sdm.getServer_logo()+sdm.getServer_name()+sdm.getServer_mapsize()+sdm.getServer_maxplayer()+sdm.getServer_seed();
        return s.hashCode();
    }
}
