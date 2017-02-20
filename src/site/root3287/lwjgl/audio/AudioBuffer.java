package site.root3287.lwjgl.audio;

import java.util.HashMap;

import org.lwjgl.openal.AL10;

public class AudioBuffer {
	public static HashMap<String, Integer> buffers = new HashMap<String, Integer>();
	public AudioBuffer(){
		Audio.init();
	}
	public void addSound(String key, String file){
		buffers.put(key, Audio.loadSound(file));
	}
	public void dispose(){
		for(String buffer: buffers.keySet()){
			AL10.alDeleteBuffers(buffers.get(buffer));
		}
		Audio.dispose();
	}
}
