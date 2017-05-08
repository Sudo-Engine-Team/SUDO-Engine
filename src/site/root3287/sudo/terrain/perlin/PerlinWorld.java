package site.root3287.sudo.terrain.perlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;
import site.root3287.sudo.utils.Utils;

public class PerlinWorld {
	private List<Terrain> terrain = new ArrayList<>();
	private HashMap<Integer, HashMap<Integer, Terrain>> heights =new HashMap<>();
	private List<Vector2f> lastPosition = new ArrayList<>();
	private long seed;
	private int worldSize = 5;
	private Loader loader;
	
	public PerlinWorld(Loader loader){
		this.loader = loader;
		this.seed = new Random().nextLong();
	}
	
	public void init(){
		for(Vector2f p : Utils.getPositionsInRadius(new Vector2f(0,0), worldSize)){
			addToWorld((int)p.x, (int)p.y);
			lastPosition.add(p);
		}
	}
	
	public void update(Camera c){
		int chunkX = (int) Math.floor(c.getComponent(TransformationComponent.class).position.x / Terrain.SIZE);
		int chunkY = (int) Math.floor(c.getComponent(TransformationComponent.class).position.z / Terrain.SIZE);
		List<Vector2f> current = Utils.getPositionsInRadius(new Vector2f(chunkX, chunkY), worldSize);
		List<Vector2f> chunkNeedToBeAdded = Utils.compareAdded(current, lastPosition);
		List<Vector2f> chunkNeedToBeRemoved = Utils.compareRemoved(current, lastPosition);
		for(Vector2f remove : chunkNeedToBeRemoved){
			removeTerrain((int)remove.x, (int)remove.y);
		}
		for(Vector2f add : chunkNeedToBeAdded){
			addToWorld((int)add.x, (int)add.y);
		}
		lastPosition = current;
	}
	public void addToWorld(int x, int z){
		HashMap<Integer, Terrain> batch;
		if(heights.containsKey(x)){
			batch = heights.get(x);
		}else{
			batch = new HashMap<>();
		}
		Terrain t = new PerlinTerrain(x, z, loader, new ModelTexture(loader.loadTexture("res/image/grass-plane.png")), 100, seed);
		if(terrain.add(t)){
		}
		batch.put(z, t);
		heights.put(x, batch);
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
	public List<Terrain> getTerrain(){
		return this.terrain;
	}
}
