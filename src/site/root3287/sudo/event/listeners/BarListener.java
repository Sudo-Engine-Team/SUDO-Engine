package site.root3287.sudo.event.listeners;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class BarListener extends EventListener {

	public BarListener() {
		super(EventType.FOO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onEvent(Event e) {
		System.out.println("Bar");
		return false;
	}

}
