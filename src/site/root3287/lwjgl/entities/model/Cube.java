package site.root3287.lwjgl.entities.model;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.ModelComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.objConverter.ModelData;
import site.root3287.lwjgl.engine.objConverter.OBJFileLoader;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.physics.component.AABBComponent;
import site.root3287.lwjgl.texture.ModelTexture;

public class Cube extends Entity{
	public Cube(Loader loader){
		super();
		TransformationComponent transform = new TransformationComponent();
		addComponent(transform);
		ModelData data = OBJFileLoader.loadOBJ("res/model/Cube/cube.obj");
		ModelComponent model = new ModelComponent(new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture("res/image/white.png"))));
		addComponent(model);
		addComponent(new AABBComponent(this.id, new Vector3f(-2, 0, -2), new Vector3f(2, 2, 2)));
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
