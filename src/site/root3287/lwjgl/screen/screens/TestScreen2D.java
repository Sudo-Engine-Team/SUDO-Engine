package site.root3287.lwjgl.screen.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.fontMeshCreator.FontType;
import site.root3287.lwjgl.fontMeshCreator.GUIText;
import site.root3287.lwjgl.input.objects.UIText;
import site.root3287.lwjgl.screen.Screen;

public class TestScreen2D extends Screen {
	private GUIText text;
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
		UIText.init(loader);
		text = new GUIText("Hello World!", 12, new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt")), new Vector2f(0,0), 12, true);
		//UIText.loadText(text);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		render.render(suns, camera);
		UIText.render();
	}

	@Override
	public void dispose() {
		render.dispose();
		loader.destory();
		UIText.dispose();
	}

}
