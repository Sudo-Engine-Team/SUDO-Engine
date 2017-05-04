package site.root3287.sudo.event;

import java.util.ArrayList;
import java.util.List;

import site.root3287.sudo.event.listeners.EventListener;
import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class EventDispatcher {
	private List<EventListener> eventList = new ArrayList<>();
	private EventType type;
	public EventDispatcher(EventType e){
		this.type = e;
	}
	public void addListener(EventListener listener){
		eventList.add(listener);
	}
	public void dispatch(Event e){
		if(e.getType() != type)
			return;
		
		for(EventListener listener : eventList){
			if(!e.hasHandle() && listener.getType() == type)
				e.setHandle(listener.onEvent(e));
		}
	}
}
