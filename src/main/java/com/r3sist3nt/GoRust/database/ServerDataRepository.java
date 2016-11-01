package com.r3sist3nt.GoRust.database;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface ServerDataRepository extends CrudRepository<ServerDataModel,Long>{
	
    List<ServerDataModel> findAll();

    //List<ServerDataModel> lessThenOrEqualToData_lastScan(Date d);
    List<ServerDataModel> findByServeridOrderByEntrydateDesc(long serverID, Pageable pageable);



}
