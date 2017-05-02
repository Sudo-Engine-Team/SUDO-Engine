package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.audio.Audio;
import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.logger.Logger;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.terrain.perlin.PerlinTerrain;
import site.root3287.sudo.texture.ModelTexture;

public class TerrainScreen extends Screen{

	private List<Light> lights = new ArrayList<Light>();
	private List<Terrain> terrain = new ArrayList<>();
	private HashMap<Integer, HashMap<Integer, Terrain>> heights =new HashMap<>();
	private Light light;
	private Camera c;
	
	public TerrainScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		Audio.init();
		this.c = new FirstPerson(new Vector3f(0, 0, 0));
		Mouse.setGrabbed(this.c.getComponent(PlayerControlsComponent.class).isGrabbed);
		this.light = new Light(new Vector3f(c.getComponent(TransformationComponent.class).position.x, 100, c.getComponent(TransformationComponent.class).position.x), 
				new Vector3f(1.25f, 1.25f, 1.25f));
		this.lights.add(this.light);
		
		PerlinTerrain t = new PerlinTerrain(0, 0, loader, new ModelTexture(loader.loadTexture("res/image/grass-plane.png")), 128, 123);
		terrain.add(t);
		HashMap<Integer, Terrain> batch = new HashMap<>();
		batch.put(0, t);
		heights.put(0, batch);
	}

	@Override
	public void update() {
		int chunkX = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.x / Terrain.SIZE);
		int chunkY = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.z / Terrain.SIZE);
		
		Vector2f[] position = new Vector2f[9];
		int k = 0;
		for(int i=-1; i<1; i++){
			for(int j =-1; j <1; j++){
				position[k] = new Vector2f(chunkX+i, chunkY+j);
				k++;
			}
		}
		
		
		
		this.c.update(heights, DisplayManager.DELTA);
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		for(Terrain t: terrain){
			this.render.processTerrain(t);
		}
		this.render.render(this.lights, this.c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		Audio.dispose();
	}
	
	public void addToWorld(Terrain t){
		HashMap<Integer, Terrain> batch;
		if(heights.containsKey(t.getGridX())){
			batch = heights.get(t.getGridX());
		}else{
			batch = new HashMap<>();
		}
		if(!terrain.contains(t)){
			batch.put(t.getGridZ(), t);
			terrain.add(t);
		}
	}
	
	public void removeTerrain(int x, int z){
		int i = 0;
		for(Terrain t : terrain){
			if(terrain.get(i).getGridX() == x && terrain.get(i).getGridZ() == z){
				terrain.remove(i);
			}
			i++;
		}
		loader.removeVAO(heights.get(x).get(z).getModel().getVaoID());
		loader.removeTexture(heights.get(x).get(z).getTexture().getTextureID());
		heights.get(x).remove(z);
	}
	
}
