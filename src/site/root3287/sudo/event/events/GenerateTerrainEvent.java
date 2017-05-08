package site.root3287.sudo.event.events;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class GenerateTerrainEvent extends Event {

	public int x, y, vertexCount;
	public long seed;
	public String image;
	public float[][] height;
	
	public GenerateTerrainEvent() {
		super(EventType.GENERATE_TERRAIN);
	}

}
