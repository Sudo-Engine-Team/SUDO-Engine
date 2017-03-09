package site.root3287.lwjgl.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import site.root3287.lwjgl.audio.Audio;
import site.root3287.lwjgl.audio.AudioSource;
import site.root3287.lwjgl.engine.Disposeable;

public class SoundComponent extends Component implements Disposeable{
	public AudioSource player;
	public Map<String, Integer> sounds;
	private UUID id;
	public SoundComponent(UUID id, String name, String file) {
		this.id = id;
		sounds = new HashMap<>();
		player = new AudioSource();
		sounds.put(name, Audio.loadSound(file));
	}
	public void update(float delta){
		
	}
	public void dispose(){
		player.delete();
	}
}
