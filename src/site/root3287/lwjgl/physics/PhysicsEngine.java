package site.root3287.lwjgl.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.physics.collision.IntersectData;
import site.root3287.lwjgl.physics.collision.boundingSphere.BoundingSphere;
import site.root3287.lwjgl.physics.component.BoundingSphereComponent;
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
	public void collision(){
		
	}
	public void collisionDetection(){
		for(int i = 0; i < entity.size(); i++){
			for(int j = i+1; j < entity.size(); j++){
				if(Entity.hasComponent(entity.get(i), BoundingSphereComponent.class)){
					IntersectData intersection = Entity.getComponent(
							entity.get(i), BoundingSphere.class).isIntersecting(
									Entity.getComponent(entity.get(j), BoundingSphere.class)
					);
					if(intersection.isIntersecting()){
						Entity.getComponent(entity.get(i), TransformationComponent.class).velocity = new Vector3f(
								Entity.getComponent(entity.get(i), TransformationComponent.class).velocity.x *-1,
								Entity.getComponent(entity.get(i), TransformationComponent.class).velocity.y *-1,
								Entity.getComponent(entity.get(i), TransformationComponent.class).velocity.z * -1
						);
						Entity.getComponent(entity.get(j), TransformationComponent.class).velocity = new Vector3f(
								Entity.getComponent(entity.get(j), TransformationComponent.class).velocity.x *-1,
								Entity.getComponent(entity.get(j), TransformationComponent.class).velocity.y *-1,
								Entity.getComponent(entity.get(j), TransformationComponent.class).velocity.z * -1
						);
					}
				}
			}
		}
	}
}
