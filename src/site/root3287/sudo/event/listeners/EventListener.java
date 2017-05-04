package site.root3287.sudo.event.listeners;

import site.root3287.sudo.event.type.EventType;
import site.root3287.sudo.event.type.IEvent;

public abstract class EventListener implements IEvent{
	private EventType type;
	protected EventListener(EventType hello){
		this.type = hello;
	}
	public EventType getType(){
		return type;
	}
}
