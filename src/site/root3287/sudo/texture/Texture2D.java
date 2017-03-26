package site.root3287.sudo.texture;

import org.lwjgl.util.vector.Vector2f;

public class Texture2D {
	private int textureID;
	private Vector2f position, scale;
	public Texture2D(int textureID, Vector2f position, Vector2f scale) {
		this.textureID = textureID;
		this.position = position;
		this.scale = scale; 
	}
	public int getTextureID() {
		return textureID;
	}
	public Vector2f getPosition() {
		return position;
	}
	public Vector2f getScale() {
		return scale;
	}
	
}
