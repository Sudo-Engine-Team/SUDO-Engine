package site.root3287.lwjgl.utils;

import org.lwjgl.util.vector.Matrix2f;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Camera.Camera;

public class LWJGLMaths {
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx,float ry, float rz, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector3f translation) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		return matrix;
	}
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f viewMatrix = new Matrix4f();
		float pitch = camera.getComponent(TransformationComponent.class).pitch;
		float yaw = camera.getComponent(TransformationComponent.class).yaw;
		Vector3f position = camera.getComponent(TransformationComponent.class).position;
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = position;
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
	}
	public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}
	public static float distance3d(Vector3f p1, Vector3f p2){
		return (float) Math.sqrt(Math.pow((p1.x - p2.x ), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
	}
}
