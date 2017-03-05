package site.root3287.lwjgl.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.events.EventType;

public class MousePressedEvent extends MouseEvent {

	protected MousePressedEvent(int x, int y, Keyboard key) {
		super(x, y, key, EventType.MOUSE_PRESSED);
		// TODO Auto-generated constructor stub
	}

}
