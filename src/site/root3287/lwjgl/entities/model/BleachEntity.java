package site.root3287.lwjgl.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class BleachEntity extends Entity{
	private static Loader loader;
	private static final TexturedModel MODEL = new TexturedModel(OBJLoader.loadObjModel("res/model/Bleach/Bleach.obj", loader), new ModelTexture(loader.loadTexture("{INSERT TEXTURE}")));
	public BleachEntity(Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(MODEL, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
