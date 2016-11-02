package com.r3sist3nt.GoRust.database;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server_data")
public class ServerDataModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "serverid", nullable = false)
	private Long serverid;

	@Column(name = "entrydate", nullable = false)
	private Timestamp entrydate;

	@Column(name = "server_name", nullable = true)
	private String server_name;

	@Column(name = "server_hash", nullable = true)
	private String server_hash;

	@Column(name = "server_logo", nullable = true)
	private String server_logo;

	@Column(name = "server_descr1", nullable = true)
	private String server_descr1;

	@Column(name = "server_descr2", nullable = true)
	private String server_descr2;

	@Column(name = "server_descr3", nullable = true)
	private String server_descr3;

	@Column(name = "server_descr4", nullable = true)
	private String server_descr4;

	@Column(name = "server_maxplayer", nullable = false)
	private int server_maxplayer;

	@Column(name = "server_seed", nullable = false)
	private int server_seed;

	@Column(name = "server_mapsize", nullable = false)
	private int server_mapsize;

	@Column(name = "server_build", nullable = false)
	private String server_build;

	@Column(name = "data_hash", nullable = false)
	private int data_hash;

	@Column(name = "data_lastscan", nullable = false)
	private Timestamp data_lastscan;

	public Timestamp getData_lastscan() {
		return data_lastscan;
	}

	public void setData_lastscan(Timestamp data_lastscan) {
		this.data_lastscan = data_lastscan;
	}

	public Long getId() {
		return id;
	}

	public Long getServerID() {
		return serverid;
	}

	public void setServerID(Long serverID) {
		this.serverid = serverID;
	}

	public Timestamp getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Timestamp entrydate) {
		this.entrydate = entrydate;
	}

	public String getServer_name() {
		return server_name;
	}

	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	public String getServer_logo() {
		return server_logo;
	}

	public void setServer_logo(String server_logo) {
		this.server_logo = server_logo;
	}

	public String getServer_descr1() {
		return server_descr1;
	}

	public void setServer_descr1(String server_descr1) {
		this.server_descr1 = server_descr1;
	}

	public String getServer_descr2() {
		return server_descr2;
	}

	public void setServer_descr2(String server_descr2) {
		this.server_descr2 = server_descr2;
	}

	public String getServer_descr3() {
		return server_descr3;
	}

	public void setServer_descr3(String server_descr3) {
		this.server_descr3 = server_descr3;
	}

	public String getServer_descr4() {
		return server_descr4;
	}

	public void setServer_descr4(String server_descr4) {
		this.server_descr4 = server_descr4;
	}

	public int getServer_maxplayer() {
		return server_maxplayer;
	}

	public void setServer_maxplayer(int server_maxplayer) {
		this.server_maxplayer = server_maxplayer;
	}

	public int getServer_mapsize() {
		return server_mapsize;
	}

	public void setServer_mapsize(int server_mapsize) {
		this.server_mapsize = server_mapsize;
	}

	public String getServer_build() {
		return server_build;
	}

	public void setServer_build(String server_build) {
		this.server_build = server_build;
	}

	public int getData_hash() {
		return data_hash;
	}

	public void setData_hash(int data_hash) {
		this.data_hash = data_hash;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServerid() {
		return serverid;
	}

	public void setServerid(Long serverid) {
		this.serverid = serverid;
	}

	public String getServer_hash() {
		return server_hash;
	}

	public void setServer_hash(String server_hash) {
		this.server_hash = server_hash;
	}

	public int getServer_seed() {
		return server_seed;
	}

	public void setServer_seed(int server_seed) {
		this.server_seed = server_seed;
	}
}
