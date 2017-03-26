package site.root3287.sudo.texture;

import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.entities.Quad2D;

public class Ninepatch {
	private Quad2D corrners;
	private Quad2D background;
	private Quad2D border;
	
	public Ninepatch(Loader loader, Texture2D corrners, Texture2D border, Texture2D background){
		this.corrners = new Quad2D(loader, corrners);
		this.border = new Quad2D(loader, border);
		this.background = new Quad2D(loader, background);
	}
	
	public Ninepatch(Loader loader, Texture2D image, Vector4f corrner, Vector4f border, Vector4f background){
		
	}
}
