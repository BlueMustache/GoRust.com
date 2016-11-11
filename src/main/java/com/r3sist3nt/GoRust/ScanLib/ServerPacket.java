package com.r3sist3nt.GoRust.ScanLib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.xml.bind.DatatypeConverter;

public class ServerPacket {
	/**
	 * Building A2S_INFO Packet.
	 * Bytes 0-5: Header
	 * Bytes 6-22: Payload String("Source Engine Query")
	 * Byte 23: String NULL TERM Byte
	 *
	 * @return A2S_INFO Package as byte Array.
	 */
	//byte[] bytes = ByteBuffer.allocate(4).putInt(1695609641).array();
	public byte [] PLAYER_INFO(byte[] c){
		byte[] data = new byte[5];
		data[0]= (byte) 0x55;
		System.arraycopy(c,0,data,1,4);
		return data;
	}
	public byte[] A2S_INFO(){
		byte[] data = new byte[25];
		data[0] = (byte) 0xFF;
		data[1] = (byte) 0xFF;
		data[2] = (byte) 0xFF;
		data[3] = (byte) 0xFF;
		data[4] = 0x54;
		byte[] payload = "Source Engine Query".getBytes();
		System.arraycopy(payload, 0, data, 5, payload.length);
		data[24] = (byte) 0x00;
		return data;
	}
	
	public byte[] A2S_RULES(){
		byte[] data = new byte[9];
		data[0] = (byte) 0xFF;
		data[1] = (byte) 0xFF;
		data[2] = (byte) 0xFF;
		data[3] = (byte) 0xFF;
		data[4] = 0x56;
		data[5] = 0x00;
		data[6] = 0x00;
		data[7] = 0x00;
		data[8] = 0x00;
		return data;
	}
	
	public ServerPacket(){

	}
	public byte[] ruleRequest(String ip, int port)throws IOException{
		 byte[] resp1 = new byte[2048];
		 byte[] resp2 = new byte[2048];
		 DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout(3000);
	     InetAddress IPAddress = InetAddress.getByName(ip);

		/**
		 * Send Challenge request
		 */
		 DatagramPacket sendPacket = new DatagramPacket(A2S_RULES() ,A2S_RULES().length, IPAddress, port);
		 
		 //Send Package
	     clientSocket.send(sendPacket);
	     
	     DatagramPacket rec1 = new DatagramPacket(resp1, resp1.length);
	     DatagramPacket rec2 = new DatagramPacket(resp2, resp2.length);
	    // DatagramPacket receivePacket2 = new DatagramPacket(response, response.length);
	     
	     clientSocket.receive(rec1);
		/**
		 * Building Challenge Packet:
		 * Bytes 0-5: Header Bytes
		 * Bytes 0-4: Challenge Bytes from retrieved packet.
		 */
		byte[] cRes = {(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0x56,resp1[5],resp1[6],resp1[7],resp1[8]};
	     DatagramPacket cPack = new DatagramPacket(cRes ,cRes.length, IPAddress, port);

	     
	     if(rec1.getLength()==9){//Challegne package
			 clientSocket.send(cPack);

			 clientSocket.receive(rec2);

			 clientSocket.close();

			 /**
			  * Creating byte array with correct size and copy from Buffer
			  */
			 byte b[] = new byte[rec2.getLength()];
	    	 System.arraycopy(resp2, 0, b, 0, rec2.getLength());
	    	 return b;
	     }
	     clientSocket.close();
	     return resp1;
		
	}
	public byte[] serverRequest(String ip, int port, byte[] REQUEST_TYPE) throws IOException{
		 byte[] response = new byte[2048];
		 DatagramSocket clientSocket = new DatagramSocket();
		 clientSocket.setSoTimeout(3000);
	     InetAddress IPAddress = InetAddress.getByName(ip);
	     
		 DatagramPacket sendPacket = new DatagramPacket(REQUEST_TYPE ,REQUEST_TYPE.length, IPAddress, port);
		 
		 //Send Package
	     clientSocket.send(sendPacket);
	     
	     DatagramPacket receivePacket = new DatagramPacket(response, response.length);
	     clientSocket.receive(receivePacket);
	     clientSocket.close();
		
		return response;
	}

	/**
	 * Debug function to convert raw bytes to readable hex
	 */
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 3];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 3] = hexArray[v >>> 4];
	        hexChars[j * 3 + 1] = hexArray[v & 0x0F];
	        hexChars[j * 3 + 2] = ' ';
	        
	    }
	    return new String(hexChars);
	}
}
