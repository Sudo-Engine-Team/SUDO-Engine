package site.root3287.sudo.launcher;

import org.lwjgl.opengl.Display;

import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.screen.screens.ModelScreen;
import site.root3287.sudo.screen.screens.Test;

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
		DisplayManager.setScreen(new ModelScreen(r, l, state));
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
