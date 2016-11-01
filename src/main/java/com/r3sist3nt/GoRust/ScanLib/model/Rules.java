package com.r3sist3nt.GoRust.ScanLib.model;

import java.util.LinkedList;

public class Rules {
	private String servername;
	private String seed;
	private LinkedList<String[]> rules = new LinkedList<String[]>();
	public Rules(byte[] response){
		int last=6;
		byte[] sub;
		LinkedList<String> sList = new LinkedList<String>();
		//0-5 Header, 6 NULL Byte
		for(int k=7;k<response.length;k++){
			if(response[k]==0){
				sub=new byte[k-1-last];
				System.arraycopy(response, last+1, sub, 0, k-last-1);
				last=k;
				sList.add(new String(sub));
			}
		}
		for(int k=0;k<sList.size();k+=2){
			addNewRule(sList.get(k),sList.get(k+1));
		}
		
	}
	
	private void addNewRule(String rule,String value){
		String[] s = new String[2];
		s[0]=rule;
		s[1]=value;
		rules.add(s);
	}
	
	public void printRules(){
		for(int k=0;k<rules.size();k++){
			System.out.println(rules.get(k)[0] +": " + rules.get(k)[1]);
		}
	}
}
