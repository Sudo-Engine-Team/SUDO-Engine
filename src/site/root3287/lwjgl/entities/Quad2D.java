package site.root3287.lwjgl.entities;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.texture.Texture2D;

public class Quad2D {
	private RawModel model;
	private Texture2D texture;
	private int vertexCount;
	public Quad2D(Loader l, Texture2D texture){
		float[] positions = {-1,1,-1,-1,1,1,1,-1};
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
