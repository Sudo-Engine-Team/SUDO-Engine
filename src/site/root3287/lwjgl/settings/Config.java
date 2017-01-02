package site.root3287.lwjgl.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
	private boolean exists;
	private String filePath;
	private Properties properties;
	public Config(String filePath){
		this.filePath = filePath;
		try{
			File file = new File(filePath+"config.xml");
			exists = file.exists();
			if(!exists){
				file.createNewFile();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Config(){
		this.filePath = "res/settings/";
		try{
			File file = new File(this.filePath+"config.xml");
			exists = file.exists();
			if(!exists){
				file.createNewFile();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveConfig(String key, Object value){
		try{
			File file = new File(this.filePath+"/config.xml");
			exists = file.exists();
			if(!exists){
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(this.filePath+"/config.xml");
			this.properties.put(key, value);
			this.properties.storeToXML(os, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object loadConfigureation(String key){
		Object out = null;
		try{
			InputStream input = new FileInputStream(this.filePath);
			properties.loadFromXML(input);
			out = properties.get(key);
			input.close();
		}catch(FileNotFoundException e){
			
		}catch(IOException e1){
			
		}
		return out;
	}
}
