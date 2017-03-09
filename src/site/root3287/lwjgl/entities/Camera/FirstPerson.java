package site.root3287.lwjgl.entities.Camera;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.AudioListenerComponent;
import site.root3287.lwjgl.component.PlayerControlsComponent;
import site.root3287.lwjgl.terrain.Terrain;

public class FirstPerson extends Camera{
	
	public FirstPerson(Vector3f position) {
		super(position);
		addComponent(new PlayerControlsComponent(this.id));
		addComponent(new AudioListenerComponent(this.id));
	}

	@Override
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta) {
		getComponent(PlayerControlsComponent.class).update(terrain, delta);
		getComponent(AudioListenerComponent.class).update(delta);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
