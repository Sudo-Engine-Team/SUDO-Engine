package site.root3287.sudo.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.sudo.events.EventType;

public class KeyReleaseEvent extends KeyboardEvent {

	protected KeyReleaseEvent(Keyboard key) {
		super(key, EventType.KEY_RELEASED);
		// TODO Auto-generated constructor stub
	}

}
