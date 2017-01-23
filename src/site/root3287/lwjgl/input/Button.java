package site.root3287.lwjgl.input;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.lwjgl.LWJGL;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.input.interfaces.ButtonInterface;
import site.root3287.lwjgl.texture.Texture2D;

public abstract class Button implements ButtonInterface{
	private Texture2D guiTexture;
	private Vector2f scale, position;
	private boolean isHidden = false, isHovering = false;
	public Button(String file, Loader l, Vector2f position, Vector2f scale) {
		this.guiTexture = new Texture2D(l.loadTexture(file), position, scale);
		this.scale = scale;
		this.position = position;
	}
	public abstract void onClick(ButtonInterface b);
	public abstract void onHover(ButtonInterface b);
	public abstract void offHover(ButtonInterface b);
	public abstract void whileHover();
	public void show(List<Texture2D> guiTextures){
		if(isHidden && !guiTextures.contains(this.guiTexture)){
			this.isHidden = false;
			guiTextures.add(this.guiTexture);
		}
	}
	public void hide(List<Texture2D> guiTextures){
		if(!isHidden && guiTextures.contains(this.guiTexture)){
			this.isHidden = true;
			guiTextures.add(this.guiTexture);
		}
	}
	
	public void update() {
		if(!isHidden){
			Vector2f normalisedMouseCoords = LWJGL.getNormalisedMouseCoords();
			if(		this.position.y + this.scale.y > -normalisedMouseCoords.y &&
					this.position.y - this.scale.y < -normalisedMouseCoords.y &&
					this.position.x + this.scale.x > normalisedMouseCoords.x &&
					this.position.x - this.scale.x < normalisedMouseCoords.x
					){
				onHover(this);
				if(!isHovering){
					onHover(this);
					this.isHovering = true;
				}
				while(Mouse.next()){
					if(Mouse.isButtonDown(0)){
						onClick(this);
					}
				}
			}else{
				if(isHovering){
					offHover(this);
					this.isHovering = false;
				}
			}
		}
	}
}
