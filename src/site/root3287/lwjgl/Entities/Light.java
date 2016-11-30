package site.root3287.lwjgl.Entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	private Vector3f position;
	private Vector3f colour;
	
	public Light(){
		this.colour = new Vector3f(0,0,0);
		this.position = new Vector3f(0,0,0);
	}
	public Light(Vector3f position){
		this.position = position;
		this.colour = new Vector3f(0,0,0);
	}
	public Light(Vector3f position, Vector3f colour){
		this.position = position;
		this.colour = colour;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
}
