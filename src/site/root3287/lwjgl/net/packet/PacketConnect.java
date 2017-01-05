package site.root3287.lwjgl.net.packet;

import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.server.Server;

public class PacketConnect extends Packet{

	private String username;
	public PacketConnect(byte[] data) {
		super(PacketType.CONNECT);
		this.username = readData(data);
	}
	public PacketConnect(String username) {
		super(PacketType.CONNECT);
		this.username = username;
	}
	@Override
	public void writeData(Server server) {
		server.sendToAllClients(getData());
	}
	@Override
	public void writeData(Client client) {
		client.send(getData());
	}
	@Override
	public byte[] getData() {
		return (this.packet.getLength()+this.packet.getType()+this.username).getBytes();
	}
}
