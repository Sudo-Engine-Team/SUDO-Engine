package site.root3287.sudo.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;
import site.root3287.sudo.terrain.Terrain;

public class World {
	@SuppressWarnings("unused")
	private int width, height, size, loadRadius, seed;
	private List<Terrain> terrains;
	private Terrain currentTerrain;
	private HashMap<Integer, HashMap<Integer, Terrain>> terrainCollision;
	private Loader loader;
	public World(Loader loader){
		this.setLoader(loader);
		this.seed = 1234;
		this.terrains = new ArrayList<Terrain>();
		this.terrainCollision = new HashMap<Integer, HashMap<Integer, Terrain>>();
		generateTerrain();
	}
	public World(Loader loader, int seed){
		this.setLoader(loader);
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
        		/*Terrain t1 = new PerlinTerrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass-plane.png")
						), 
						128, 
						this.seed
        				);
        		terrains.add(t1);
        		Logger.log(LogLevel.INFO, "Proccessing:" + tX + " " + tY);
        		temp.put(tY, t1);*/
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
	public Loader getLoader() {
		return loader;
	}
	public void setLoader(Loader loader) {
		this.loader = loader;
	}
}
