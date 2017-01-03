package site.root3287.lwjgl.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.Display;

public class Server {
	private int port;
	private Thread listenThread;
	private volatile boolean listening = false;
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
			if(Display.isCloseRequested()){
				listening =false;
			}
			DatagramPacket packet = new DatagramPacket(this.recievedDataBuffer, MAX_PACKET_SIZE);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			process(packet);
		}
		close();
	}
	
	public synchronized void start(){
		try {
			this.socket = new DatagramSocket(this.port);
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		}
		this.listening = true;
		this.listenThread = new Thread(() -> listen()); //Landers?
		this.listenThread.start();
		System.out.println("Starting server on port"+this.port);
	}
	
	public synchronized void close(){
		this.listening = false;
		System.out.println("Attempting to stop the server!");
		try {
			this.listenThread.interrupt();
			System.out.println("Thread interrupted!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.listenThread.join();
			System.out.println("Cleaning Thread");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server Stopped!");
	}
	
	private void process(DatagramPacket packet){
		byte[] data = packet.getData();
		
		if(new String(data, 0, data.length).equals(new String(Packet.PING.getData(),0, Packet.PING.getData().length))){
			send("pong".getBytes(), packet.getAddress(), packet.getPort());
			System.out.println("Client > Server : ping");
		}
		if(new String(data, 0, data.length).equals(new String(Packet.CONNECT.getData(),0, Packet.CONNECT.getData().length))){
			//send("".getBytes(), packet.getAddress(), packet.getPort());
			clients.add(new ServerClient(packet.getAddress(), packet.getPort()));
			System.out.println("Client > Server : Connected");
		}
		if(new String(data, 0, data.length).equals(new String(Packet.DISCONNECT.getData(),0, Packet.DISCONNECT.getData().length))){
			//send("".getBytes(), packet.getAddress(), packet.getPort());
			System.out.println("Client > Server : Disconnected");
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
}
