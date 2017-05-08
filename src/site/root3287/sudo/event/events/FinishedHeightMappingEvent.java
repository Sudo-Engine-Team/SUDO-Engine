package site.root3287.sudo.event.events;

import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;

public class FinishedHeightMappingEvent extends Event{

	public float[][] heights;
	public int x, y, vertexCount;
	public long seed;
	
	public FinishedHeightMappingEvent(float[][] heights, int x, int y, int vertexCount, long seed) {
		super(EventType.FinishedHeightMapping);
		this.heights = heights;
		this.x = x;
		this.y = y;
		this.vertexCount= vertexCount;
		this.seed = seed;
	}

}
