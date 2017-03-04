package site.root3287.lwjgl.entities.model;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class CanEntity extends Entity {
	private Loader loader;
	public CanEntity(Loader loader) {
		this.loader = loader;
		TransformationComponent transform = new TransformationComponent();
		ModelComponent model = new ModelComponent(new TexturedModel(OBJLoader.loadObjModel("res/model/Can/Can.obj", loader), new ModelTexture(loader.loadTexture("res/model/Can/Can.obj"))));
		this.addComponent(model);
		this.addComponent(transform);
	}

	@Override
	public void update(float delta) {
		
	}
}
