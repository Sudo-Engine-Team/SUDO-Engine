package site.root3287.sudo.engine.frustum;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.utils.LWJGLMaths;


public class Frustum {

	public static final int VERTEX_COUNT = 8;

	private Vector4f[] originalVertices = new Vector4f[VERTEX_COUNT];
	private Vector4f[] frustumVertices = new Vector4f[VERTEX_COUNT];

	private Camera camera;

	private float frustumLength;
	private float farWidth, farHeight, nearWidth, nearHeight;
	private Matrix4f cameraTransform = new Matrix4f();

	public Frustum(Camera camera) {
		this.camera = camera;
		this.frustumLength = Render.FAR_PLANE;
		initFrusutmVertices();
		calculateOriginalVertices();
		update();
	}
	
	public Frustum(){}

	public void update() {
		updateCameraTransform();
		for (int i = 0; i < frustumVertices.length; i++) {
			Matrix4f.transform(cameraTransform, originalVertices[i], frustumVertices[i]);
		}
	}

	public void update(float limitedDistance) {
		if (frustumLength != limitedDistance) {
			this.frustumLength = limitedDistance;
			calculateOriginalVertices();
		}
		update();
	}
	
	public void update(Vector4f[] newVertices) {
		this.frustumVertices = newVertices;
	}

	public Vector3f getVertex(int i){
		return new Vector3f(frustumVertices[i]);
	}

	private void calculateWidthsAndHeights() {
		farHeight = (float) (frustumLength * Math.tan(Math.toRadians(Render.FOV/2f)));
		nearHeight = (float) (Render.NEAR_PLANE * Math.tan(Math.toRadians(Render.FOV/2f)));
		farWidth = farHeight * DisplayManager.getAspectRatio();
		nearWidth = nearHeight * DisplayManager.getAspectRatio();
	}

	private void calculateOriginalVertices() {
		calculateWidthsAndHeights();
		for (int i = 0; i < originalVertices.length; i++) {
			originalVertices[i] = getVertex((i / 4) % 2 == 0, i % 2 == 0, (i / 2) % 2 == 0);
		}
	}

	private Vector4f getVertex(boolean isNear, boolean positiveX, boolean positiveY) {
		Vector4f vertex = new Vector4f();
		vertex.z = isNear ? -Render.NEAR_PLANE : -frustumLength;
		Vector2f sizes = isNear ? new Vector2f(nearWidth, nearHeight) : new Vector2f(farWidth, farHeight);
		vertex.x = positiveX ? sizes.x : -sizes.x;
		vertex.y = positiveY ? sizes.y : -sizes.y;
		vertex.w = 1;
		return vertex;
	}

	private void initFrusutmVertices() {
		for (int i = 0; i < frustumVertices.length; i++) {
			frustumVertices[i] = new Vector4f();
		}
	}

	private void updateCameraTransform() {
		cameraTransform.setIdentity();
		cameraTransform.translate(camera.getComponent(TransformationComponent.class).position, cameraTransform);
		cameraTransform.rotate(LWJGLMaths.degreesToRadians(camera.getComponent(TransformationComponent.class).pitch), new Vector3f(0, 1, 0));
		cameraTransform.rotate(LWJGLMaths.degreesToRadians(-camera.getComponent(TransformationComponent.class).yaw), new Vector3f(1, 0, 0));
	}

}
