package site.root3287.lwjgl.font.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.lwjgl.font.FontType;
import site.root3287.lwjgl.font.GUIText;
import site.root3287.lwjgl.logger.LogLevel;
import site.root3287.lwjgl.logger.Logger;
import site.root3287.lwjgl.utils.LWJGLMaths;

public class FontRender {

	private FontShader shader;
	 
    public FontRender() {
        shader = new FontShader();
    }
     
    public void render(Map<FontType, List<GUIText>> texts){
        prepare();
        for(FontType font : texts.keySet()){
        	Logger.log(LogLevel.DEBUG_RENDER, "Preparing to render some text");
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTextureAtlas());
            for(GUIText text : texts.get(font)){
            	Logger.log(LogLevel.DEBUG_RENDER, "Rendering Text");
                renderText(text);
            }
        }
        endRendering();
    }
    
    private void prepare(){
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        shader.start();
    }
     
    private void renderText(GUIText text){
        GL30.glBindVertexArray(text.getMesh());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        shader.loadColour(text.getColour());
        // Larger text, less edge higher width (0.51, 0.2)
        // small text, MOre edge less width (0.46, 0.19)
        shader.isDistanceField(text.getFont().isDistanceField());
        shader.loadDistanceFields(0.46f, 0.19f);
        Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(text.getPosition(), text.getRotation(), new Vector2f(text.getScale(), text.getScale()));
        shader.loadTranslation(transformationMatrix);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
     
    private void endRendering(){
        shader.stop();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

	public void dispose(){
		shader.dispose();
	}
}
