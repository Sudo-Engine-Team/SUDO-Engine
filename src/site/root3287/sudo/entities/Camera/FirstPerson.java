package site.root3287.sudo.entities.Camera;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.AudioListenerComponent;
import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.utils.LWJGLMaths;

public class FirstPerson extends Camera{
	
	public FirstPerson(Vector3f position) {
		super(position);
		addComponent(new PlayerControlsComponent(this.id));
		addComponent(new AudioListenerComponent(this.id));
	}

	@Override
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta) {
		viewMatrix = LWJGLMaths.createViewMatrix(this);
		if(!Keyboard.isKeyDown(Keyboard.KEY_M)){
			Matrix4f.mul(projectionMatrix, viewMatrix, projectionView);
			frustum.update(projectionView);
		}
		getComponent(PlayerControlsComponent.class).update(terrain, delta);
		getComponent(AudioListenerComponent.class).update(delta);
	}

	@Override
	public void update(float delta) {
		viewMatrix = LWJGLMaths.createViewMatrix(this);
		Matrix4f.mul(projectionMatrix, viewMatrix, projectionView);
		frustum.update(projectionView);
		if(hasComponent(TransformationComponent.class) && hasComponent(PlayerControlsComponent.class)){
			Vector3f position = getComponent(TransformationComponent.class).position;
			float yaw = getComponent(TransformationComponent.class).yaw;
			float pitch = getComponent(TransformationComponent.class).pitch;
			float sensitivity = getComponent(PlayerControlsComponent.class).sensitivity;
			boolean isGrabbed = getComponent(PlayerControlsComponent.class).isGrabbed;
			System.out.println(yaw);
			if(isGrabbed){
				pitch -= Mouse.getDY() * sensitivity;
				yaw += Mouse.getDX() * sensitivity;
				
				if(pitch > 90){
					pitch = 90;
				}else if(pitch < -90){
					pitch = -90;
				}
				
				if(yaw > 360){
					yaw = 0;
				}else if(yaw < 0){
					yaw = 360-Math.abs(yaw);
				}
			}
			getComponent(TransformationComponent.class).position = position;
			getComponent(TransformationComponent.class).yaw = yaw;
			getComponent(TransformationComponent.class).pitch = pitch;
			getComponent(PlayerControlsComponent.class).sensitivity = sensitivity;
			getComponent(PlayerControlsComponent.class).isGrabbed = isGrabbed;
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
