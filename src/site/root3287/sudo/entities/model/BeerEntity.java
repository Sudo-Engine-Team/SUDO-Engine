package site.root3287.sudo.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.objConverter.ModelData;
import site.root3287.sudo.engine.objConverter.OBJFileLoader;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.texture.ModelTexture;

public class BeerEntity extends Entity {
	
	public BeerEntity(Loader loader) {
		TransformationComponent transform = new TransformationComponent();
		transform.position = new Vector3f(0, 0, 0);
		transform.scale = 1;
		transform.scale = 0.0025f;
		addComponent(transform);
		
		ModelData modelData = OBJFileLoader.loadOBJ("res/model/Beer/Beer.obj");
		ModelComponent model = new ModelComponent(
				new TexturedModel(loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices()), new ModelTexture(loader.loadTexture("res/model/Beer/BeerTest.png")))
		);
		addComponent(model);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
