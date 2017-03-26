package site.root3287.sudo.entities.Camera;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.terrain.Terrain;

public abstract class Camera extends Entity{
	public Camera(Vector3f position) {
		TransformationComponent transform = new TransformationComponent();
		transform.position = position;
		addComponent(transform);
	}

	public abstract void update(float delta);
	public abstract void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta);
}
