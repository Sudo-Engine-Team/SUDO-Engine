package site.root3287.lwjgl.engine.render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.entities.Quad2D;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.shader.shaders.Shader2D;
import site.root3287.lwjgl.texture.Texture2D;

public class Render2D{
	private Shader2D shader;
	
	public Render2D(Shader2D shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		this.shader.start();
		this.shader.loadTransformation(projectionMatrix);
		this.shader.stop();
	}
	public void render(List<Quad2D> guis){
		for(Quad2D gui : guis){
			prepareQuad(gui);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, gui.getModel().getVertexCount());
			unbindQuad();
		}
	}
	private void prepareQuad(Quad2D quad){
		RawModel rawModel = quad.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		Texture2D texture = quad.getTexture();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	}
	private void unbindQuad(){
		GL20.glDisableVertexAttribArray(0);
		GL30.glDeleteVertexArrays(0);
	}
	public void dispose(){
		this.shader.dispose();
	}
}
