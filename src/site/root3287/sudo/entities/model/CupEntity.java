package site.root3287.sudo.entities.model;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.objConverter.ModelData;
import site.root3287.sudo.engine.objConverter.OBJFileLoader;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.texture.ModelTexture;

public class CupEntity extends Entity {

	public CupEntity(Loader loader) {
		TransformationComponent transform = new TransformationComponent();
		ModelData data = OBJFileLoader.loadOBJ("res/model/Cup/Cup.obj");
		ModelComponent model = new ModelComponent(new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture("res/model/Cup/Cup.png"))));
		this.addComponent(transform);
		this.addComponent(model);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
