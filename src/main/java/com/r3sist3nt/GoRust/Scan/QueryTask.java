package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.database.ServerDataModel;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Julian on 02.11.2016.
 */
public class QueryTask extends Thread {
    /**
     * Query Task.
     * This Thread updates the Database records. See ServerDataUpdate.class
     */


    private ServerDataRepository dataRepo;
    private ServerIndexRepository indexRepo;

    private ServerDataUpdate sdu;

    public QueryTask(ServerDataUpdate sdu, ServerDataRepository dataRepo, ServerIndexRepository indexRepo) {
        this.sdu = sdu;
        this.dataRepo = dataRepo;
        this.indexRepo = indexRepo;
    }

    private static final Logger log = LoggerFactory.getLogger(QueryTask.class);

    public void run() {
        ServerIndexModel sim = sdu.getNextQueuedServer();
        while (sim != null) {
            try {


                List<ServerDataModel> dataList = dataRepo.findByServeridOrderByEntrydateDesc(sim.getId(), new PageRequest(0, 1));

                ServerDataModel sdm = sdu.buildNewServerDataModel(sim);
                if (sdm != null) {
                    /**
                     * Check for existing entry and validate data hash.
                     * When existing record is found:
                     *      -> Check if something changed.
                     *          -> Changed: Add new record.
                     *          -> Unchanged: Update last record with new timestamp
                     * When no record is found
                     *      -> Add record with query data
                     */
                    if (dataList.size() > 0) {
                        ServerDataModel db = dataList.get(0);
                        if (db.getData_hash() == sdm.getData_hash()) {
                            db.setData_lastscan(new Timestamp(System.currentTimeMillis()));
                            dataRepo.save(db);
                        } else {
                            sdm.setData_lastscan(new Timestamp(System.currentTimeMillis()));
                            sdm.setEntrydate(new Timestamp(System.currentTimeMillis()));
                            dataRepo.save(sdm);
                        }

                    } else {
                        sdm.setEntrydate(new Timestamp(System.currentTimeMillis()));
                        sdm.setData_lastscan(new Timestamp(System.currentTimeMillis()));
                        dataRepo.save(sdm);
                    }
                } else {
                    sim.setActive(false);
                    indexRepo.save(sim);
                }
                /**
                 * Get next Server entry.
                 */
            } catch (DataException e) {
                log.error("DataException: "+ e.getMessage());
            } catch(DataIntegrityViolationException e){
                log.error("DataIntegrityViolationException: "+ e.getMessage());
            }
            sim = sdu.getNextQueuedServer();
        }
    }
}
