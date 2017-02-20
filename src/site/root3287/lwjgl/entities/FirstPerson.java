package site.root3287.lwjgl.entities;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.terrain.Terrain;

public class FirstPerson extends Camera{
	private boolean isGrabbed = true, 
			isMouseGrabbedRequest = false, 
			canFly = false,
			gravity = true,
			isInAir = false,
			canDoubleJump = false;
	private final float GRAVITY = (float) (-9.81/2f), 
			JUMP = 1, 
			CAMERA_HEIGHT = 3.5f;
	private int direction = 0;
	
	public FirstPerson(Vector3f position) {
		super(position);
	}

	@Override
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta) {
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
	
	private void move(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta){
		if(pauseCooldown<0){
			this.pauseCooldown = 0;
		}
		if(this.pauseCooldown<=5){
			this.pauseCooldown += delta;
		}
		
		if(isGrabbed){
			this.pitch -= Mouse.getDY() * sensitivity;
			this.yaw += Mouse.getDX() * sensitivity;
			
			if(this.pitch > 90){
				this.pitch = 90;
			}else if(this.pitch < -90){
				this.pitch = -90;
			}
			
			if(this.yaw > 360){
				this.yaw = 0;
			}else if(this.yaw < 0){
				this.yaw = 360-Math.abs(this.yaw);
			}
		}
		
		this.direction = (int) (this.yaw/90);

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
		
		if(gravity && !canFly){ // Gravity is in effect!
			//direction for gravity
			this.dy += GRAVITY * delta;
			
			//Jump
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !isInAir){
				this.dy = JUMP;
				if(!this.canDoubleJump){
					isInAir = true;
				}
			}
			
			//Apply Gravity
			this.position.y  += dy;
			
			//Collision detection
			int chunkX = (int) Math.floor(position.x / Terrain.SIZE);
			int chunkZ = (int) Math.floor(position.z / Terrain.SIZE);
			Terrain currentTerrain = null;
			Map<Integer, Terrain> temp;
			float terrainHeight = 0;
			if(terrain.containsKey((int)chunkX)){
				temp = terrain.get((int)chunkX);
				if(temp.containsKey((int)chunkZ)){
					currentTerrain = temp.get((int)chunkZ);
				}
			}
			terrainHeight = currentTerrain.getTerrainHeightByCoords(position.x, position.z) + CAMERA_HEIGHT;
			if(position.y < terrainHeight){ // Collision detection
				this.dy = 0;
				isInAir = false;
				position.y = terrainHeight;
			}
		}else{
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				if(canFly && !gravity){ // Move up
					position.y += finalDistance;
				}
			}
		
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				if(canFly && !gravity){
					position.y -= finalDistance;
				}
			}
		}
	}

	@Override
	public void update(float delta) {
	}
	public void setGrabbed(boolean grabbed){
		this.isGrabbed = grabbed;
	}
	
	public boolean isGrabbed(){
		return this.isGrabbed;
	}
}
