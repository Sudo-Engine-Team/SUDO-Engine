package site.root3287.sudo.entities.model;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.objConverter.ModelData;
import site.root3287.sudo.engine.objConverter.OBJFileLoader;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.texture.ModelTexture;

public class BleachEntity extends Entity{
	private Loader loader;
	public BleachEntity(Loader loader) {
		this.loader = loader;
		ModelData data = OBJFileLoader.loadOBJ("res/model/Bleach/Bleach.obj");
		ModelComponent model = new ModelComponent(
				new TexturedModel(
						loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), 
						new ModelTexture(loader.loadTexture("res/model/Bleach.png"))
				)
		);
		TransformationComponent transform = new TransformationComponent();
		
		addComponent(model);
		addComponent(transform);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		
	}
}
