package com.r3sist3nt.GoRust.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 02.11.2016.
 */
@Component
public interface ServerEventRepository extends CrudRepository<ServerEventModel,Long>{

}
