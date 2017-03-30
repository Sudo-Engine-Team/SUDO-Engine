package site.root3287.sudo.engine.frustum;

import org.lwjgl.util.vector.Vector3f;

public class FrustumPlane {
	public float distance = 0;
	public Vector3f normal = new Vector3f(), point = new Vector3f();
	public float distanceToPoint(Vector3f point){
		return Vector3f.dot(point, normal)+distance;
	}
}
