package site.root3287.lwjgl.engine.frustum;

import org.lwjgl.util.vector.Vector3f;

public class Plane {

	private Vector3f origin;
	private Vector3f normal;
	private float constant;
	
	public Plane(){
	}

	public Plane(Vector3f point0, Vector3f point1, Vector3f point2) {
		setPlane(point0, point1, point2);
	}
	
	public Plane(Vector3f normal,Vector3f origin){
		this.normal = normal;
		this.origin = origin;
		constant = -(normal.x*origin.x+normal.y*origin.y+normal.z*origin.z);
	}
	
	public Vector3f getNormal(){
		return normal;
	}
	
	public float getConstant(){
		return constant;
	}
	
	public void setPlane(Vector3f point0, Vector3f point1, Vector3f point2){
		normal = Vector3f.cross(Vector3f.sub(point2, point0, null),
				Vector3f.sub(point1, point0, null), null);
		normal.normalise();
		origin = new Vector3f(point0.x,point0.y,point0.z);
		constant = -(normal.x*origin.x+normal.y*origin.y+normal.z*origin.z);
	}
	
	public float getSignedDistance(Vector3f point){
		return Vector3f.dot(normal, point)+constant;
	}
	
	public boolean isFrontFacingTo(Vector3f direction){
		float dot = Vector3f.dot(normal, direction);
		return (dot<=0);
	}
	
}
