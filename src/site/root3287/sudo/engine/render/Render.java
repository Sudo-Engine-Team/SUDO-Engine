package site.root3287.sudo.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo.component.ModelComponent;
import site.root3287.sudo.engine.frustum.Frustum;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.shader.shaders.Shader2D;
import site.root3287.sudo.shader.shaders.StaticShader;
import site.root3287.sudo.shader.shaders.TerrainShader;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.utils.LWJGLMaths;

public class Render {
	public static final float FOV = 90;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 10000;
	public static Vector4f colour = new Vector4f(0.5f, 0.5f, 0.5f, 1);

	private Matrix4f projectionMatrix;
	private Matrix4f orthographicMatrix;

	private StaticShader shader = new StaticShader();
	private EntityRender renderer;

	private TerrainRender terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private Render2D render2d;
	private Shader2D shader2d = new Shader2D();

	private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
	private List<Terrain> terrains = new ArrayList<>();
	private List<Quad2D> gui = new ArrayList<>();
	
	public static Frustum culler;
	
	public Render() {
		enableCulling();
		this.projectionMatrix = Camera.projectionMatrix;
		this.orthographicMatrix = LWJGLMaths.createOrthoMatrix();
		renderer = new EntityRender(shader, projectionMatrix);
		terrainRenderer = new TerrainRender(terrainShader, projectionMatrix);
		render2d = new Render2D(shader2d, this.orthographicMatrix);
	}
	public static void enableCulling(){
		Logger.log(LogLevel.DEBUG_RENDER, "Enabling Culling");
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		Logger.log(LogLevel.DEBUG_RENDER, "Disabling Culling");
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	public void render(List<Light> sun, Camera camera) {
		prepare();
		
		culler = camera.frustum;
		
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix();
		renderer.render(entities);
		shader.stop();
		
		terrainShader.start();
		terrainShader.loadLight(sun.get(0));
		terrainShader.loadViewMatrix();
		terrainRenderer.render(terrains);
		terrainShader.stop();
		
		shader2d.start();
		render2d.render(gui);
		shader2d.stop();
		
		terrains.clear();
		entities.clear();
	}

	public void processTerrain(Terrain terrain) {
		terrains.add(terrain);
	}

	public void processEntity(Entity entity) {
		TexturedModel entityModel = entity.getComponent(ModelComponent.class).model;
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void processGUI(Quad2D quad){
		this.gui.add(quad);
	}

	public void dispose() {
		shader.dispose();
		terrainShader.dispose();
		shader2d.dispose();
		render2d.dispose();
	}

	public void prepare() {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing render");
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(colour.x, colour.y, colour.z, colour.w);
	}

	public void setBackgroundColour(Vector4f vector4f) {
		colour = vector4f;
	}
}
