package site.root3287.sudo.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Config {
	private String name;
	private File file;
	private static Map<String, File> configs = new HashMap<>();
	private StringBuilder source = new StringBuilder();
	private JSONObject json;
	public Config(String name, String filePath){
		this.setName(name);
		this.file = new File(filePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.file));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line.trim());
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = new JSONObject(this.source.toString());
	}
	public JSONObject getJSON(){
		return this.json;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static Map<String, File> getConfigs() {
		return configs;
	}
	public static void setConfigs(Map<String, File> configs) {
		Config.configs = configs;
	}
}
