package site.root3287.sudo.input.objects;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import site.root3287.sudo.input.UIObject;

public class UICheckBox extends UIObject{
	private enum State{Checked, Unchecked}
	 private State state = State.Unchecked;
	 private boolean held = false;
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (Mouse.getX() > getX() && Mouse.getX() < getX() + getWidth()) {
            int mousey = Display.getHeight() - Mouse.getY();
            if (mousey > getY() && mousey < getY() + getHeight()) {
                if (Mouse.isButtonDown(0)) {
                    if (!held) {
                        if (state == State.Checked) {
                            state = State.Unchecked;
                        } else {
                            state = State.Checked;
                        }
                        held = true;
                    }
                } else {
                    held = false;
                }
            }
		}
	}
}