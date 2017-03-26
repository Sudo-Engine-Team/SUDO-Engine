package site.root3287.sudo.events.type;

import site.root3287.sudo.events.Event;
import site.root3287.sudo.events.EventType;

public class MouseMovedEvent extends Event{

	private int x, y;
	
	protected MouseMovedEvent(int x, int y) {
		super(EventType.MOUSE_MOVE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
