package site.root3287.sudo.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import site.root3287.sudo.net.ServerClient;

public class Server {
	
	private int port;
	private Thread listenThread;
	private boolean listening = false;
	private DatagramSocket socket;
	private static final int MAX_PACKET_SIZE = 1024;
	private byte[] recievedDataBuffer = new byte[MAX_PACKET_SIZE*10];
	private List<ServerClient> clients = new ArrayList<>();
	
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
			//TODO: some sort of packet processing
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

	public void broadcast(byte[] data) {
		for(ServerClient c : clients){
			send(data, c.address, c.port);
		}
	}
}
