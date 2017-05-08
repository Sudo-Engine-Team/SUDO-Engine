package site.root3287.sudo.terrain.perlin;

import java.util.HashMap;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.terrain.Terrain;

public class PerlinTerrainCache {
	public static HashMap<Integer, HashMap<Integer, Terrain>> world = new HashMap<>();
	
	public static boolean isLoaded(int x, int y){
		return (world.containsKey(x) && world.get(x).containsKey(y)) ? true:false;
	}
	public static boolean isLoaded(int x){
		return (world.containsKey(x))? true:false;
	}
	public static Terrain retrive(int x, int y){
		if(isLoaded(x, y)){
			return world.get(x).get(y);
		}
		return null;
	}
	
	public static void load(int x, int y, Terrain terrain){
		HashMap<Integer, Terrain> batch = new HashMap<>();
		if(world.containsKey(x)){
			batch = world.get(x);
		}
		batch.put(y, terrain);
		world.put(x, batch);
	}
	
	public static void unloadChunk(int x, int y){
		Loader.getInstance().removeVAO(world.get(x).get(y).getModel().getVaoID());
		world.remove(x).remove(y);
	}
}
