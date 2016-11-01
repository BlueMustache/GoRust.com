package com.r3sist3nt.GoRust.ScanLib.model;

import java.io.IOException;

import com.r3sist3nt.GoRust.ScanLib.ServerPacket;

public class Server {
	private String ipaddress;
	private int port;
	private Rules rules;
	public Server(String ipaddress, int port){
		this.ipaddress=ipaddress;
		this.port=port;
	}
	
	public Rules getRules(){
		if(rules==null){
			ServerPacket sp = new ServerPacket();
			byte[] info;
			try {
				info = sp.serverRequest(ipaddress, port, sp.A2S_INFO());
				Rules s = new Rules(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}else{
			return rules;
		}
		
		
	}
	
	public String getUri(){
		return ipaddress+":"+port;
	}
}
