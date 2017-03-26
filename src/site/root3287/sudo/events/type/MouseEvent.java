package site.root3287.sudo.events.type;

import org.lwjgl.input.Keyboard;

import site.root3287.sudo.events.EventType;

public class MouseEvent extends InputEvent{
	private int x, y;
	protected MouseEvent(int x, int y, Keyboard key, EventType type) {
		super(key, type);
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
