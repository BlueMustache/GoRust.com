package com.r3sist3nt.GoRust.database;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ServerDataRepository extends CrudRepository<ServerDataModel,Long>{
	
    List<ServerDataModel> findAll();

    List<ServerDataModel> lessThenOrEqualToData_lastScan(Date d);
}
