package site.root3287.lwjgl.shader;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.Entities.Camera;
import site.root3287.lwjgl.Entities.Light;
import site.root3287.lwjgl.toolbox.LWJGLMaths;

public class StaticShader extends Shader{

	private static final String VERTEX_FILE = "res/shaders/vertexShader.glsl", 
								FRAGMENT_FILE = "res/shaders/fragmentShader.glsl";
	
	private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
 
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "textureCoordinates");
        super.bindAttributes(2, "normal");
    }
 
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightColour = super.getUniformLocation("lightColour");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_shineDamper = super.getUniformLocation("shineDamper");
    }
     
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }
     
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = LWJGLMaths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
     
    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }
    
    public void loadLight(Light light){
    	super.loadVector(location_lightPosition, light.getPosition());
    	super.loadVector(location_lightColour, light.getColour());
    }
    
    public void loadShineVaribles(float shineDamper, float reflectivity){
    	super.loadFloat(location_shineDamper, shineDamper);
    	super.loadFloat(location_reflectivity, reflectivity);
    }
}
