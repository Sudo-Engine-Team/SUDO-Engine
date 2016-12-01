package site.root3287.lwjgl.engine.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.LWJGL;
import site.root3287.lwjgl.Entities.Entity;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.shader.StaticShader;
import site.root3287.lwjgl.toolbox.LWJGLMaths;

public class Render {
	
	private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
     
    private Matrix4f projectionMatrix;
    private StaticShader shader;
     
    public Render(StaticShader shader){
    	GL11.glEnable(GL11.GL_CULL_FACE);
    	GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
 
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0.0f, 0.0f, 1);
    }
    
    public void render(Map<TexturedModel, List<Entity>> entities){
    	for(TexturedModel model : entities.keySet()){
    		prepareTextruedModel(model);
    		List<Entity> batch = entities.get(model);
    		for(Entity entity: batch){
    			prepareInstance(entity);
    			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
    		}
    		unbindTexturedModel();
    	}
    }
    
    private void prepareTextruedModel(TexturedModel model){
    	 RawModel rawModel = model.getRawModel();
         GL30.glBindVertexArray(rawModel.getVaoID());
         GL20.glEnableVertexAttribArray(0);
         GL20.glEnableVertexAttribArray(1);
         GL20.glEnableVertexAttribArray(2);
         shader.loadShineVaribles(model.getTexture().getShineDamper(), model.getTexture().getReflectivity());
         GL13.glActiveTexture(GL13.GL_TEXTURE0);
         GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }
    
    public void unbindTexturedModel(){
    	 GL20.glDisableVertexAttribArray(0);
         GL20.glDisableVertexAttribArray(1);
         GL20.glDisableVertexAttribArray(2);
         GL30.glBindVertexArray(0);
    }
    
    private void prepareInstance(Entity entity){
    	Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(entity.getPosition(),
                entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }
     
    private void createProjectionMatrix(){
        float aspectRatio = (float) LWJGL.WIDTH / (float) LWJGL.HEIGHT;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
