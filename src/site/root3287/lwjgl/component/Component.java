package site.root3287.lwjgl.component;

public abstract class Component {
	protected boolean isActive = true;
	protected String name;
	public abstract void update(float delta);
	public abstract void destroy();
	public String getName(){
		return this.name.toLowerCase();
	}
	public boolean isActive(){
		return isActive;
	}
}
