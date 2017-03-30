package site.root3287.sudo.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.objConverter.ModelData;
import site.root3287.sudo.engine.objConverter.OBJFileLoader;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.physics.component.AABBComponent;
import site.root3287.sudo.texture.ModelTexture;

public class Cube extends Entity{
	public Cube(Loader loader){
		super();
		TransformationComponent transform = new TransformationComponent();
		addComponent(transform);
		ModelData data = OBJFileLoader.loadOBJ("res/model/Cube/cube.obj");
		ModelComponent model = new ModelComponent(new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture("res/image/white.png"))));
		addComponent(model);
		addComponent(new AABBComponent(id, new Vector3f(2, 2, 2)));
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
