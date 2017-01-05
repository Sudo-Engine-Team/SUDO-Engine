package site.root3287.lwjgl.net;

import java.net.InetAddress;

public class ServerClient {
	
	public int userID;
	public InetAddress address;
	public int port;
	public boolean status = false;
	
	private static int userIDCounter = 0;
	
	public ServerClient(InetAddress address, int port){
		this.userID = userIDCounter++;
		this.address = address;
		this.port = port;
		this.status = true;
	}
	
	@Override
	public int hashCode() {
		return userID;
	}
}
