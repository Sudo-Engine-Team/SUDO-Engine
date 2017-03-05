package site.root3287.lwjgl.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.events.EventType;

public class KeyReleaseEvent extends KeyboardEvent {

	protected KeyReleaseEvent(Keyboard key) {
		super(key, EventType.KEY_RELEASED);
		// TODO Auto-generated constructor stub
	}

}
