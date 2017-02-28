package site.root3287.lwjgl.entities;

import java.util.ArrayList;
import java.util.List;

import site.root3287.lwjgl.component.Component;

public abstract class Entity {
	protected List<Component> components = new ArrayList<Component>();
	
	public Entity() {
		super();
	}
	public abstract void update(float delta);
	
	public void addComponent(Component c){
		this.components.add(c);
	}
	
	public Component getComponent(String component){
		for(Object c : this.components.toArray()){
			if(component == ((Component) c).getName()){
				return (Component) c;
			}
		}
		return null;
	}
}
