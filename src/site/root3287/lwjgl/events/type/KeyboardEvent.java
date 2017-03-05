package site.root3287.lwjgl.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.events.EventType;

public class KeyboardEvent extends InputEvent{
	
	protected KeyboardEvent(Keyboard key, EventType type) {
		super(key, type);
	}

}
