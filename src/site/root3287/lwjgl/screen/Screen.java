package site.root3287.lwjgl.screen;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;

public abstract class Screen {
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void dispose();
	
	protected Render render;
	protected Loader loader;
	
	public Screen(){
		this.render = new Render();
		this.loader = new Loader();
	}
	protected Screen(Render render, Loader loader) {
		this.render = render;
		this.loader = loader;
	}
}
