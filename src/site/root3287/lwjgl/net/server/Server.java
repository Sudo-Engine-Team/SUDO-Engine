package site.root3287.lwjgl.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

import site.root3287.lwjgl.net.ServerClient;
import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.packet.Packet;
import site.root3287.lwjgl.net.packet.PacketConnect;
import site.root3287.lwjgl.net.packet.PacketDisconnect;
import site.root3287.lwjgl.net.packet.PacketType;

public class Server {
	
	private int port;
	private Thread listenThread;
	private boolean listening = false;
	private DatagramSocket socket;
	private static final int MAX_PACKET_SIZE = 1024;
	private byte[] recievedDataBuffer = new byte[MAX_PACKET_SIZE*10];
	private Set<ServerClient> clients = new HashSet<ServerClient>();
	
	public Server(int port){
		this.port = port;
	}

	public int getPort() {
		return port;
	}
	
	private void listen(){
		while(listening){
			DatagramPacket packet = new DatagramPacket(this.recievedDataBuffer, MAX_PACKET_SIZE);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			process(packet);
		}
	}
	
	public void start(){
		try {
			this.socket = new DatagramSocket(this.port);
			this.socket.setSoTimeout(30000);
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		}
		this.listening = true;
		this.listenThread = new Thread(() -> listen()); //Landers?
		this.listenThread.setDaemon(true);
		this.listenThread.start();
		System.out.println("Server started on port "+this.port);
	}
	
	public void close(){
		this.listening = false;
		System.out.println("Stopping server!");
		try {
			this.listenThread.interrupt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server Stopped!");
	}
	
	private void process(DatagramPacket packet){
		byte[] data = packet.getData();
		System.out.println("Received Packet!");
		System.out.println("----------------");
		System.out.println("\t Data Content:");
		System.out.print("\t\t");
		for(int i = 0; i < packet.getLength(); i++){
			System.out.printf("%x ", data[i]);
			if((i+1) % 16 == 0){
				System.out.print("\n\t\t");
			}
		}
		System.out.println();
		System.out.println("\t Data Length: " + packet.getLength());
		System.out.println("----------------");
		
		String msg = new String(data);
		int length = Integer.parseInt(msg.substring(0,1));
		String dataType = "";
		for(int i =0; i< length; i++){
			dataType = dataType+msg.charAt(i);
		}
		
		PacketType packetName = Packet.find(dataType);
		Packet p = null;
		switch(packetName){
		case CONNECT:
			p = new PacketConnect(data);
			ServerClient player = new ServerClient(packet.getAddress(), packet.getPort());
			this.addConnection(player, (PacketConnect) p);
			break;
		case DISCONNECT:
			p = new PacketDisconnect(data);
			this.removeConnetion(((PacketDisconnect)p));
			break;
		case MOVE:
			break;
		}
	}
	
	private void send(byte[] data, InetAddress address, int port){
		assert(socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SERVER > CLIENT :"+ new String(data));
	}

	public void sendToAllClients(byte[] data) {
		for(ServerClient c : clients){
			send(data, c.address, c.port);
		}
	}
	
	public void addConnection(ServerClient player, PacketConnect connection){
		boolean alreadyConnected = false;
		for(ServerClient sc : this.clients){
			// if player user name is already exists in the set of clients.
			// 	if sc player ip address is null... set it to the current player
			// 	if sc player port is -1... set it to the current player  port
			// 	alreadyConnected = true;
			// else
			// sendData(packet.getData(), sc.Address, sc.port);
		}
		if(!alreadyConnected){
			this.clients.add(player);
			connection.writeData(this);
		}
	}

	private void removeConnetion(PacketDisconnect packetDisconnect) {
		
	}
}
