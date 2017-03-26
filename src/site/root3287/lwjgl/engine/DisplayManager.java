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
	public static int WIDTH, HEIGHT, DISPLAY_RATIO;
	public static String TITLE;
	public static boolean isFullScreen;
	public static int FPS_CAP =60;
	public static long lastTime;
	public static long lastFPSTime;
	public static int frames = 0;
	
	private static boolean isWidthSet = false, 
					isHeightSet = false, 
					isRatioSet = false, 
					isTitleSet = false;
	
	public static void createDisplay(String[] args){
		WIDTH = 900;
		DISPLAY_RATIO = 16*9;
		HEIGHT = WIDTH / DISPLAY_RATIO;
		isFullScreen = false;
		TITLE = "LWJGL";
		
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			if(args.length >= 1){
				int i = 0;
				for(String a : args){
					System.out.println("Loading configurement arguments " + a);
					if(a.equals("-t")){
						if(args[i+1] != null && !DisplayManager.isTitleSet){
							DisplayManager.TITLE = args[i+1];
							isTitleSet = true;
						}
					}
					
					if(a.equals("-w")){
						if(args[i+1] != null && !DisplayManager.isWidthSet){
							DisplayManager.WIDTH = Integer.parseInt(args[i+1]);
							isWidthSet = true;
						}
					}
					
					if(a.equals("-h")){
						if(args[i+1] != null && !DisplayManager.isHeightSet){
							DisplayManager.HEIGHT = Integer.parseInt(args[i+1]);
							DisplayManager.isHeightSet = true;
						}
					}
					
					if(a.equals("-r")){
						if(args[i+1] != null && !isRatioSet){
							String split1 = new String(args[i+1]).substring(0, args[i+1].indexOf('*'));
							String split2 = new String(args[i+1]).substring(args[i+1].indexOf('*')+1);
							int s1 = Integer.parseInt(split1);
							int s2 = Integer.parseInt(split2);
							if(isWidthSet && !isHeightSet){
								DisplayManager.HEIGHT = DisplayManager.WIDTH / s1*s2;
								DisplayManager.isHeightSet = true;
							}else if(isHeightSet && !isWidthSet){
								DisplayManager.WIDTH = DisplayManager.HEIGHT / s1*s2;
								DisplayManager.isWidthSet = true;
							}else if(!isWidthSet && !isHeightSet){ // ignore 
								
							}else if(isWidthSet && isHeightSet){ // ignore
								
							}
 						}
					}
					i++;
				}
			}else{
				WIDTH = 900;
				DISPLAY_RATIO = 16*9;
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
			Display.create(new PixelFormat().withSamples(4), attribs);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, DisplayManager.WIDTH, DisplayManager.HEIGHT);
	}
	
	public static void updateDisplay(){
		DisplayManager.updateFPS();
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
	    float delta = (float) (time - DisplayManager.lastTime)/1000f;
	    DisplayManager.lastTime = time;
	    return delta;
	}
	public static void updateFPS(){
		long currentTime = getTime();
		frames++;
		if ( currentTime - lastFPSTime >= 1.0 ){ // If last prinf() was more than 1 sec ago
	         // printf and reset timer
	         frames = 0;
	         lastTime += 1.0;
	     }
	}
	public static int getFPS(){
		return frames;
	}
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public static void setScreen(Screen screen){
		DisplayManager.screen = screen;
	}
	public static float getAspectRatio(){
		return Display.getWidth()/Display.getHeight();
	}
}
