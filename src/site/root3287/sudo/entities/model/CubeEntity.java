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

public class CubeEntity extends Entity{
	private static TexturedModel saved;
	public CubeEntity(Loader loader){
		super();
		TransformationComponent transform = new TransformationComponent();
		addComponent(transform);
		if(saved == null){
			ModelData data = OBJFileLoader.loadOBJ("res/model/Cube/cube.obj");
			saved = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture("res/image/white.png")));
			ModelComponent model = new ModelComponent(saved);
			addComponent(model);
		}else{
			ModelComponent model = new ModelComponent(saved);
			addComponent(model);
		}
		addComponent(new AABBComponent(id, new Vector3f(2, 2, 2)));
	}
	
	public CubeEntity(Loader loader, Vector3f position){
		this(loader);
		getComponent(TransformationComponent.class).position = position;
		getComponent(AABBComponent.class).aabb.point = position;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
