package site.root3287.lwjgl.net.packet;

import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.server.Server;

public abstract class Packet {
	protected PacketType packet;
	
	public Packet(PacketType packet){
		this.packet = packet;
	}
	
	public abstract void writeData(Server server);
	public abstract void writeData(Client client);
	public abstract byte[] getData();
	
	public String readData(byte[] data){
		String msg = new String(data).trim();
		return msg.substring((1+this.packet.getLength()));
	}
	
	public static PacketType find(int id){
		for(PacketType p : PacketType.values()){
			if(p.getID() == id){
				return p;
			}
		}
		return PacketType.INVALID;
	}
	public static PacketType find(String type){
		for(PacketType p : PacketType.values()){
			if(p.getType() == type){
				return p;
			}
		}
		return PacketType.INVALID;
	}
}
