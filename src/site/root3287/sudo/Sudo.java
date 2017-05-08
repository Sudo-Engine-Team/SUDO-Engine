package site.root3287.sudo;

import java.io.File;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo.launcher.GameLauncher;
import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;

public class Sudo {
	/**
	 * Happy 100th commit!
	 */
	//public static File file;
	public static void main(String[] args){
		if(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("windows")){
			Logger.log(LogLevel.INFO, "Loading LWJGL natives using windows");
			System.setProperty("org.lwjgl.librarypath", new File("lib/native/windows").getAbsolutePath());
		}
		if(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("mac")){
			Logger.log(LogLevel.INFO, "Loading LWJGL natives using MacOS");
			System.setProperty("org.lwjgl.librarypath", new File("lib/native/macosx").getAbsolutePath());
		}
		Logger.log(LogLevel.INFO, "System.getProperty('os.name') == " + System.getProperty("os.name"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.version') == " + System.getProperty("os.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.arch') == " + System.getProperty("os.arch"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.version') == " + System.getProperty("java.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.vendor') == " + System.getProperty("java.vendor"));
		Logger.log(LogLevel.INFO, "System.getProperty('sun.arch.data.model') == " + System.getProperty("sun.arch.data.model"));
		//new Launcher();
		new GameLauncher();
	}
	
	public static Vector2f getNormalisedMouseCoords(){
		float x = -1.0f + 2.0f * Mouse.getX() / Display.getWidth();
		float y = -1.0f + 2.0f * Mouse.getY() / Display.getHeight();
		return new Vector2f(x,y);
	}
}
