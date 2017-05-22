package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.terrain.perlin.PerlinWorld;
import site.root3287.sudo.texture.Texture2D;

public class TerrainScreen extends Screen{

	private List<Light> lights = new ArrayList<Light>();
	private Light light;
	private Camera c;
	float TerrainTimeout = 0;
	
	private Quad2D gui = new Quad2D(this.loader, new Texture2D(loader.loadTexture("res/image/white.png"), new Vector2f(0, 0), new Vector2f(1f, 0.5f)));

	private PerlinWorld world;
	public TerrainScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		Display.setVSyncEnabled(false);
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
		if(TerrainTimeout > 20 / (c.getComponent(PlayerControlsComponent.class).flySpeed)){
			world.update(c);
			TerrainTimeout = 0;
		}
		TerrainTimeout+=DisplayManager.DELTA;
		this.c.update(DisplayManager.DELTA);
		if(Keyboard.isKeyDown(Keyboard.KEY_H)){
			DisplayManager.setScreen(new Splash(this.render, this.loader, this.state));
		}
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		this.render.processGUI(gui);
		for(Terrain t: world.getTerrain()){
			this.render.processTerrain(t);
		}
		this.render.render(this.lights, this.c);
	}

	@Override
	public void dispose() {
	}
}
