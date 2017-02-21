package site.root3287.lwjgl.component;

import site.root3287.lwjgl.model.TexturedModel;

public abstract class Component {
	protected boolean isActive = true;
	protected String name;
	public Component(String name) {
		this.name = name;
		start();
	}
	protected abstract void start(); // Start the varibles that need to be started.
	public abstract void update(float delta);
	public abstract void destroy();
	public String getName(){
		return this.name.toLowerCase();
	}
}
