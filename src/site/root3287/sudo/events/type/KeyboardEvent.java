package site.root3287.sudo.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.sudo.events.EventType;

public class KeyboardEvent extends InputEvent{
	
	protected KeyboardEvent(Keyboard key, EventType type) {
		super(key, type);
	}

}
