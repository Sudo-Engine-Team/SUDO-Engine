package site.root3287.lwjgl.world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.terrain.PerlinTerrain;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;

public class World {
	private int width, height, size, loadRadius, seed;
	private List<Terrain> terrains;
	private Terrain currentTerrain;
	private Terrain[][] terrainForCollision;
	private List<Vector2f> terrainCollision;
	private Loader loader;
	public World(Loader loader){
		this.loader = loader;
		this.seed = 1234;
		this.terrains = new ArrayList<Terrain>();
		this.terrainForCollision = new Terrain[255][255];
		generateTerrain();
	}
	private void generateTerrain(){
		for(int tX = -1; tX <= 1; tX++){
        	for(int tY = -1; tY <= 1; tY++){
        		Terrain t1 = new PerlinTerrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass.png")
						), 
						64, 
						seed
        				);
        		terrains.add(t1);
        		if(tX >= 0 && tY >= 0){
        			this.terrainForCollision[tX][tY] = t1;
        		}
        	}
        }
	}
	public void update(Vector3f camPosition, int radius){
		int chunkX = (int) (camPosition.x/Terrain.SIZE);
		int chunkY = (int) (camPosition.y/Terrain.SIZE);
	}
	private List<Vector2f> getChunkPositionInRadius(Vector2f chunkPosition, int radius){
		List<Vector2f> result = new ArrayList<Vector2f>();
		 
	    for (int zCircle = -radius; zCircle <= radius; zCircle++){
	        for (int xCircle = -radius; xCircle <= radius; xCircle++){
	            if (xCircle * xCircle + zCircle * zCircle < radius * radius)
	                result.add(new Vector2f(chunkPosition.x + xCircle, chunkPosition.y + zCircle));
	        }
	    }
	 
	    return result;
	}
	private List<Vector2f> getChunkPositionOutRadius(Vector2f chunkPosition, int radius){
		List<Vector2f> result = new ArrayList<Vector2f>();
		 
	    for (int zCircle = -radius; zCircle <= radius; zCircle++){
	        for (int xCircle = -radius; xCircle <= radius; xCircle++){
	            if (xCircle * xCircle + zCircle * zCircle > radius * radius)
	                result.add(new Vector2f(chunkPosition.x + xCircle, chunkPosition.y + zCircle));
	        }
	    }
	 
	    return result;
	}
	public List<Terrain> getTerrains(){
		return terrains;
	}
	public Terrain currentTerrain(){
		return this.currentTerrain;
	}
	public Terrain[][] getTerrainForCollision(){
		return this.terrainForCollision;
	}
}
