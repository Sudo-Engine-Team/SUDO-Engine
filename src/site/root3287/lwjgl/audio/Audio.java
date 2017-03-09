package site.root3287.lwjgl.audio;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.lwjgl.util.vector.Vector3f;

//The CD's.

public class Audio {
	private static List<Integer> buffers = new ArrayList<>();
	public static void init(){
		try {
			AL.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AL10.alDistanceModel(AL10.AL_INVERSE_DISTANCE);
	}
	
	public static void setListenerData(Vector3f position){
		AL10.alListener3f(AL10.AL_POSITION, position.x, position.y, position.z);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String file){
		System.out.println(file);
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData wavFile = WaveData.create(file);
		AL10.alBufferData(buffer, wavFile.format, wavFile.data, wavFile.samplerate);
		wavFile.dispose();
		return buffer;
	}
	
	public static void dispose(){
		for(int buffer : buffers){
			AL10.alDeleteBuffers(buffer);
		}
		AL.destroy();
	}
}
