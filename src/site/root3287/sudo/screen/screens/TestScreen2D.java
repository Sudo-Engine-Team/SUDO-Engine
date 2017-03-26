package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.screen.Screen;

public class TestScreen2D extends Screen {
	private Camera camera;
	private List<Light> suns = new ArrayList<>();
	public TestScreen2D(Render render, Loader loader, GameState state) {
		super(render, loader, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		suns.add(new Light(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0)));
		camera = new FirstPerson(new Vector3f(0, 0, 0));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		render.render(suns, camera);
	}

	@Override
	public void dispose() {
		render.dispose();
		loader.destory();
	}

}
