package site.root3287.lwjgl.events;

public class Event {
	private EventType type;
	private boolean handled;
	protected Event(EventType type){
		this.type = type;
	}
	public EventType getType(){
		return this.type;
	}
	public boolean isHandled() {
		return handled;
	}
	public void setHandled(boolean handled) {
		this.handled = handled;
	}
}
