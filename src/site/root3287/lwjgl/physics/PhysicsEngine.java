package site.root3287.lwjgl.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.physics.component.PhysicsComponent;

public class PhysicsEngine {
	private List<UUID> entity = new ArrayList<UUID>();
	
	public void addEntity(Entity e){
		if(!e.hasComponent(PhysicsComponent.class)){
			throw new IllegalAccessError("This entity don't have a physics object");
		}
		this.entity.add(e.getID());
	}
	
	public void simulate(float delta){
		for(UUID id : entity){
			Entity.getComponent(id, PhysicsComponent.class).update(delta);
		}
	}
}
