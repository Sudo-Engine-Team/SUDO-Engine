package site.root3287.sudo.physics.component;

import java.util.UUID;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.physics.collision.aabb.AABB;

public class AABBComponent extends PhysicsComponent{
	public AABB aabb;
	public AABBComponent(UUID id) {
		super(id);
		if(!Entity.hasComponent(id, TransformationComponent.class)){
			throw new IllegalArgumentException("The object does not have a transformation component!");
		}
		this.aabb = new AABB(Entity.getComponent(id, TransformationComponent.class).position, new Vector3f(1, 1, 1));
	}
	
	public AABBComponent(UUID id, Vector3f maxExtent) {
		super(id);
		this.aabb = new AABB(Entity.getComponent(id, TransformationComponent.class).position, maxExtent);
	}

	@Override
	public void update(float delta) {
		this.aabb.update(Entity.getComponent(id, TransformationComponent.class).position);
	}

}
