package site.root3287.lwjgl.entities;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.Component;
import site.root3287.lwjgl.model.TexturedModel;

public abstract class Entity {
	protected Map<String, Component> components = new HashMap<String, Component>();
	
	public Entity() {
		super();
	}
	public abstract void update(float delta);
	
	public void addComponent(Component c){
		this.components.put(c.getName(), c);
	}
	
	public Component getComponent(String component){
		return (this.components.containsKey(component.toLowerCase()))? this.components.get(component) : null;
	}
}
