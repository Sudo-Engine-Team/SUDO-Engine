package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.texture.Texture2D;

public class TestScreen2D extends Screen {
	private Camera camera;
	private List<Light> suns = new ArrayList<>();
	private List<Quad2D> quads = new ArrayList<>();
 	public TestScreen2D(Render render, Loader loader, GameState state) {
		super(render, loader, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		suns.add(new Light(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0)));
		camera = new FirstPerson(new Vector3f(0, 0, 0));
		
		Texture2D texture = new Texture2D(loader.loadTexture("res/gui/testGUI/Full.png"), new Vector2f(0f, 0), new Vector2f((1f+1f/200f),1+1/2f));
		Quad2D quad2d = new Quad2D(loader, texture);
		quads.add(quad2d);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		render.render(suns, camera);
		render.processGUI(quads.get(0));
	}

	@Override
	public void dispose() {
		render.dispose();
		loader.destory();
	}

}
