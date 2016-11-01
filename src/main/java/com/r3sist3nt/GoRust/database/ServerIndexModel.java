package com.r3sist3nt.GoRust.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server_index")
public class ServerIndexModel {
	
	public Long getId() {
		return id;
	}


	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ipaddress", nullable = false)
	private String ipaddress;
	
	@Column(name = "port", nullable = false)
	private int port;
	
	@Column(name = "active", nullable = false)
	private boolean active;
}
