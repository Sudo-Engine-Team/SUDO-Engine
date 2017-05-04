package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
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
import site.root3287.sudo.event.EventDispatcher;
import site.root3287.sudo.event.type.EventType;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.terrain.perlin.PerlinWorld;

public class TerrainScreen extends Screen{

	private List<Light> lights = new ArrayList<Light>();
	private Light light;
	private Camera c;
	private PerlinWorld world;
	private EventDispatcher generateTerrainDispatcher = new EventDispatcher(EventType.GENERATE_TERRAIN);
	
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
		world = new PerlinWorld(loader, c);
	}

	@Override
	public void update() {
		this.c.update(DisplayManager.DELTA);
		world.updateCamera(c);
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		for(Terrain t: world.getTerrain()){
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
}
