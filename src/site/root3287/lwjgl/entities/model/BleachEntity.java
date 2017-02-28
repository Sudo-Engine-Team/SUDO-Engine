package site.root3287.lwjgl.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class BleachEntity extends Entity{
	private static Loader loader;
	private static final TexturedModel MODEL = new TexturedModel(OBJLoader.loadObjModel("res/model/Bleach/Bleach.obj", loader), new ModelTexture(loader.loadTexture("{INSERT TEXTURE}")));
	public BleachEntity() {
		super();
		TransformationComponent transform = new TransformationComponent();
		transform.position = new Vector3f(0,0,0);
		ModelComponent model = new ModelComponent();
		model.model = MODEL;
		this.addComponent(transform);
		this.addComponent(model);
	}

	@Override
	public void update(float delta) {
		
	}

}
