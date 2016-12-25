package site.root3287.lwjgl.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.terrain.Terrain;

public class Camera{
	
	private Vector3f position;
	private float pitch, yaw, roll, dy;
	private boolean isGrabbed = true;
	private boolean isMouseGrabbedRequest = false;
	private boolean canFly = false;
	private boolean gravity = true;
	private boolean isInAir = false;
	private float sensitivity = 0.25f;
	private float distance = 20f;
	private float pauseCooldown = 0f;
	private final float GRAVITY = -9.81f;
	private final float JUMP = 2;
	private final float CAMERA_HEIGHT = 10.0f;
	
	public Camera(Vector3f position) {
		this.position = position;
		this.dy = 0;
	}

	public void update(Terrain terrain, float delta) {
		move(terrain, delta);
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
	
	private void move(Terrain terrain, float delta){
		
		if(pauseCooldown<0){
			this.pauseCooldown = 0;
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
		
		if(gravity && !canFly){
			this.dy += GRAVITY * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(canFly && !gravity){
				position.y += finalDistance;
			}else if(!canFly && gravity && !isInAir){
				this.dy = JUMP;
				isInAir = true;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if(canFly && !gravity){
				position.y -= finalDistance;
			}else if(!canFly && gravity){
				
			}
		}
		if(gravity && !canFly){
			this.position.y  += dy;
		}
		float terrainHeight = terrain.getTerrainHeightByCoords(this.position.x, this.position.z) + CAMERA_HEIGHT;
		if(position.y < terrainHeight && gravity && !canFly){
			this.dy = 0;
			isInAir = false;
			position.y = terrainHeight;
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
