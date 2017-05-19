package site.root3287.sudo.net;

import java.net.InetAddress;
import java.util.UUID;

public class ServerClient {
	
	public UUID sessionToken;
	public InetAddress address;
	public int port;
	public boolean status = false;
	
	private static int userIDCounter = 0;
	
	public ServerClient(InetAddress address, int port){
		setUserIDCounter(getUserIDCounter() + 1);
		this.sessionToken = UUID.randomUUID();
		this.address = address;
		this.port = port;
		this.status = true;
	}
	
	@Override
	public int hashCode() {
		return sessionToken.hashCode();
	}

	public static int getUserIDCounter() {
		return userIDCounter;
	}

	public static void setUserIDCounter(int userIDCounter) {
		ServerClient.userIDCounter = userIDCounter;
	}
}
