package site.root3287.sudo.entities;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.model.TexturedModel;

public class NullEntity extends Entity{
	public NullEntity(Vector3f position, TexturedModel model) {
		TransformationComponent transform = new TransformationComponent();
		transform.position = position;
		ModelComponent modelComponent = new ModelComponent(model);
		addComponent(transform);
		addComponent(modelComponent);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
