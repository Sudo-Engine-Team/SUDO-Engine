package site.root3287.sudo.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	private int port;
	private String address;
	private InetAddress server;
	private DatagramSocket socket;
	
	public Client(String host, int port){
		this.address = host;
		this.port = port;
	}
	
	public boolean connect(){
		try {
			server = InetAddress.getByName(this.address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		//TODO: wait for server to reply
		return true;
	}
	
	public void send(byte[] data){
		assert(socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, this.server, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
