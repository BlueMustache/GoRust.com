package com.r3sist3nt.GoRust.ScanLib.model;

public class ServerRequestException extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -785154779738292810L;
	public ServerRequestException() { super(); }
	  public ServerRequestException(String message) { super(message); }
	  public ServerRequestException(String message, Throwable cause) { super(message, cause); }
	  public ServerRequestException(Throwable cause) { super(cause); }
}
