package site.root3287.sudo.event.listeners;

import site.root3287.sudo.event.events.FinishedHeightMappingEvent;
import site.root3287.sudo.event.events.GenerateTerrainEvent;
import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;
import site.root3287.sudo.terrain.perlin.PerlinWorld;

public class FinishedHeightMapListener extends EventListener {

	protected FinishedHeightMapListener() {
		super(EventType.FinishedHeightMapping);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onEvent(Event e) {
		if(e.equals(EventType.FinishedHeightMapping)){
			if(e instanceof FinishedHeightMappingEvent){
				GenerateTerrainEvent t = new GenerateTerrainEvent();
				t.x = ((FinishedHeightMappingEvent) e).x;
				t.y = ((FinishedHeightMappingEvent) e).y;
				t.vertexCount = ((FinishedHeightMappingEvent) e).vertexCount;
				t.seed = ((FinishedHeightMappingEvent) e).seed;
				PerlinWorld.generateTerrainListener.dispatch(t);
			}
		}
		e.setHandle(false);
		return false;
	}

}
