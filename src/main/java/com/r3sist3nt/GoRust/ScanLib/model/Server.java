package com.r3sist3nt.GoRust.ScanLib.model;

import java.io.IOException;

import com.r3sist3nt.GoRust.ScanLib.ServerPacket;

public class Server {
	private String ipaddress;
	private int port;
	private Rules rules;
	private ServerInfo serverInfo;
	public Server(String ipaddress, int port){
		this.ipaddress=ipaddress;
		this.port=port;
	}

	public ServerInfo getServerInfo(){
		if(serverInfo==null){
			try {
				ServerPacket sp = new ServerPacket();
				serverInfo = new ServerInfo(sp.serverRequest(ipaddress, port, sp.A2S_INFO()));
			} catch (IOException e) {}
		}
		return serverInfo;
	}

	public Rules getRules(){
		if(rules==null){
			try {
				ServerPacket sp = new ServerPacket();
				rules = new Rules(sp.ruleRequest(ipaddress,port));
			} catch (IOException e) {

			}
		}
		return rules;
		
	}
	
	public String getUri(){
		return ipaddress+":"+port;
	}
}
