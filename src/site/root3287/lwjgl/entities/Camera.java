package site.root3287.lwjgl.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera{
	
	private Vector3f position;
	private float pitch, yaw, roll;
	private boolean isGrabbed = true;
	private boolean isMouseGrabbedRequest = false;
	private boolean canFly = true;
	private boolean gravity = false;
	private boolean isInAir = false;
	private float sensitivity = 0.25f;
	private float distance = 20f;
	private float pauseCooldown = 0f;
	
	public Camera(Vector3f position) {
		this.position = position;
	}

	public void update(float delta) {
		move(delta);
		if(isMouseGrabbedRequest){
			isMouseGrabbedRequest = false;
			if(isGrabbed){
				this.isGrabbed = false;
			}else{
				this.isGrabbed = true;
			}
			Mouse.setGrabbed(this.isGrabbed);
		}
	}
	
	private void move(float delta){
		float dy;
		
		if(pauseCooldown<0){
			this.pauseCooldown =0;
		}
		if(this.pauseCooldown<=5){
			this.pauseCooldown += delta;
		}
		if(isGrabbed){
			this.pitch -= Mouse.getDY() * sensitivity;
			this.yaw += Mouse.getDX() * sensitivity;
		}
		
		float finalDistance = this.distance*delta;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.x += finalDistance * (float)Math.sin(Math.toRadians(yaw));
		    position.z -= finalDistance * (float)Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw+90));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw+90));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw-90));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw-90));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if(pauseCooldown >= 0.75){
				this.isMouseGrabbedRequest = true;
				pauseCooldown = 0;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(canFly && !gravity){
				position.y += finalDistance;
			}else if(!canFly && gravity){
				//Jump
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if(canFly && !gravity){
				position.y -= finalDistance;
			}else if(!canFly && gravity){
				//Jump
			}
		}
		
		if(position.y < 3.5 && gravity && !canFly){
			dy = 0;
			isInAir = false;
			position.y = 3.5f;
		}
	}

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
	
	public void setGrabbed(boolean grabbed){
		this.isGrabbed = grabbed;
	}
	
	public boolean isGrabbed(){
		return this.isGrabbed;
	}
}
