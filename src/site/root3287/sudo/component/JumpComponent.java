package site.root3287.sudo.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.terrain.Terrain;

public class JumpComponent extends Component{
	private UUID id;
	private static final int JUMP = 10, GRAVITY = -5;
	private boolean inAir = false;
	public JumpComponent(UUID id) {
		this.id = id;
		if(!Entity.hasComponent(id, TransformationComponent.class)){
			throw new IllegalAccessError("The entity does not have a Transformation Component!");
		}
	}
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta){
		Vector3f position = Entity.getComponent(id, TransformationComponent.class).position;
		float dy = Entity.getComponent(id, TransformationComponent.class).velocity.y;
		int xChunk = (int) Math.floor(position.x / Terrain.SIZE);
		int yChunk = (int) Math.floor(position.z/Terrain.SIZE);
		
		dy += GRAVITY * delta;
		Terrain currentTerrain = null;
		Map<Integer, Terrain> temp;
		float terrainHeight = 0;
		if(terrain.containsKey((int)xChunk)){
			temp = terrain.get((int)xChunk);
			if(temp.containsKey((int)yChunk)){
				currentTerrain = temp.get((int)yChunk);
			}
		}
		if(currentTerrain != null){
			terrainHeight = currentTerrain.getTerrainHeightByCoords(position.x, position.z);
		}else{
			terrainHeight = 0;
		}
		
		if(position.y < terrainHeight){ // Collision detection
			dy = 0;
			inAir = false;
			position.y = JUMP; // Jump again
		}
		position.y  += dy;
		
		Entity.getComponent(id, TransformationComponent.class).position = position;
		Entity.getComponent(id, TransformationComponent.class).velocity.y = dy;
	}
}
