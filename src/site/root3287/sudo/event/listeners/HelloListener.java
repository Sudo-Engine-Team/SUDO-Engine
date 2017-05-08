package site.root3287.sudo.event.listeners;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class HelloListener extends EventListener{

	public HelloListener() {
		super(EventType.HELLO);
	}

	@Override
	public boolean onEvent(Event e) {
		System.out.println("Hello Listener");
		return false;
	}

}
