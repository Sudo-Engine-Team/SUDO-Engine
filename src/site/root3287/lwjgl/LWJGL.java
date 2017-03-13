package site.root3287.lwjgl;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.screen.screens.Splash;
import site.root3287.lwjgl.screen.screens.Test;
import site.root3287.lwjgl.screen.screens.WaterScreen;

public class LWJGL {
	private Render r;
	private Loader l;
	private GameState state;
	public static void main(String[] args){
		//new Launcher();
		new LWJGL(args);
	}
	public LWJGL(String[] args){
		DisplayManager.createDisplay(args);
		this.r = new Render();
		this.l = new Loader();
		this.state = new GameState();
		this.state.state = GameState.State.LOADING;
		DisplayManager.setScreen(new WaterScreen(r, l, state));
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
		this.r = new Render();
		this.l = new Loader();
		DisplayManager.setScreen(new Splash(r, l, state));
		DisplayManager.screen.init();
		while(!Display.isCloseRequested()){
			DisplayManager.DELTA = DisplayManager.getDelta();
			DisplayManager.screen.update();
			DisplayManager.screen.render();
			DisplayManager.updateDisplay();
		}
		DisplayManager.screen.dispose();
		DisplayManager.closeDisplay();
		System.exit(0);
	}
	
	public static Vector2f getNormalisedMouseCoords(){
		float x = -1.0f + 2.0f * Mouse.getX() / Display.getWidth();
		float y = -1.0f + 2.0f * Mouse.getY() / Display.getHeight();
		return new Vector2f(x,y);
	}
}
