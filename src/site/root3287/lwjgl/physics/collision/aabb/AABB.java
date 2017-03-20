package site.root3287.lwjgl.physics.collision.aabb;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.physics.collision.IntersectData;
import site.root3287.lwjgl.utils.LWJGLMaths;

public class AABB {
	private Vector3f minExtent, maxExtent;
	
	public AABB(Vector3f minExtent, Vector3f maxExtent) {
		this.minExtent = minExtent;
		this.maxExtent = maxExtent;
	}

	public Vector3f getMinExtent() {
		return minExtent;
	}

	public void setMinExtent(Vector3f minExtent) {
		this.minExtent = minExtent;
	}

	public Vector3f getMaxExtent() {
		return maxExtent;
	}

	public void setMaxExtent(Vector3f maxExtent) {
		this.maxExtent = maxExtent;
	}
	
	public IntersectData isIntersecting(AABB other){
		Vector3f distance1 = new Vector3f();
		Vector3f distance2 = new Vector3f();
		Vector3f.sub(other.minExtent, this.maxExtent, distance1);
		Vector3f.sub(this.minExtent, other.maxExtent, distance2);
		Vector3f distance = LWJGLMaths.maxVector3f(distance1, distance2);
		float maxDistance = LWJGLMaths.maxVector3fItem(distance);
		return new IntersectData(maxDistance < 0, maxDistance);
	}
}
