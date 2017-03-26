package site.root3287.sudo.physics.component;

import java.util.UUID;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.physics.collision.aabb.AABB;

public class AABBComponent extends PhysicsComponent{
	public Vector3f minExtent, maxExtent;
	public AABB aabb;
	public AABBComponent(UUID id) {
		super(id);
		this.aabb = new AABB(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
	}
	
	public AABBComponent(UUID id, Vector3f minExtent, Vector3f maxExtent) {
		super(id);
		this.minExtent = minExtent;
		this.maxExtent = maxExtent;
		this.aabb = new AABB(minExtent, maxExtent);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
