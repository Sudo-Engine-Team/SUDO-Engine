package site.root3287.lwjgl.net.packet;

public enum PacketType {
	INVALID("INV", -1),
	PACKET_HEADER("AB", 00),
	CONNECT("con", 01),
	DISCONNECT("01", 02),
	MOVE("02", 03),
	CHAT("03", 04),
	PING("04", 05),
	PONG("05", 06);
	
	private String type;
	private int id;
	private int length;
	private PacketType(String type, int id){
		this.type = type.trim();
		this.id = id;
		this.length = this.type.length();
	}
	
	public String getType(){
		return type;
	}
	public int getID(){
		return this.id;
	}
	public int getLength(){
		return this.length;
	}
}
