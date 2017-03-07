package site.root3287.lwjgl.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.lwjgl.util.vector.Vector3f;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

import site.root3287.lwjgl.audio.Audio;
import site.root3287.lwjgl.audio.AudioSource;
import site.root3287.lwjgl.engine.Disposeable;
import site.root3287.lwjgl.entities.Entity;

public class SoundComponent extends Component implements Disposeable{
	public AudioSource player;
	public Map<String, Integer> sounds;
	public SoundComponent(String name, String file) {
		sounds = new HashMap<>();
		player = new AudioSource();
		Audio.setListenerData(new Vector3f(0,0,0));
		sounds.put(name, Audio.loadSound(file));
	}
	public void dispose(){
		player.delete();
	}
}
