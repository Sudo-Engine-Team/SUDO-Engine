package site.root3287.lwjgl.entities;

import java.net.InetAddress;

import org.lwjgl.util.vector.Vector3f;

public class PlayerMP extends Player{
	public int userID, port;
	private static int userIDCounter;
	public InetAddress address;
	public boolean status = false;
	public PlayerMP(Vector3f position, String username, InetAddress address, int port) {
		super(position, username);
		this.userID = userIDCounter++;
		this.status = true;
		this.address = address;
		this.port = port;
	}
	@Override
	public int hashCode() {
		return this.userID;
	}
}
