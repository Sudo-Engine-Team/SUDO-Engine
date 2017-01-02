package site.root3287.lwjgl.audio;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.lwjgl.util.vector.Vector3f;

public class Audio {
	public static List<Integer> buffers = new ArrayList<Integer>();
	public static void init(){
		try {
			AL.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setListenerData(Vector3f position){
		AL10.alListener3f(AL10.AL_POSITION, position.x, position.y, position.z);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String file){
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData wavFile = WaveData.create(file);
		AL10.alBufferData(buffer, wavFile.format, wavFile.data, wavFile.samplerate);
		wavFile.dispose();
		return buffer;
	}
	
	public static void dispose(){
		for(int b: buffers){
			AL10.alDeleteBuffers(b);
		}
		AL.destroy();
	}
}
