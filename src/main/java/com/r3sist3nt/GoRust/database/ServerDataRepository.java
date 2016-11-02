package com.r3sist3nt.GoRust.database;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ServerDataRepository extends CrudRepository<ServerDataModel,Long>{
	
    List<ServerDataModel> findAll();

    List<ServerDataModel> findByServeridOrderByEntrydateDesc(long serverID);



}
