package site.root3287.lwjgl.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.root3287.lwjgl.Entities.Camera;
import site.root3287.lwjgl.Entities.Entity;
import site.root3287.lwjgl.Entities.Light;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.shader.StaticShader;

public class EntityRender {
	private StaticShader shader = new StaticShader();
	private Render render = new Render(shader);
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	public void render(Light sun, Camera camera){
		render.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		render.render(entities);
		shader.stop();
		entities.clear();
	}
	
	public void processEntity(Entity entity){
		List<Entity> batch = entities.get(entity.getModel());
		if(batch!=null){
			batch.add(entity);
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entity.getModel(), newBatch);
		}
	}
	
	public void dispose(){
		shader.dispose();
	}
}
