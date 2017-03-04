package site.root3287.lwjgl.shader.shaders;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.shader.Shader;
import site.root3287.lwjgl.utils.LWJGLMaths;

public class StaticShader extends Shader{

	private static final String VERTEX_FILE = "res/shaders/vertexShader.glsl", 
								FRAGMENT_FILE = "res/shaders/fragmentShader.glsl";
     
    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int[] location_lightPosition;
    private int[] location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_useFakeLight;
    private int location_fogDensity;
    private int location_fogGradient;
    private int location_skyColour;
    private int location_maxLight;
    
    private static final int MAX_LIGHT = 4;
 
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normal");
    }
 
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_useFakeLight = super.getUniformLocation("useFakeLight");
        location_fogDensity = super.getUniformLocation("fogDensity");
        location_fogGradient = super.getUniformLocation("fogGradient");
        location_skyColour = super.getUniformLocation("skyColour");
        location_maxLight = super.getUniformLocation("maxLight");
        
        location_lightPosition = new int[MAX_LIGHT];
        location_lightColour = new int[MAX_LIGHT];
        for(int i=0; i<MAX_LIGHT; i++){
        	location_lightPosition[i] = super.getUniformLocation("lightPosition["+i+"]");
        	location_lightColour[i] = super.getUniformLocation("lightColour["+i+"]");
        }
    }
     
    public void loadShineVariables(float damper,float reflectivity){
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }
     
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }
     
    public void loadLight(List<Light> light){
    	for(int i =0; i<light.size(); i++){
    		if(i < MAX_LIGHT){
    			super.loadVector(location_lightPosition[i], light.get(i).getPosition());
            	super.loadVector(location_lightColour[i], light.get(i).getColour());
    		}else{
    			super.loadVector(location_lightPosition[i], new Vector3f(0,0,0));
    			super.loadVector(location_lightColour[i], new Vector3f(0,0,0));
    		}
    	}
    }
     
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = LWJGLMaths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
     
    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }
    
    public void loadFakeLight(boolean fakeLight){
    	float useFakeLight =0;
    	if(fakeLight){
    		useFakeLight = 1;
    	}
    	super.loadFloat(location_useFakeLight, useFakeLight);
    }
    public void setFog(float fogDensity, float fogGradient){
    	super.loadFloat(location_fogDensity, fogDensity);
    	super.loadFloat(location_fogGradient, fogGradient);
    }
    public void loadSkyColour(float r, float g, float b){
    	super.loadVector(location_skyColour, new Vector3f(r,g,b));
    }
}
