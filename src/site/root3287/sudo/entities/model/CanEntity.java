package site.root3287.sudo.entities.model;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.OBJLoader;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.texture.ModelTexture;

public class CanEntity extends Entity {
	public CanEntity(Loader loader) {
		TransformationComponent transform = new TransformationComponent();
		ModelComponent model = new ModelComponent(new TexturedModel(OBJLoader.loadObjModel("res/model/Can/Can.obj", loader), new ModelTexture(loader.loadTexture("res/model/Can/Can.obj"))));
		this.addComponent(model);
		this.addComponent(transform);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
