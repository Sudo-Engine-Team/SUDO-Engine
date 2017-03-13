package site.root3287.lwjgl.engine;

public class GameState {
	public static enum State{
		SPLASH, LOADING, PAUSED, GAME, MENU
	}
	
	public State state = State.LOADING;
	
	public State getState(){
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}
}
