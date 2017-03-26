package site.root3287.sudo.engine.resource;

import java.util.HashMap;

import org.lwjgl.openal.AL10;

import site.root3287.sudo.audio.Audio;
import site.root3287.sudo.audio.AudioSource;

public class SoundManager {
	public static HashMap<String, Integer> buffers = new HashMap<String, Integer>();
	public static HashMap<String, AudioSource> sources = new HashMap<String, AudioSource>();
	public SoundManager(){
		Audio.init();
	}
	public void addSource(String key, AudioSource source){
		sources.put(key, source);
	}
	public void addSound(String key, String file){
		buffers.put(key, Audio.loadSound(file));
	}
	public Integer getSound(String key){
		return buffers.get(key);
	}
	public AudioSource getSource(String key){
		return sources.get(key);
	}
	public void dispose(){
		for(String buffer: buffers.keySet()){
			AL10.alDeleteBuffers(buffers.get(buffer));
		}
		for(String source: sources.keySet()){
			sources.get(source).delete();
		}
		Audio.dispose();
		
	}
}
