package site.root3287.lwjgl.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import site.root3287.lwjgl.logger.LogLevel;
import site.root3287.lwjgl.logger.Logger;
import site.root3287.lwjgl.model.RawModel;

public class Loader {
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public Map<Integer, List<Integer>> vaoText = new HashMap<>();
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){ //3d models
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		storeDataInAttributeList(2, 3, normals);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	public RawModel loadToVAO(float[] positions, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 2, positions);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	public RawModel loadToVAO(float[] positions){
		int vaoID = createVAO();
		storeDataInAttributeList(0, 2, positions);
		unbindVAO();
		return new RawModel(vaoID, positions.length/2);
	}
	public int loadToVAO(float[] positions, float[] textureCoords){
		int vaoID = createVAO();
		storeDataInAttributeList(0, 2, positions);
		storeDataInAttributeList(0, 2, textureCoords);
		unbindVAO();
		return vaoID;
	}
	public int loadText(float[] position, float[] textureCoords){
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		List<Integer> vboBatch = new ArrayList<>();
		
		//StoreDataInAttibArray
		int vboPosition = GL15.glGenBuffers();
		vboBatch.add(vboPosition);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboPosition);
		FloatBuffer positionBuffer = storeDataInFloatBuffer(position);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		int vboTextureCoords = GL15.glGenBuffers();
		vboBatch.add(vboTextureCoords);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboTextureCoords);
		FloatBuffer textureBuffer = storeDataInFloatBuffer(textureCoords);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);		
		unbindVAO();
		
		vaoText.put(vaoID, vboBatch);
		
		return vaoID;
	}
	private int createVAO(){
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer b = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, b, GL15.GL_STATIC_DRAW);
	}
	private void storeDataInAttributeList(int attributeNumber, int coordnateSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordnateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		return b;
	}
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(fileName));
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	public void removeTextFromMemory(int vao){
		Logger.log(LogLevel.DEBUG_RENDER, "Removing Text from memory");
		List<Integer> vbos = vaoText.remove(vao); 
		for (int vbo : vbos){
			GL15.glDeleteBuffers(vbo);
		}
		GL30.glDeleteVertexArrays(vao);
	}
	
	public void destory(){
		Logger.log(LogLevel.INFO, "Disposing Loader");
		int size = 0;
		for(int vao: vaos){
			GL30.glDeleteVertexArrays(vao);
			size++;
		}
		Logger.log(LogLevel.INFO, "Deleted "+size+" VAOS");
		size =0;
		for(int vbo: vbos){
			GL15.glDeleteBuffers(vbo);
			size++;
		}
		Logger.log(LogLevel.INFO, "Deleted "+size+" VBOS");
		size =0;
		int vaoSize = 0;
		int vboSize = 0;
		for(List<Integer> textVBO : vaoText.values()){
			for(int textvbo : textVBO){
				GL15.glDeleteBuffers(textvbo);
				vboSize++;
			}
			vaoSize++;
		}
		Logger.log(LogLevel.INFO, "Deleted "+vaoSize+" Text VAOS");
		Logger.log("Deleted "+vboSize+" Text VBOS");
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
}
