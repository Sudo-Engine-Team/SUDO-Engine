package site.root3287.lwjgl.screen;

import site.root3287.lwjgl.engine.Disposeable;
import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;

public abstract class Screen implements Disposeable{
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void dispose();
	
	protected Render render;
	protected Loader loader;
	protected GameState state;
	
	public Screen(Render render, Loader loader, GameState state) {
		this.render = render;
		this.loader = loader;
		this.state = state;
	}
}
