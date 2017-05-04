package site.root3287.sudo.event.listeners;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.event.events.GenerateTerrainEvent;
import site.root3287.sudo.event.type.Event;
import site.root3287.sudo.event.type.EventType;
import site.root3287.sudo.terrain.perlin.PerlinTerrain;
import site.root3287.sudo.terrain.perlin.PerlinWorld;
import site.root3287.sudo.texture.ModelTexture;

public class GenerateTerrainListener extends EventListener {

	private PerlinWorld world;
	
	protected GenerateTerrainListener(PerlinWorld w) {
		super(EventType.GENERATE_TERRAIN);
		this.world = w;
	}

	@Override
	public boolean onEvent(Event e) {
		if(e.getType() == EventType.GENERATE_TERRAIN){
			if(e instanceof GenerateTerrainEvent){
				this.world.addToWorld(
						new PerlinTerrain(
								((GenerateTerrainEvent) e).x, 
								((GenerateTerrainEvent) e).y, 
								Loader.getInstance(), 
								new ModelTexture(Loader.getInstance().loadTexture(((GenerateTerrainEvent) e).image)), 
								((GenerateTerrainEvent) e).vertexCount, 
								((GenerateTerrainEvent) e).seed,
						((GenerateTerrainEvent) e).height));
			}
		}
		e.setHandle(true);
		return true;
	}

}
