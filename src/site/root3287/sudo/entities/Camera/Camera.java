package site.root3287.sudo.entities.Camera;

import java.util.HashMap;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.frustum.Frustum;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.utils.LWJGLMaths;

public abstract class Camera extends Entity{
	public static Matrix4f projectionMatrix = LWJGLMaths.createProjectionMatrix();
	public static Matrix4f viewMatrix;
	public static Matrix4f projectionView;
	public Frustum frustum;
	public Camera(Vector3f position) {
		TransformationComponent transform = new TransformationComponent();
		transform.position = position;
		addComponent(transform);
		viewMatrix = LWJGLMaths.createViewMatrix(this);
		
		projectionView = new Matrix4f();
		Matrix4f.mul(projectionMatrix, viewMatrix, projectionView);
		frustum = new Frustum(projectionView);
	}

	public abstract void update(float delta);
	public abstract void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta);
}
