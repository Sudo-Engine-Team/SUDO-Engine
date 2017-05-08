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
}
