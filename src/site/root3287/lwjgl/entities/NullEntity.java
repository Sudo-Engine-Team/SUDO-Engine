package site.root3287.lwjgl.entities;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.model.TexturedModel;

public class NullEntity extends Entity{
	public NullEntity(Vector3f position, TexturedModel model) {
		TransformationComponent transform = new TransformationComponent();
		transform.position = position;
		ModelComponent modelComponent = new ModelComponent();
		modelComponent.model = model;
		this.components.add(transform);
		this.components.add(modelComponent);
	}
	@Override
	public void update(float delta) {
		
	}

}
