package site.root3287.lwjgl.world;

import java.util.ArrayList;
import java.util.List;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;

public class World {
	private int width, height, size, loadRadius, seed;
	private List<Terrain> terrains, activeTerrain;
	private Terrain currentTerrain;
	private Terrain[][] terrainForCollision;
	private Loader loader;
	public World(Loader loader){
		this.loader = loader;
		this.seed = 1234;
		this.terrains = new ArrayList<Terrain>();
		this.activeTerrain = new ArrayList<Terrain>();
		this.terrainForCollision = new Terrain[255][255];
		generateTerrain();
	}
	private void generateTerrain(){
		for(int tX = 0; tX <= 1; tX++){
        	for(int tY = 0; tY <= 1; tY++){
        		Terrain t1 = new Terrain(
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
	public void update(){
		//Check which chunks is around the player
		//if the player is in the range of a chunk, then spawn the and load it;
		//if the player is outside the chunk range. Then despawn the chunk.
	}
	public List<Terrain> getTerrains(){
		return terrains;
	}
	public List<Terrain> getActiveTerrain(){
		return activeTerrain;
	}
	public Terrain currentTerrain(){
		return this.currentTerrain;
	}
	public Terrain[][] getTerrainForCollision(){
		return this.terrainForCollision;
	}
}
