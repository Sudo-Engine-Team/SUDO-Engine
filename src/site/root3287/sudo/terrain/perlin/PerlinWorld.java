package site.root3287.sudo.terrain.perlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;

public class PerlinWorld implements Runnable{
	
	private Thread t;
	public boolean running = false;
	private Loader loader;
	private List<Vector2f> lastPosition = new ArrayList<>();
	public List<Terrain> terrain = new ArrayList<>();
	private static int WORLD_SIZE = 2;
	private Camera c;
	private HashMap<Integer, HashMap<Integer, Terrain>> heights =new HashMap<>();
	
	public PerlinWorld(Loader loader, Camera c) {
		this.loader = loader;
		this.t = new Thread(this);
		this.t.setDaemon(true);
		updateCamera(c);
	}
	
	@Override
	public void run() {
		for(Vector2f p : getChunkPositionsInRadius(new Vector2f(0,0), WORLD_SIZE)){
			addToWorld((int)p.x, (int)p.y);
			lastPosition.add(p);
		}
		while(running){
			int chunkX = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.x / Terrain.SIZE);
			int chunkY = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.z / Terrain.SIZE);
			
			List<Vector2f> current = getChunkPositionsInRadius(new Vector2f(chunkX, chunkY), WORLD_SIZE);
			List<Vector2f> chunkNeedToBeAdded = compareAdded(current, lastPosition);
			List<Vector2f> chunkNeedToBeRemoved = compareRemoved(current, lastPosition);
			for(Vector2f remove : chunkNeedToBeRemoved){
				removeTerrain((int)remove.x, (int)remove.y);
			}
			for(Vector2f add : chunkNeedToBeAdded){
				addToWorld((int)add.x, (int)add.y);
			}
			lastPosition = current;
		}
	}
	
	private List<Vector2f> getChunkPositionsInRadius(Vector2f chunkPosition, int radius){
	    List<Vector2f> result = new ArrayList<>();
	 
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
	
	public void updateCamera(Camera c){
		this.c = c;
	}
	
	public synchronized List<Terrain> getTerrain(){
		return this.terrain;
	}
	
	public synchronized HashMap<Integer, HashMap<Integer, Terrain>> getHeight(){
		return this.heights;
	}
	
	public void start(){
		this.running = true;
		t.start();
	}
}
