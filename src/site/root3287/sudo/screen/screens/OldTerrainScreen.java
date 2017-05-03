package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Mouse;
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
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.terrain.perlin.PerlinTerrain;
import site.root3287.sudo.texture.ModelTexture;

public class OldTerrainScreen extends Screen {

	private List<Light> lights = new ArrayList<Light>();
	private List<Terrain> terrain = new ArrayList<>();
	private HashMap<Integer, HashMap<Integer, Terrain>> heights =new HashMap<>();
	private Camera c;
	private static int WORLD_SIZE = 5;
	
	public OldTerrainScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		Audio.init();
		this.c = new FirstPerson(new Vector3f(0, 0, 0));
		Light light = new Light(new Vector3f(c.getComponent(TransformationComponent.class).position.x, 100, c.getComponent(TransformationComponent.class).position.x), 
				new Vector3f(1.25f, 1.25f, 1.25f));
		lights.add(light);
		Mouse.setGrabbed(this.c.getComponent(PlayerControlsComponent.class).isGrabbed);
		
		for(int i=0; i<4;i++){
			for(int j =0; j <4; j++){
				PerlinTerrain t = new PerlinTerrain(i, j, loader, new ModelTexture(loader.loadTexture("res/image/grass-plane.png")), 128, 123);
				terrain.add(t);
				HashMap<Integer, Terrain> batch = new HashMap<>();
				batch.put(j, t);
				heights.put(i, batch);
			}
		}
	}

	@Override
	public void update() {
		this.c.update(DisplayManager.DELTA);
	}

	@Override
	public void render() {
		for(Terrain t : terrain){
			render.processTerrain(t);
		}
		render.render(lights, c);
	}

	@Override
	public void dispose() {
		Audio.dispose();
		render.dispose();
		loader.destory();
	}

}
