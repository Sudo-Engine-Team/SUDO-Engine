package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.PlayerControlsComponent;
import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.water.WaterRender;
import site.root3287.lwjgl.water.WaterTile;

public class WaterScreen extends Screen {
	
	private WaterRender wr;
	private WaterTile water;
	private Camera camera;
	private Light sun;
	private List<Light> lights = new ArrayList<>();

	public WaterScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		camera = new FirstPerson(new Vector3f(0,10,0));
		Mouse.setGrabbed(camera.getComponent(PlayerControlsComponent.class).isGrabbed);
		sun = new Light(new Vector3f(1,100,0), new Vector3f(5, 5, 5));
		water = new WaterTile(0, 0, loader, new ModelTexture(loader.loadTexture("res/image/white.png")),60);
		lights.add(sun);
	}

	@Override
	public void update() {
		camera.update(DisplayManager.DELTA);
	}

	@Override
	public void render() {
		render.processWater(water);
		render.render(lights, camera);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
