package site.root3287.sudo.engine.render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.shader.shaders.Shader2D;
import site.root3287.sudo.texture.Texture2D;
import site.root3287.sudo.utils.LWJGLMaths;

public class Render2D{
	private Shader2D shader;
	
	public Render2D(Shader2D shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		this.shader.start();
		this.shader.loadProjection(projectionMatrix);
		this.shader.stop();
	}
	public void render(List<Quad2D> guis){
		this.shader.start();
		for(Quad2D gui : guis){
			prepareQuad(gui);
			Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(gui.getTexture().getPosition(), gui.getTexture().getScale(), 0);
			shader.loadTransformation(transformationMatrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, gui.getModel().getVertexCount());
			unbindQuad();
		}
		this.shader.stop();
	}
	private void prepareQuad(Quad2D quad){
		RawModel rawModel = quad.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		Texture2D texture = quad.getTexture();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	private void unbindQuad(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glDeleteVertexArrays(0);
	}
	public void dispose(){
		this.shader.dispose();
	}
}
