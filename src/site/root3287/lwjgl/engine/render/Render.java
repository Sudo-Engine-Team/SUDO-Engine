package site.root3287.lwjgl.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.Entities.Camera;
import site.root3287.lwjgl.Entities.Entity;
import site.root3287.lwjgl.Entities.Light;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.shader.shaders.StaticShader;
import site.root3287.lwjgl.shader.shaders.TerrainShader;
import site.root3287.lwjgl.terrain.Terrain;

public class Render {
	private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
     
    private Matrix4f projectionMatrix;
     
    private StaticShader shader = new StaticShader();
    private EntityRender renderer;
     
    private TerrainRender terrainRenderer;
    private TerrainShader terrainShader = new TerrainShader();
     
     
    private Map<TexturedModel,List<Entity>> entities = new HashMap<TexturedModel,List<Entity>>();
    private List<Terrain> terrains = new ArrayList<Terrain>();
     
    public Render(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        renderer = new EntityRender(shader,projectionMatrix);
        terrainRenderer = new TerrainRender(terrainShader,projectionMatrix);
    }
     
    public void render(Light sun,Camera camera){
        prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        terrainShader.start();
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();
        terrains.clear();
        entities.clear();
    }
     
    public void processTerrain(Terrain terrain){
        terrains.add(terrain);
    }
     
    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);        
        }
    }
     
    public void dispose(){
        shader.dispose();
        terrainShader.dispose();
    }
     
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.49f, 89f, 0.98f, 1);
    }
     
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
