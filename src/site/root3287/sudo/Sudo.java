package site.root3287.sudo;

import java.io.File;

import site.root3287.sudo.launcher.GameLauncher;
import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;

public class Sudo {
	/**
	 * Happy 100th commit!
	 */
	//public static File file;
	public static void main(String[] args){
		Logger.log(LogLevel.INFO, "Loading LWJGL natives for "+System.getProperty("os.name"));
		if(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("windows")){
			System.setProperty("org.lwjgl.librarypath", new File("lib/native/windows").getAbsolutePath());
		}
		if(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("mac")){
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
}
