package site.root3287.sudo.terrain.perlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;

public class PerlinWorld {
	private long seed;
	public HashMap<Integer, HashMap<Integer, Terrain>> terrain =new HashMap<>();
	private List<Vector2f> lastPosition = new ArrayList<>();
	
	public PerlinWorld() {
		this.seed = new Random().nextLong();
	}
	public PerlinWorld(String seed){
		long sum = 0;
		for(char s : seed.toCharArray()){
			sum+=Math.pow(s*31, getPosition(s));
		}
		this.seed= sum;
	}
	public PerlinWorld(long seed){
		this.seed = seed;
	}
	
<<<<<<< HEAD
	private int getPosition(char input) {
		  char start = ' ';
		  int alphabetStart = (int) start;
		  int position = (int) input - alphabetStart + 1;
		  return position;
	}
	
	public void update(Vector3f positon, float delta){
		int chunkX = (int) Math.floor(positon.x/Terrain.SIZE);
		int chunkY = (int) Math.floor(positon.z/Terrain.SIZE);
		HashMap<Integer, Terrain> batch = new HashMap<>();
		if(PerlinTerrainCache.isLoaded(chunkX, chunkY)){
			if(PerlinTerrainCache.isLoaded(chunkX)){
				//batch.put(, value)
			}
		}else{
			PerlinTerrainCache.load(chunkX, chunkY, new PerlinTerrain(chunkX, chunkY, Loader.getInstance(), new ModelTexture(Loader.getInstance().loadTexture("res/image/grass-plane.png")), 128, seed));
		}
		for(Vector2f add : chunkNeedToBeAdded){
			ThreadedHeightGenerator terrain = new ThreadedHeightGenerator((int)add.x, (int)add.y, 128, 123);
			terrain.start();
			
		}
		lastPosition = current;
	}
	
	private List<Vector2f> getChunkPositionsInRadius(Vector2f chunkPosition, int radius){
	    List<Vector2f> result = new ArrayList<Vector2f>();
	 
	    for (int zCircle = -radius; zCircle <= radius; zCircle++){
	        for (int xCircle = -radius; xCircle <= radius; xCircle++){
	            if (xCircle * xCircle + zCircle * zCircle < radius * radius)
	                result.add(new Vector2f(chunkPosition.x + xCircle, chunkPosition.y + zCircle));
	        }
	    }
	 
	    return result;

	}
	private static List<Vector2f> compareAdded(List<Vector2f> a, List<Vector2f> b){
		List<Vector2f> union = new ArrayList<>(a);
		union.removeAll(b);
		return union;
	}
	private static List<Vector2f> compareRemoved(List<Vector2f> a, List<Vector2f> b){
		List<Vector2f> union = new ArrayList<>(b);
		union.removeAll(a);
		return union;
	}
	
	public void addToWorld(int x, int z){
		HashMap<Integer, Terrain> batch;
		if(heights.containsKey(x)){
			batch = heights.get(x);
		}else{
			batch = new HashMap<>();
		}
		Terrain t = new PerlinTerrain(x, z, loader, new ModelTexture(loader.loadTexture("res/image/grass-plane.png")), 128, 123);
		terrain.add(t);
		batch.put(z, t);
		heights.put(x, batch);
	}
	
	public void addToWorld(Terrain t){
		HashMap<Integer, Terrain> batch;
		if(heights.containsKey(t.getGridX())){
			batch = heights.get(t.getGridX());
		}else{
			batch = new HashMap<>();
		}
		terrain.add(t);
		batch.put(t.getGridZ(), t);
		heights.put(t.getGridX(), batch);
	}
	
	public void removeTerrain(int x, int z){
		int i = 0;
		if(heights.containsKey(x) && heights.get(x).containsKey(z)){
			int removeIndex = -1;
			for(Terrain t : terrain){
				if(t.getGridX() == x && t.getGridZ() == z){
					removeIndex = i;
				}
				i++;
			}
			terrain.remove(removeIndex);
			loader.removeVAO(heights.get(x).get(z).getModel().getVaoID());
			heights.get(x).remove(z);
			if(heights.get(x).isEmpty()){
				heights.remove(x);
			}
		}
	}
	
	public void removeTerrain(Terrain t){
		int i = 0;
		if(heights.containsKey(t.getGridX()) && heights.get(t.getGridX()).containsKey(t.getGridZ())){
			int removeIndex = -1;
			for(Terrain tx : terrain){
				if(tx.getGridX() == t.getGridX() && tx.getGridZ() == t.getGridZ()){
					removeIndex = i;
				}
				i++;
			}
			terrain.remove(removeIndex);
			loader.removeVAO(heights.get(t.getGridX()).get(t.getGridZ()).getModel().getVaoID());
			heights.get(t.getGridX()).remove(t.getGridZ());
			if(heights.get(t.getGridX()).isEmpty()){
				heights.remove(t.getGridX());
			}
		}
	}
	
	public void updateCamera(Camera c){
		this.c = c;
	}
	
	public synchronized List<Terrain> getTerrain(){
		return this.terrain;
	}
}
