package com.r3sist3nt.GoRust.ScanLib;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

import com.r3sist3nt.GoRust.ScanLib.model.Server;


public class Masterserver {
	public Masterserver() {
		
	}
	
	public LinkedList<Server> requestServerList() {
		LinkedList<Server> sList = new LinkedList<Server>();
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("hl2master.steampowered.com");
			
			clientSocket.setSoTimeout(2000);
			

			//String lastIP = "66.55.154.239:28017";
			String lastIP = "0.0.0.0:0";
			boolean finished = false;
			int timeouts=0;
			while (!finished) {
				try {
					LinkedList<Server> tList;
					tList = getServerList(lastIP, clientSocket,IPAddress);
					lastIP = tList.getLast().getUri();
					sList.addAll(tList);
					if (tList.getLast().getUri().equals("0.0.0.0:0")) {
						finished = true;
					}
				} catch (IOException e) {
					timeouts++;
					System.out.println("Req Timeout, Retrying... IP: "+lastIP);
					System.out.println("ServerNo."+ sList.size());
					if(timeouts>5){
						System.out.println("Transfer interrupted");
						finished=true;
					}
				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return sList;
	}

	private byte[] MASTER_REQUEST(String seed, String filter) {
		byte[] payload_seed = seed.getBytes();
		byte[] payload_filter = filter.getBytes();
		byte[] b = new byte[4 + payload_seed.length + payload_filter.length];
		b[0] = (byte) 0x31;
		b[1] = (byte) 0xFF;

		System.arraycopy(payload_seed, 0, b, 2, payload_seed.length);
		b[2 + payload_seed.length] = (byte) 0x00;

		System.arraycopy(payload_filter, 0, b, 3 + payload_seed.length, payload_filter.length);
		b[3 + payload_seed.length + payload_filter.length] = (byte) 0x00;
		//System.out.println(ServerPacket.bytesToHex(b));
		return b;
	}
	private String filter = "\\appid\\252490\\empty\\1";
//	private String filter = "\\appid\\252490";
	private LinkedList<Server> getServerList(String seedIP, DatagramSocket clientSocket,InetAddress IPAddress) throws IOException {
		LinkedList<Server> sList = new LinkedList<Server>();
		byte[] response = new byte[2048];
		DatagramPacket receivePacket = new DatagramPacket(response, response.length);

		DatagramPacket sendPacket = new DatagramPacket(MASTER_REQUEST(seedIP, filter),
				MASTER_REQUEST(seedIP, filter).length, IPAddress, 27011);

		// Send Package
		clientSocket.send(sendPacket);


		
		clientSocket.receive(receivePacket);
		System.out.println("Rec1.");

		// System.out.println(Scan.bytesToHex(response));
		String lastIP = "";
		// System.out.println(receivePacket.getLength());
		for (int k = 6; k < receivePacket.getLength(); k += 6) {

			String ip = "" + toUnsignedInt(response[k]) + "." + toUnsignedInt(response[k + 1]) + "."
					+ toUnsignedInt(response[k + 2]) + "." + toUnsignedInt(response[k + 3]);

			byte by[] = new byte[2];
			by[0] = (byte) (0x000000FF & ((int) response[k + 4]));
			by[1] = (byte) (0x000000FF & ((int) response[k + 5]));
			

			int s = (int) (ByteBuffer.wrap(by).order(ByteOrder.BIG_ENDIAN).getShort() & 0xffff);
			lastIP = ip + ":" + s;
			
			//System.out.println(lastIP);
			sList.add(new Server(ip,s));
		}
		return sList;
	}

	public static int convertTwoBytesToInt2(byte b1, byte b2) {
		return (int) (((b2 & 0xFF) << 8) | (b1 & 0xFF));
	}

	public static int toUnsignedInt(byte x) {
		return ((int) x) & 0xff;
	}
}
