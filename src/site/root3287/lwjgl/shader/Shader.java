package site.root3287.lwjgl.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class Shader {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public Shader(String vertexFile, String fragmentFile){
		this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		this.programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, this.vertexShaderID);
		GL20.glAttachShader(programID, this.fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	protected abstract void bindAttributes();
	
	protected void bindAttributes(int attribute, String varibleName){
		GL20.glBindAttribLocation(programID, attribute, varibleName);
	}
	
	public void start(){
		GL20.glUseProgram(this.programID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	public void dispose(){
		stop();
		GL20.glDetachShader(this.programID, vertexShaderID);
		GL20.glDetachShader(this.programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.programID);
	}
	
	private static int loadShader(String file, int type){
		StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
	}
	
	protected abstract void getAllUniformLocations();
	
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(this.programID, uniformName);
		
	}
	
	protected void loadFloat(int location, float value){
		GL20.glUniform1f(location, value);
	}
	protected void loadVector(int location, Vector3f value){
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	protected void loadBoolean(int location, boolean value){
		float toLoad = 0;
		if(value){
			toLoad = 1;
		}
		GL20.glUniform1f(location, toLoad);
	}
	protected void loadMatrix(int location, Matrix4f matrix){
		matrix.store(this.matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
}
