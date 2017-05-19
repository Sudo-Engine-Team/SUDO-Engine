package site.root3287.sudo.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import site.root3287.sudo.audio.Audio;
import site.root3287.sudo.audio.AudioSource;
import site.root3287.sudo.engine.Disposeable;

public class SoundComponent extends Component implements Disposeable{
	public AudioSource player;
	public Map<String, Integer> sounds;
	private UUID id;
	public SoundComponent(UUID id, String name, String file) {
		this.setId(id);
		sounds = new HashMap<>();
		player = new AudioSource();
		sounds.put(name, Audio.loadSound(file));
	}
	public void update(float delta){
		
	}
	public void dispose(){
		player.delete();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
}
