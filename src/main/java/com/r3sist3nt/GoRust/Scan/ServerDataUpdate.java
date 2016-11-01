package com.r3sist3nt.GoRust.Scan;

import com.r3sist3nt.GoRust.database.ServerDataModel;
import com.r3sist3nt.GoRust.database.ServerDataRepository;
import com.r3sist3nt.GoRust.database.ServerIndexModel;
import com.r3sist3nt.GoRust.database.ServerIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     */
    public void checkServerStatus(){
        List<ServerIndexModel> newServerList = indexRepo.findByActive(true);


        List<ServerDataModel> list = dataRepo.lessThenOrEqualToData_lastScan(new Date(System.currentTimeMillis()-REFRESH_INTERVAL*1000));

    }
}
