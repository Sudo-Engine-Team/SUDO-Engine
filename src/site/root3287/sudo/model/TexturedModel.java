package site.root3287.sudo.model;

import site.root3287.sudo.texture.ModelTexture;

public class TexturedModel {
	private RawModel rawModle;
	private ModelTexture texture;
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModle = model;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModle;
	}

	public ModelTexture getTexture() {
		return texture;
	}
}
