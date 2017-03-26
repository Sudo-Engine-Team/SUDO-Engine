package site.root3287.sudo.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.sudo.events.Event;
import site.root3287.sudo.events.EventType;

public class InputEvent extends Event{
	private Keyboard key;
	protected InputEvent(Keyboard key, EventType type) {
		super(type);
		this.key = key;
	}
	public Keyboard getKey(){
		return key;
	}
}
