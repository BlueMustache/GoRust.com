package com.r3sist3nt.GoRust.ScanLib.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Rules {
	HashMap<String,String> keys = new HashMap<String,String>();
	public Rules(byte[] response){
		int last=6;
		byte[] sub;
		LinkedList<String> sList = new LinkedList<String>();
		//0-5 Header, 6 NULL Byte
		for(int k=7;k<response.length;k++){
			if(response[k]==0) {
				sub = new byte[k - 1 - last];
				System.arraycopy(response, last + 1, sub, 0, k - last - 1);
				last = k;
				sList.add(new String(sub));
			}
		}
		for(int k=0;k<sList.size()-1;k+=2){
			keys.put(sList.get(k),sList.get(k+1));
		}


		
	}

	public String getRule(String key){
		return keys.get(key);
	}

	public void printRules(){
		for(Map.Entry key : keys.entrySet()){
			System.out.println(key.getKey() + " -> "+key.getValue());
		}
	}
}
