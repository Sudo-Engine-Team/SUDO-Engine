package site.root3287.sudo.engine.frustum;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.physics.collision.aabb.AABB;

public class Frustum {
	private static int NEAR = 0, FAR = 1, TOP =2, BOTTOM=3, LEFT=4, RIGHT=5;
	private FrustumPlane[] planes = new FrustumPlane[6];
	
	public Frustum(Matrix4f projectionMatrix) {
		for(int i = 0; i < 6; i++){
			planes[i] = new FrustumPlane();
		}
	}
	
	public void update(Matrix4f projectionMatrix){
		createPlanes(projectionMatrix);
	}
	private void createPlanes(Matrix4f projectionMatrix){
		planes[LEFT].normal.x = projectionMatrix.m03 + projectionMatrix.m00;
		planes[LEFT].normal.y = projectionMatrix.m13 + projectionMatrix.m10;
		planes[LEFT].normal.z = projectionMatrix.m23 + projectionMatrix.m20;
		planes[LEFT].distance = projectionMatrix.m33 + projectionMatrix.m30;
		
		planes[RIGHT].normal.x = projectionMatrix.m03 - projectionMatrix.m00;
		planes[RIGHT].normal.y = projectionMatrix.m13 - projectionMatrix.m10;
		planes[RIGHT].normal.z = projectionMatrix.m23 - projectionMatrix.m20;
		planes[RIGHT].distance = projectionMatrix.m33 - projectionMatrix.m30;
		
		planes[BOTTOM].normal.x = projectionMatrix.m03 + projectionMatrix.m01;
		planes[BOTTOM].normal.y = projectionMatrix.m13 + projectionMatrix.m11;
		planes[BOTTOM].normal.z = projectionMatrix.m23 + projectionMatrix.m21;
		planes[BOTTOM].distance = projectionMatrix.m33 + projectionMatrix.m31;
		
		planes[TOP].normal.x = projectionMatrix.m03 - projectionMatrix.m01;
		planes[TOP].normal.y = projectionMatrix.m13 - projectionMatrix.m11;
		planes[TOP].normal.z = projectionMatrix.m23 - projectionMatrix.m21;
		planes[TOP].distance = projectionMatrix.m33 - projectionMatrix.m31;
		
		planes[NEAR].normal.x = projectionMatrix.m03 + projectionMatrix.m02;
		planes[NEAR].normal.y = projectionMatrix.m13 + projectionMatrix.m12;
		planes[NEAR].normal.z = projectionMatrix.m23 + projectionMatrix.m22;
		planes[NEAR].distance = projectionMatrix.m33 + projectionMatrix.m32;
		
		planes[FAR].normal.x = projectionMatrix.m03 - projectionMatrix.m02;
		planes[FAR].normal.y = projectionMatrix.m13 - projectionMatrix.m12;
		planes[FAR].normal.z = projectionMatrix.m23 - projectionMatrix.m22;
		planes[FAR].distance = projectionMatrix.m33 - projectionMatrix.m32;
		for(FrustumPlane p : planes){
			float length = 1/p.normal.length();
			p.normal.normalise();
			p.distance *= length;
		}
	}
	
	public boolean isPointInFrustum(Vector3f point){
		for(FrustumPlane p :planes){
			if(p.distanceToPoint(point) < 0){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAABBinFrustum(AABB box){
		 boolean result = true;

		    for (FrustumPlane plane : planes)
		    {
		        if (plane.distanceToPoint(box.getVP(plane.normal)) < 0)
		        {
		            return false;
		        }
		        else if (plane.distanceToPoint(box.getVN(plane.normal)) < 0)
		        {
		            result = true;
		        }
		    }

		return result;
	}
}
