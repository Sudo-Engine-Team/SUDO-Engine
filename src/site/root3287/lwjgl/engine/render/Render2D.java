package site.root3287.lwjgl.engine.render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.shader.shaders.Shader2D;
import site.root3287.lwjgl.texture.Texture2D;

public class Render2D{
	private final RawModel quad;
	private Shader2D shader;
	public Render2D(Loader loader) {
		float[] positions = {-1,1,-1,-1,1,1,1,-1};
		this.quad = loader.loadToVAO(positions);
		this.shader = new Shader2D();
	}
	public void render(List<Texture2D> guis){
		shader.start();
		GL30.glBindVertexArray(this.quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		for(Texture2D gui : guis){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTextureID());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, this.quad.getVertexCount());
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	public void dispose(){
		this.shader.dispose();
	}
}
