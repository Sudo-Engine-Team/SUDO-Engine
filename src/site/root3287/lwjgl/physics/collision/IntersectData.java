package site.root3287.lwjgl.physics.collision;

public class IntersectData {
	private boolean isIntersecting;
	private float distance;
	
	public IntersectData(boolean intersecting, float distance){
		this.distance = distance;
		this.isIntersecting = intersecting;
	}
	
	public boolean isIntersecting() {
		return isIntersecting;
	}
	public void setIntersect(boolean isIntersect) {
		this.isIntersecting = isIntersect;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	
}
