package site.root3287.lwjgl.entities;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.terrain.Terrain;

public abstract class Camera{
	
	protected Vector3f position;
	protected float 	pitch, 
				  	yaw, 
				  	roll, 
				  	dy,
				  	sensitivity = 0.25f,
				  	distance = 20f,
				  	pauseCooldown = 0f;
	public Camera(Vector3f position) {
		this.position = position;
		this.dy = 0;
	}

	public abstract void update(float delta);
	public abstract void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta);

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public Vector3f getPosition() {
		return this.position;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
	}

	public float getRoll() {
		return this.roll;
	}
}
