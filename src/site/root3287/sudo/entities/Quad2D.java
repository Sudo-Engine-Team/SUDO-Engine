package site.root3287.sudo.entities;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.texture.Texture2D;

public class Quad2D {
	private RawModel model;
	private Texture2D texture;
	public Quad2D(Loader l, Texture2D texture){
		/*float[] positions = {
				//Top Left
				-0.5f,1f, // I
				
				//Bottom Left
				-0.5f,-1f, // III
				
				//Top Right
				0.5f, 1f, // II
				
				//Top Left
				0.5f,-1f // IV
		};*/
		float[] positions = {
				-1,1,
				-1,-1,
				1,1,
				1,-1
		};
		this.model = l.loadToVAO(positions);
		this.texture = texture;
	}
	public RawModel getModel() {
		return model;
	}
	public Texture2D getTexture(){
		return texture;
	}
}
