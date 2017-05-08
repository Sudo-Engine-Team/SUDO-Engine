package site.root3287.sudo.event.events;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class HelloEvent extends Event{

	public HelloEvent() {
		super(EventType.HELLO);
	}

}
