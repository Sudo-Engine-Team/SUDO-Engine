package site.root3287.lwjgl.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.logger.LogLevel;
import site.root3287.lwjgl.logger.Logger;
import site.root3287.lwjgl.terrain.PerlinTerrain;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;

public class World {
	@SuppressWarnings("unused")
	private int width, height, size, loadRadius, seed;
	private List<Terrain> terrains;
	private Terrain currentTerrain;
	private HashMap<Integer, HashMap<Integer, Terrain>> terrainCollision;
	private Loader loader;
	public World(Loader loader){
		this.loader = loader;
		this.seed = 1234;
		this.terrains = new ArrayList<Terrain>();
		this.terrainCollision = new HashMap<Integer, HashMap<Integer, Terrain>>();
		generateTerrain();
	}
	public World(Loader loader, int seed){
		this.loader = loader;
		this.seed = seed;
		Logger.log(LogLevel.INFO, "Seed: "+ seed);
		this.terrains = new ArrayList<Terrain>();
		this.terrainCollision = new HashMap<Integer, HashMap<Integer, Terrain>>();
		generateTerrain();
	}
	private void generateTerrain(){
		for(int tX = -2; tX <= 2; tX++){
			HashMap<Integer, Terrain> temp = new HashMap<Integer, Terrain>();
        	for(int tY = -2; tY <= 2; tY++){
        		Terrain t1 = new PerlinTerrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass-plane.png")
						), 
						64, 
						this.seed
        				);
        		terrains.add(t1);
        		Logger.log(LogLevel.INFO, "Proccessing:" + tX + " " + tY);
        		temp.put(tY, t1);
        	}
        	this.terrainCollision.put(tX, temp);
        }
	}
	public void update(Vector3f camPosition, int radius){
		
	}
	public List<Terrain> getTerrains(){
		return terrains;
	}
	public Terrain currentTerrain(){
		return this.currentTerrain;
	}
	public HashMap<Integer, HashMap<Integer, Terrain>> getTerrainForCollision(){
		return this.terrainCollision;
	}
}
