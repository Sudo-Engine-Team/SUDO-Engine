package site.root3287.lwjgl.entities.model;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class CupEntity extends Entity {
	
	private Loader loader;

	public CupEntity(Loader loader) {
		this.loader = loader;
		TransformationComponent transform = new TransformationComponent();
		ModelComponent model = new ModelComponent();
		model.setTexturedModel(new TexturedModel(OBJLoader.loadObjModel("res/model/Cup/Cup.obj", loader), new ModelTexture(loader.loadTexture("res/model/Cup/Cup.png"))));
		this.addComponent(transform);
		this.addComponent(model);
	}

	@Override
	public void update(float delta) {
		
	}
}
