package site.root3287.sudo.event.listeners;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class FooListener extends EventListener{

	public FooListener() {
		super(EventType.FOO);
	}

	@Override
	public boolean onEvent(Event e) {
		System.out.println("Foo Listener");
		return true;
	}

}
