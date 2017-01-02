package site.root3287.lwjgl.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import site.root3287.lwjgl.screen.Screen;

public class DisplayManager {
	public static Screen screen;
	public static float DELTA;
	public static int WIDTH, HEIGHT;
	public static String TITLE;
	public static boolean isFullScreen;
	public static int FPS_CAP =60;
	public static long lastTime;
	
	public static void createDisplay(String[] args){
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			if(args.length >= 1){
				int i = 0;
				for(String a : args){
					System.out.println(a);
					if(a.equals("-t")){
						if(args[i+1] != null){
							DisplayManager.TITLE = args[i+1];
						}else{
							DisplayManager.TITLE = "INVALID TITLE";
						}
					}else{
						DisplayManager.TITLE = "LWJGL";
					}
					
					if(a.equals("-w")){
						if(args[i+1] != null){
							DisplayManager.WIDTH = Integer.parseInt(args[i+1]);
						}else{
							DisplayManager.WIDTH = 900;
						}
					}else{
						DisplayManager.WIDTH = 900;
					}
					
					if(a.equals("-h")){
						if(args[i+1] != null){
							DisplayManager.HEIGHT = Integer.parseInt(args[i+1]);
						}else{
							DisplayManager.HEIGHT = WIDTH/16*9;
						}
					}else{
						DisplayManager.HEIGHT = WIDTH /16*9;
					}
					
					if(a.equalsIgnoreCase("-fullScreen")){
						DisplayManager.isFullScreen = true;
					}else{
						DisplayManager.isFullScreen = false;
					}
					
					i++;
				}
			}else{
				WIDTH = 900;
				HEIGHT = WIDTH / 16*9;
				isFullScreen = false;
				TITLE = "LWJGL";
			}
			Display.setResizable(true);
			Display.setTitle(DisplayManager.TITLE);
			Display.setFullscreen(DisplayManager.isFullScreen);
			Display.setDisplayMode(new DisplayMode(DisplayManager.WIDTH, DisplayManager.HEIGHT));
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, DisplayManager.WIDTH, DisplayManager.HEIGHT);
	}

	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		WIDTH = 900;
		HEIGHT = WIDTH / 16*9;
		isFullScreen = false;
		TITLE = "LWJGL";
		Display.setResizable(true);
		Display.setTitle(DisplayManager.TITLE);
		try {
			Display.setFullscreen(DisplayManager.isFullScreen);
			Display.setDisplayMode(new DisplayMode(DisplayManager.WIDTH, DisplayManager.HEIGHT));
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, DisplayManager.WIDTH, DisplayManager.HEIGHT);
	}
	
	public static void updateDisplay(){
		if(Display.wasResized()){
			DisplayManager.WIDTH = Display.getWidth();
			DisplayManager.HEIGHT = Display.getHeight();
			GL11.glViewport(0, 0, DisplayManager.WIDTH, DisplayManager.HEIGHT);
		}
		Display.sync(DisplayManager.FPS_CAP);
		Display.update();
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}
	
	public static float getDelta() {
	    long time = getTime();
	    float delta = (int) (time - DisplayManager.lastTime)/1000f;
	    DisplayManager.lastTime = time;

	    return delta;
	}

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public static void setScreen(Screen screen){
		DisplayManager.screen = screen;
	}
}
