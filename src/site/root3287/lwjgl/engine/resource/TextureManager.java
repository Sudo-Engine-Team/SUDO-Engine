package site.root3287.lwjgl.engine.resource;

import java.util.HashMap;
import java.util.Map;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.texture.ModelTexture;

public class TextureManager {
	private Map<String, ModelTexture> textures = new HashMap<String, ModelTexture>();
	private Loader loader;
	public TextureManager(Loader loader){
		this.loader = loader;
	}
	public void addResources(String key, String filePath){
		textures.put(key, new ModelTexture(loader.loadTexture(filePath)));
	}
	public ModelTexture get(String key){
		return textures.get(key);
	}
}
