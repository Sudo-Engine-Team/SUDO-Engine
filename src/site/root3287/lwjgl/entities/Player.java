package site.root3287.lwjgl.entities;

import org.lwjgl.util.vector.Vector3f;

public class Player extends Camera{
	private String username;
	public Player(Vector3f position, String username) {
		super(position);
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
}
