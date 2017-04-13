package site.root3287.sudo.engine.render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.shader.shaders.TerrainShader;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;
import site.root3287.sudo.utils.LWJGLMaths;

public class TerrainRender{
    private TerrainShader shader;
    
    public TerrainRender(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
 
    public void render(List<Terrain> terrains) {
        for (Terrain terrain : terrains) {
            if(Render.culler != null && terrain.getAABB() != null &&Render.culler.isAABBinFrustum(terrain.getAABB())){
            	prepareTerrain(terrain);
            	loadModelMatrix(terrain);
            	GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
            	unbindTexturedModel();
            }else if(Render.culler == null || terrain.getAABB() == null){
            	prepareTerrain(terrain);
            	loadModelMatrix(terrain);
            	GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
            	unbindTexturedModel();
            }
        }
    }
 
    private void prepareTerrain(Terrain terrain) {
        RawModel rawModel = terrain.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = terrain.getTexture();
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
    }
 
    private void unbindTexturedModel() {
    	GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
 
    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(
                new Vector3f(terrain.getX(), 0, terrain.getZ()));
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
