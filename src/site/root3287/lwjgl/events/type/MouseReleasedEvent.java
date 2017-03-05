package site.root3287.lwjgl.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.events.Event;
import site.root3287.lwjgl.events.EventType;

public class MouseReleasedEvent extends MouseEvent{

	protected MouseReleasedEvent(int x, int y, Keyboard key) {
		super(x, y, key, EventType.KEY_RELEASED);
		// TODO Auto-generated constructor stub
	}
	
}
