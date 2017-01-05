package site.root3287.lwjgl.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import site.root3287.lwjgl.net.BinaryWriter;
import site.root3287.lwjgl.net.packet.PacketType;

public class Client {
	public enum Error{
		NONE, INVALID_HOST, SOCKET_EXCEPTION
	}
	
	private static final byte[] PACKET_HEADER = new byte[]{0x41, 0x42};
	private int port;
	private String address;
	private InetAddress server;
	private Error errorCode = Error.NONE;
	private DatagramSocket socket;
	
	/*
	 * @param host
	 * E.G 192.168.1.1:8123
	 */
	public Client(String host){
		String[] parts = host.split(":");
		if(parts.length !=2){
			errorCode = Error.INVALID_HOST;
			return;
		}
		this.address = parts[0];
		try{
			this.port = Integer.parseInt(parts[1]);
		}catch(NumberFormatException e){
			e.printStackTrace();
			errorCode = Error.INVALID_HOST;
			return;
		}
	}
	
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
			errorCode = Error.INVALID_HOST;
			return false;
		}
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			errorCode = Error.SOCKET_EXCEPTION;
			return false;
		}
		sendConnectionPacket();
		//TODO: wait for server to reply
		return true;
	}
	
	private void sendConnectionPacket(){
		BinaryWriter writer = new BinaryWriter();
		//writer.write(PacketType.PACKET_HEADER.getData());
		//writer.write(PacketType.CONNECT.getDataByte());
		send(writer.getBuffer());
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
