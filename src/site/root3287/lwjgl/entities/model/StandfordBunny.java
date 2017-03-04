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
	
	public StandfordBunny(Loader loader) {
		ModelData data = OBJFileLoader.loadOBJ("res/model/standfordBunny/bunny.obj");
		ModelComponent model = new ModelComponent(
				new TexturedModel(
						loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), 
						new ModelTexture(loader.loadTexture("res/image/white.png"))));
		
		addComponent(model);
		addComponent(new TransformationComponent());
	}
	
	@Override
	public void update(float delta) {
		//TransformationComponent transform = getComponent(TransformationComponent.class);
	}

}
