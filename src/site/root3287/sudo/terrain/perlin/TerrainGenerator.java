package site.root3287.sudo.terrain.perlin;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.terrain.Terrain;

public class TerrainGenerator {
	public static HashMap<Integer, HashMap<Integer, Terrain>> activeTerrain = new HashMap<>();
	
	public void update(Camera c){
		Vector3f cameraPosition = c.getComponent(TransformationComponent.class).position;
		int chunkX = (int) Math.floor(cameraPosition.x/Terrain.SIZE);
		int chunkY = (int) Math.floor(cameraPosition.z/Terrain.SIZE);
		
		//Get the chunks that is around the player;
		Vector2f[] chunksAround = new Vector2f[9]; 
		chunksAround[0] = new Vector2f(chunkX+1, chunkY-1);
		chunksAround[1] = new Vector2f(chunkX+1, chunkY);
		chunksAround[2] = new Vector2f(chunkX+1, chunkY+1);
		
		chunksAround[3] = new Vector2f(chunkX, chunkY-1);
		chunksAround[4] = new Vector2f(chunkX, chunkY);
		chunksAround[5] = new Vector2f(chunkX, chunkY+1);
		
		chunksAround[6] = new Vector2f(chunkX-1, chunkY-1);
		chunksAround[7] = new Vector2f(chunkX-1, chunkY);
		chunksAround[8] = new Vector2f(chunkX-1, chunkY+1);
		
		//Compare the results to the activeTerrain.
			// If the terrain is out of view... save to a file... and remove it from memory
		for(int x : activeTerrain.keySet()){
			for(int y : activeTerrain.get(x).keySet()){
				for(Vector2f testingChunk : chunksAround){
					if(testingChunk.x == x && testingChunk.y == y){ // It's in the list and we don't need to generate the terrain there.
						activeTerrain.get(x).get(y).update(DisplayManager.DELTA);
					}
				}
			}
		}
	}
}
