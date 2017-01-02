package site.root3287.lwjgl;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.launcher.Launcher;
import site.root3287.lwjgl.screen.screens.Test;

public class LWJGL {
	public static void main(String[] args){
		new Launcher();
		//new LWJGL(args);
	}
	public LWJGL(String[] args){
		DisplayManager.createDisplay(args);
		Loader l = new Loader();
		Render r = new Render();
		DisplayManager.setScreen(new Test(r, l));
		DisplayManager.screen.init();
		while(!Display.isCloseRequested()){
			DisplayManager.DELTA = DisplayManager.getDelta();
			DisplayManager.screen.update();
			DisplayManager.screen.render();
			DisplayManager.updateDisplay();
		}
		DisplayManager.screen.dispose();
		DisplayManager.closeDisplay();
	}
	
	public LWJGL(){
		DisplayManager.createDisplay();
		Loader l = new Loader();
		Render r = new Render();
		DisplayManager.setScreen(new Test(r, l));
		DisplayManager.screen.init();
		while(!Display.isCloseRequested()){
			DisplayManager.DELTA = DisplayManager.getDelta();
			DisplayManager.screen.update();
			DisplayManager.screen.render();
			DisplayManager.updateDisplay();
		}
		DisplayManager.screen.dispose();
		DisplayManager.closeDisplay();
	}
	
	public static Vector2f getNormalisedMouseCoords(){
		float x = -1.0f + 2.0f * Mouse.getX() / Display.getWidth();
		float y = -1.0f + 2.0f * Mouse.getY() / Display.getHeight();
		return new Vector2f(x,y);
	}
}
