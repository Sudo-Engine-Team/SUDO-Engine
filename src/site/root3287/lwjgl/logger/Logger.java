package site.root3287.lwjgl.logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
	public static Map<LogLevel, List<String>> logsMap = new HashMap<>();
	public static List<String> logs = new ArrayList<>();
	public static StringBuilder logString = new StringBuilder();
	//Level of importance
	// -1 = all
	// 0 = Everything except debug_render
	// 1= Everything except debug_Render and debug
	// 2 = Everyting except debug_render, debug, info.
	// 3 = Everything except debug_render, debug, info, and warnings.
	public static int level = 0;
	
	public static void log(LogLevel level, String message){
		String format = "["+new SimpleDateFormat("dd MMMM YYYY HH:mm:ss").format(new Date()) + " "+ level.toString()+"] "+message;
		logs.add(format);
		
		List<String> list = logsMap.get(level);
		
		if(list != null){
			list.add(message);
		}else{
			List<String> newList = new ArrayList<>();
			newList.add(message);
			logsMap.put(level, newList);
		}
		if(Logger.level == -1){
			System.out.println(format);
		}else{
			if(Logger.level < 0 && level.toString().equalsIgnoreCase("debug_render")){
				System.out.println(format);
				logString.append(format+"\n");
			}
			if(Logger.level < 1 && level.toString().equalsIgnoreCase("debug")){
				System.out.println(format);
				logString.append(format+"\n");
			}
			if(Logger.level < 2 && level.toString().equalsIgnoreCase("info")){
				System.out.println(format);
				logString.append(format+"\n");
			}
			if(Logger.level < 3 && level.toString().equalsIgnoreCase("warning")){
				System.out.println(format);
				logString.append(format+"\n");
			}
		}
	}
	public static void log(String message){
		Logger.log(LogLevel.INFO, message);
	}
	public static List<String> getLogsByLevel(LogLevel level){
		return logsMap.get(level);
	}
	public synchronized static String getLogByString(){
		return logString.toString();
	}
	public static String getLogByIndex(int index){
		return logs.get(index);
	}
	public static void setLevel(int level){
		Logger.level = level;
	}
}
