package site.root3287.lwjgl.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.entities.Entity;

public class CupEntity extends Entity {
	
	private Loader loader;

	public CupEntity(Loader loader) {
		this.loader = loader;
		TransformationComponent transform = new TransformationComponent();
		ModelComponent model = new ModelComponent();
		transform.position = new Vector3f(0, 0, 0);
		transform.rotation = new Vector3f(0, 0, 0);
		transform.scale = 1; 
		this.addComponent(transform);
		this.addComponent(model);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}
}
