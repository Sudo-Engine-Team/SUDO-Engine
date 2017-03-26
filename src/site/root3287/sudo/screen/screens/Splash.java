package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.engine.render.Render2D;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.Camera2D;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.texture.Texture2D;

public class Splash extends Screen {
	private Quad2D quad;
	private Render2D render2d;
	private Camera camera;
	private List<Light> suns = new ArrayList<>();
	public Splash(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		camera = new Camera2D(new Vector3f(0,0,0));
		Texture2D texture = new Texture2D(loader.loadTexture("res/image/white.png"), new Vector2f(0,0), new Vector2f(1f,1f));
		quad = new Quad2D(loader, texture);
		
		Light light = new Light(new Vector3f(0,0,0), new Vector3f(0,0,0));
		suns.add(light);
		
		render.setBackgroundColour(new Vector4f(0,0,0,0));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		render.processGUI(quad);
		render.render(suns, camera);
	}

	@Override
	public void dispose() {
		loader.destory();
		render.dispose();
	}

}
