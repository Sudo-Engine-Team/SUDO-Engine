package site.root3287.lwjgl.utils;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.lwjgl.entities.Camera;

public class MousePicker {
	private Vector3f currentRay;
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Camera camera;
	public MousePicker(Camera camera, Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
		this.camera = camera;
		this.viewMatrix = LWJGLMaths.createViewMatrix(this.camera);
	}
	public Vector3f getCurrentRay() {
		return currentRay;
	}
	public void update(){
		this.viewMatrix = LWJGLMaths.createViewMatrix(this.camera);
		this.currentRay = calculateCurrentMouseRay();
	}
	public void update(Vector2f position){
		this.viewMatrix = LWJGLMaths.createViewMatrix(this.camera);
		this.currentRay = calculateCurrentMouseRay(position.x, position.y);
	}
	private Vector3f calculateCurrentMouseRay(){
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		Vector2f normalisedCoords = getNormalizedMouse(mouseX, mouseY);
		Vector4f clipCoord = new Vector4f(normalisedCoords.x, normalisedCoords.y, -1f, 1f);
		Vector4f eyeCoord = toEyeCoord(clipCoord);
		Vector3f worldRay = toWorldCoord(eyeCoord);
		return worldRay;
	}
	private Vector3f calculateCurrentMouseRay(float mouseX, float mouseY){
		Vector2f normalisedCoords = getNormalizedMouse(mouseX, mouseY);
		Vector4f clipCoord = new Vector4f(normalisedCoords.x, normalisedCoords.y, -1f, 1f);
		Vector4f eyeCoord = toEyeCoord(clipCoord);
		Vector3f worldRay = toWorldCoord(eyeCoord);
		return worldRay;
	}
	private Vector3f toWorldCoord(Vector4f eyeCoord){
		Matrix4f invertView = Matrix4f.invert(viewMatrix, null);
		Vector4f rayWorld = Matrix4f.transform(invertView, eyeCoord, null);
		Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
		mouseRay.normalise();
		return mouseRay;
	}
	private Vector4f toEyeCoord(Vector4f clipCoord){
		Matrix4f inverseProjection = Matrix4f.invert(projectionMatrix, null);
		Vector4f eyeCoord = Matrix4f.transform(inverseProjection, clipCoord, null);
		return new Vector4f(eyeCoord.x, eyeCoord.y, -1f, 0f);
	}
	private Vector2f getNormalizedMouse(float mouseX, float mouseY){
		float x = (2.0f * mouseX) / Display.getWidth() -1;
		float y = (2.0f * mouseY) / Display.getHeight() - 1;
		return new Vector2f(x,y);
	}
}
