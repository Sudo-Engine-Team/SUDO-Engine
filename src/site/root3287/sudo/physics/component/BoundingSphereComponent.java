package site.root3287.sudo.physics.component;

import java.util.UUID;

import site.root3287.sudo.physics.collision.boundingSphere.BoundingSphere;

public class BoundingSphereComponent extends PhysicsComponent{
	float radius;
	public BoundingSphereComponent(UUID id, float radius) {
		super(id);
		this.radius = radius;
	}

	@Override
	public void update(float delta) {
	}
	
	public BoundingSphere getBoundingSphere(){
		return new BoundingSphere(this.physics.position, radius);
	}

}
