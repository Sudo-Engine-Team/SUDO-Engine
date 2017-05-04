package site.root3287.sudo.event;

import site.root3287.sudo.event.events.FooEvent;
import site.root3287.sudo.event.listeners.BarListener;
import site.root3287.sudo.event.listeners.FooListener;
import site.root3287.sudo.event.listeners.HelloListener;
import site.root3287.sudo.event.type.EventType;

public class EventSystem {

	public static void main(String[] args) {
		EventDispatcher helloDispatcher = new EventDispatcher(EventType.HELLO);
		EventDispatcher fooDispatcher = new EventDispatcher(EventType.FOO);
		
		HelloListener helloListener = new HelloListener();
		FooListener fooListener = new FooListener();
		
		helloDispatcher.addListener(helloListener);
		helloDispatcher.addListener(fooListener);
		helloDispatcher.addListener(new BarListener());
		
		fooDispatcher.addListener(fooListener);
		
		fooDispatcher.dispatch(new FooEvent());
	}

}
