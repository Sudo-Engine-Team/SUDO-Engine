package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

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
import site.root3287.sudo.terrain.newPerlin.PerlinWorld;

public class TerrainScreen2 extends Screen{
	
	private List<Light> lights = new ArrayList<Light>();
	private Light light;
	private Camera c;
	float TerrainTimeout = 0;
	private PerlinWorld world;
	
	public TerrainScreen2(Render render, Loader loader, GameState state) {
		super(render, loader, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		this.c = new FirstPerson(new Vector3f(0, 0, 0));
		Mouse.setGrabbed(this.c.getComponent(PlayerControlsComponent.class).isGrabbed);
		this.light = new Light(new Vector3f(c.getComponent(TransformationComponent.class).position.x, 100, c.getComponent(TransformationComponent.class).position.x), 
				new Vector3f(1.25f, 1.25f, 1.25f));
		this.lights.add(this.light);
		this.world = new PerlinWorld(loader);
		world.init();
	}

	@Override
	public void update() {
		world.update(c);
		this.c.update(DisplayManager.DELTA);
	}

	@Override
	public void render() {
		for(Terrain t: world.getTerrain()){
			this.render.processTerrain(t);
		}
		this.render.render(this.lights, this.c);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
