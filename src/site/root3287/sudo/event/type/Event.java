package site.root3287.sudo.event.type;

public class Event {
	private EventType type;
	private boolean handle = false;
	public Event(EventType type){
		this.type = type;
	}
	
	public EventType getType(){
		return type;
	}
	
	public boolean hasHandle(){
		return handle;
	}

	public void setHandle(boolean handle) {
		this.handle = handle;
	}
}
