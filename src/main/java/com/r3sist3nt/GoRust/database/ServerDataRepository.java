package com.r3sist3nt.GoRust.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ServerDataRepository extends CrudRepository<ServerDataModel,Long>{
	void delete(ServerDataModel deleted);
	
    List<ServerDataModel> findAll();
}
