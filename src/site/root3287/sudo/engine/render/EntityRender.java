package site.root3287.sudo.engine.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.physics.component.AABBComponent;
import site.root3287.sudo.shader.shaders.StaticShader;
import site.root3287.sudo.texture.ModelTexture;
import site.root3287.sudo.utils.LWJGLMaths;

public class EntityRender {
	private Matrix4f projectionMatrix;
	private StaticShader shader;

	public EntityRender(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		this.projectionMatrix = projectionMatrix;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Map<TexturedModel, List<Entity>> entities) {
		Logger.log(LogLevel.DEBUG_RENDER, "Rendering a entites");
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareTexturedModel(model);
				boolean render = false;
				if(entity.hasComponent(AABBComponent.class) && Render.culler !=null){
					if(Render.culler.isAABBinFrustum(entity.getComponent(AABBComponent.class).aabb)){
						render = true;
					}
				}else{
					render = true;
				}
				if(render){
					prepareTexturedModel(model);
					prepareInstance(entity);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				}
				unbindTexturedModel();
			}
		}
	}

	private void prepareTexturedModel(TexturedModel model) {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing the texture model");
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}

	private void unbindTexturedModel() {
		Logger.log(LogLevel.DEBUG_RENDER, "Unbinding model");
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing entity to be rendered");
		Vector3f position = entity.getComponent(TransformationComponent.class).position;
		Vector3f rotation = entity.getComponent(TransformationComponent.class).rotation;
		float scale = entity.getComponent(TransformationComponent.class).scale;
		Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(position, rotation, scale);
		shader.loadTransformationMatrix(transformationMatrix);
		
		Logger.log(LogLevel.DEBUG_RENDER, "Rendering Entity");
	}
}
