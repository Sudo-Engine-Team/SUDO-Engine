package site.root3287.lwjgl.launcher;

import org.lwjgl.opengl.Display;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.screen.screens.Test;

public class GameLauncher implements Runnable{
	private Thread game;
	private Render r;
	private Loader l;
	private GameState state;
	public GameLauncher() {
		game = new Thread(this);
		game.setDaemon(false);
		game.start();
	}
	
	@Override
	public void run() {
		DisplayManager.createDisplay();
		this.r = new Render();
		this.l = new Loader();
		DisplayManager.setScreen(new Test(r, l, state));
		DisplayManager.screen.init();
		while(!Display.isCloseRequested()){
			DisplayManager.DELTA = DisplayManager.getDelta();
			DisplayManager.screen.update();
			DisplayManager.screen.render();
			DisplayManager.updateDisplay();
		}
		DisplayManager.screen.dispose();
		DisplayManager.closeDisplay();
		stop();
	}
	private void stop(){
		game.interrupt();
	}
}
