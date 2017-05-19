package site.root3287.sudo.entities.Camera;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
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
		//frustum.update(projectionView);
		if(hasComponent(TransformationComponent.class) && hasComponent(PlayerControlsComponent.class)){
			getComponent(PlayerControlsComponent.class).update(delta);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
