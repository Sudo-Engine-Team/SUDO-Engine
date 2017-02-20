package site.root3287.lwjgl.input.objects;

import site.root3287.lwjgl.input.UIObject;

public class UIButton extends UIObject{

	private enum State{UP, DOWN, HOVER, OFF}
	private State state = State.UP;
	
    @Override
    public void update() {

       State newState = State.UP;

        /*if (getRectangle().containsPoint(Mouse.getX(), Display.getHeight() - Mouse.getY())) {
            newState = State.HOVER;
            if (Mouse.isButtonDown(0)) {
                newState = State.DOWN;
            }
        }*/

        if (state != newState) {
            state = newState;

            if(state== State.UP){
            	
            }
                

            if(state== State.DOWN){
            	
            }
               

            if(state== State.HOVER){
            	
            }
                
        }
    }

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
