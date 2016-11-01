package com.r3sist3nt.GoRust.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ServerIndexRepository extends CrudRepository<ServerIndexModel,Long>{
    List<ServerIndexModel> findByIpaddressAndPort(String ipaddress, int port);
    List<ServerIndexModel> findByActive(boolean b);
}
