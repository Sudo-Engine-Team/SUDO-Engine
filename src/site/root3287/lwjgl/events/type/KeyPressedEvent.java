package site.root3287.lwjgl.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.events.EventType;

public class KeyPressedEvent extends KeyboardEvent {

	protected KeyPressedEvent(Keyboard key) {
		super(key, EventType.KEY_PRESSED);
	}

}
