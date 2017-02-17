package site.root3287.lwjgl.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

//The CD player.

public class AudioSource {
	private int sourceID;
	public AudioSource() {
		this.sourceID = AL10.alGenSources();
		AL10.alSourcef(this.sourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(this.sourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(this.sourceID, AL10.AL_POSITION, 0,0,0);
	}
	public void play(int buffer){
		stop();
		AL10.alSourcei(this.sourceID, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(this.sourceID);
	}
	public void delete(){
		stop();
		AL10.alDeleteSources(this.sourceID);
	}
	public void setVolume(float volume){
		AL10.alSourcef(this.sourceID, AL10.AL_GAIN, volume);
	}
	public void setPitch(float pitch){
		AL10.alSourcef(this.sourceID, AL10.AL_PITCH, pitch);
	}
	public void setPosition(Vector3f position){
		AL10.alSource3f(this.sourceID, AL10.AL_POSITION, position.x, position.y, position.z);
	}
	public void setVelocity(Vector3f velocity){
		AL10.alSource3f(this.sourceID, AL10.AL_VELOCITY, velocity.x, velocity.y, velocity.z);
	}
	public void setLooping(boolean loop){
		AL10.alSourcei(sourceID, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
	}
	public boolean isPlaying(){
		return (AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING)?true:false;
	}
	public void pause(){
		AL10.alSourcePause(sourceID);
	}
	public void resume(){
		AL10.alSourcePlay(sourceID);
	}
	public void stop(){
		AL10.alSourceStop(sourceID);
	}
}
