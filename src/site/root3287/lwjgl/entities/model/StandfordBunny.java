package site.root3287.lwjgl.entities.model;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.objConverter.ModelData;
import site.root3287.lwjgl.engine.objConverter.OBJFileLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class StandfordBunny extends Entity{
	private Loader loader;
	public StandfordBunny(Loader loader) {
		this.loader = loader;
		ModelData data = OBJFileLoader.loadOBJ("res/model/standfordBunny/bunny.obj");
		ModelComponent model = new ModelComponent(
				new TexturedModel(
						loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), 
						new ModelTexture(loader.loadTexture("res/image/white.png"))));
		TransformationComponent transform = new TransformationComponent();
		
		addComponent(model);
		addComponent(transform);
	}
	
	@Override
	public void update(float delta) {
	}

}
