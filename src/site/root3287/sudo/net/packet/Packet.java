package site.root3287.sudo.net.packet;

import site.root3287.sudo.net.client.Client;
import site.root3287.sudo.net.server.Server;

public abstract class Packet {
	protected PacketType packet;
	
	public Packet(PacketType packet){
		this.packet = packet;
	}
	
	public abstract void writeData(Server server);
	public abstract void writeData(Client client);
	public abstract byte[] getData();
}
