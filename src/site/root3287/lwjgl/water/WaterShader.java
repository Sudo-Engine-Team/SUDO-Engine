package site.root3287.lwjgl.water;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.shader.Shader;
import site.root3287.lwjgl.utils.LWJGLMaths;

public class WaterShader extends Shader {

	private final static String VERTEX_FILE = "res/shaders/water/waterVertex.glsl";
	private final static String FRAGMENT_FILE = "res/shaders/water/waterFragment.glsl";

	private int location_modelMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;

	public WaterShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = getUniformLocation("projectionMatrix");
		location_viewMatrix = getUniformLocation("viewMatrix");
		location_modelMatrix = getUniformLocation("modelMatrix");
	}

	public void loadProjectionMatrix(Matrix4f projection) {
		loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = LWJGLMaths.createViewMatrix(camera);
		loadMatrix(location_viewMatrix, viewMatrix);
	}

	public void loadModelMatrix(Matrix4f modelMatrix){
		loadMatrix(location_modelMatrix, modelMatrix);
	}

}
