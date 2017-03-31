package site.root3287.sudo.physics.collision.aabb;

import org.lwjgl.util.vector.Vector3f;

public class AABB {
	public Vector3f point;
	public Vector3f diemension;
	
	public AABB(Vector3f point, Vector3f diemension) {
		this.point = point;
		this.diemension = diemension;
	}
	
	public void update(Vector3f position){
		this.point = position;
	}
	
	public Vector3f getVN(Vector3f normal){
		Vector3f res =  new Vector3f(point.x, point.y,point.z);

	    if (normal.x < 0)
	    {
	        res.x += diemension.x;
	    }
	    if (normal.y < 0)
	    {
	        res.y += diemension.y;
	    }
	        if (normal.z < 0)
	    {
	        res.z += diemension.z;
	    }

	 return res;
	}
	public Vector3f getVP(Vector3f normal){
		Vector3f res = new Vector3f(point.x, point.y,point.z);

	    if (normal.x > 0)
	    {
	        res.x += diemension.x;
	    }
	    if (normal.y > 0)
	    {
	        res.y += diemension.y;
	    }
	        if (normal.z > 0)
	    {
	        res.z += diemension.z;
	    }

	return res;
	}
	@Override
	public String toString() {
		return this.point+" "+this.diemension; 
	}
}
