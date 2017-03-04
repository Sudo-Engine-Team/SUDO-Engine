package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.FirstPersonComponent;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.PerlinTerrain;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;

public class MainGame extends Screen{
	private List<Terrain> allTerrain = new ArrayList<Terrain>();
	private Light light;
	private Camera c;
	private List<Light> lights = new ArrayList<Light>();
	private Terrain[][] terrainForCollision;
	int frames = 0;
	long lastFPSTime;
	
	public MainGame(Render render, Loader loader) {
		super(render, loader);
	}

	
	@Override
	public void init() {
		this.light = new Light(new Vector3f(0, 1000, 0), new Vector3f(1, 1, 1));
		this.lights.add(light);
		this.c = new FirstPerson(new Vector3f(10, 10, 0));
		Mouse.setGrabbed(c.getComponent(FirstPersonComponent.class).isGrabbed);
		
		terrainForCollision = new Terrain[255][255];
		
		int seed = new Random().nextInt();
		
		for(int tX = 0; tX <= 5; tX++){
        	for(int tY = 0; tY <= 5; tY++){
        		System.out.println("Processing terrain for "+tX+" "+tY);
        		Terrain t1 = new PerlinTerrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass.png")
						), 
						64, 
						seed
        				);
        		allTerrain.add(t1);
        		terrainForCollision[tX][tY] = t1;
        	}
        }
	}

	@Override
	public void update() {
		//this.c.update(this.terrainForCollision, DisplayManager.DELTA);
	}

	@Override
	public void render() {
		for(Terrain t:allTerrain){
			this.render.processTerrain(t);
		}
		this.render.render(this.lights, this.c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
	}

}
