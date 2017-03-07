package site.root3287.lwjgl;

import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.audio.Audio;
import site.root3287.lwjgl.audio.AudioSource;

public class TestSound {
	public static void main(String[] args) throws IOException {
		Audio.init();
		Audio.setListenerData(new Vector3f(0,0,0));
		int buffer = Audio.loadSound("sounds/Jump.wav");
		AudioSource source = new AudioSource();
		source.setLooping(true);
		
		char c = ' ';
		int i =0;
		Vector3f position = new Vector3f(0,0,10);
		while(c != 'q'){
			System.out.println(position);
			c = (char)System.in.read();
			position.z += -0.05f;
			if(c == 'p'){
				if (i == 0){
					source.play(buffer);
				}else{
					if(source.isPlaying()){
						source.pause();
					}else{
						source.resume();
					}
				}
			}
			if(c == 's'){
				i = 0;
				position = new Vector3f();
				source.stop();
			}
			source.setPosition(position);
			i++;
		}
		Audio.dispose();
		source.delete();
	}
}
