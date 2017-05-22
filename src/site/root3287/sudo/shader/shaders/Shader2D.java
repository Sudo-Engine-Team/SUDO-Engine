package site.root3287.sudo.shader.shaders;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo.shader.Shader;

public class Shader2D extends Shader{

	private static final String VERTEX_FILE ="res/shaders/2D/vertexShader.glsl", 
								FRAGMENT_FILE = "res/shaders/2D/fragmentShader.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	
	public Shader2D() {
		super(VERTEX_FILE, FRAGMENT_FILE);	
	}
	
	@Override
	protected void getAllUniformLocations() {
		this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "positions");
	}
	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(this.location_transformationMatrix, matrix);
	}
	public void loadProjection(Matrix4f matrix){
		super.loadMatrix(this.location_projectionMatrix, matrix);
	}
}
