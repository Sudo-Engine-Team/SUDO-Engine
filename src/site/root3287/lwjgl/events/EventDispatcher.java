package site.root3287.lwjgl.events;

public class EventDispatcher {
	private Event event;
	public EventDispatcher(Event e) {
		this.event = e;
	}
	public void dispatch(EventType type, EventHandler e){
		if(this.event.isHandled()){
			return;
		}
		if(this.event.getType() == type){
			this.event.setHandled(e.onEvent(event));
		}
	}
}
