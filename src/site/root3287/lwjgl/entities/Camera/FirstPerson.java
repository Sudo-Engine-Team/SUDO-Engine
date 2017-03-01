package site.root3287.lwjgl.entities.Camera;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.FirstPersonComponent;
import site.root3287.lwjgl.terrain.Terrain;

public class FirstPerson extends Camera{
	
	public FirstPerson(Vector3f position) {
		super(position);
		addComponent(new FirstPersonComponent(this.id));
	}

	@Override
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta) {
		getComponent(FirstPersonComponent.class).update(terrain, delta);
		System.out.println(delta);
	}

	@Override
	public void update(float delta) {
		
	}
}
