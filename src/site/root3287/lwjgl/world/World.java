package site.root3287.lwjgl.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println("Seed: "+ seed);
		this.terrains = new ArrayList<Terrain>();
		this.terrainCollision = new HashMap<Integer, HashMap<Integer, Terrain>>();
		generateTerrain();
	}
	/*private void generateTerrain(){
		for(int tX = 0; tX <= 0; tX++){
			HashMap<Integer, Terrain> temp = new HashMap<Integer, Terrain>();
        	for(int tY = 0; tY <= 0; tY++){
        		Terrain t1 = new PerlinTerrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass.png")
						), 
						64, 
						this.seed
        				);
        		terrains.add(t1);
        		System.out.println("Proccessing:" + tX + " " + tY);
        		temp.put(tY, t1);
        	}
        	this.terrainCollision.put(tX, temp);
        }
	}*/
	private void generateTerrain(){
		HashMap<Integer, Terrain> temp = new HashMap<Integer, Terrain>();
		Terrain t1 = new PerlinTerrain(
				0,
				0, 
				this.loader, 
				new ModelTexture(
						this.loader.loadTexture("res/image/grass.png")
				), 
				64, 
				this.seed
				);
		Terrain t2 = new PerlinTerrain(
				0,
				-1, 
				this.loader, 
				new ModelTexture(
						this.loader.loadTexture("res/image/grass.png")
				), 
				64, 
				this.seed
				);
		terrains.add(t1);
		terrains.add(t2);
		temp.put(0, t1);
		temp.put(-1, t2);
		this.terrainCollision.put(0, temp);
	}
	public void update(Vector3f camPosition, int radius){
		int chunkX = (int) (camPosition.x/Terrain.SIZE);
		int chunkZ = (int) (camPosition.z/Terrain.SIZE);
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
