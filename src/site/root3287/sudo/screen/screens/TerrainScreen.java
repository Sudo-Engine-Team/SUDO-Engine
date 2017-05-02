package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.audio.Audio;
import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.terrain.perlin.PerlinTerrain;
import site.root3287.sudo.texture.ModelTexture;

public class TerrainScreen extends Screen{

	private List<Light> lights = new ArrayList<Light>();
	private List<Terrain> terrain = new ArrayList<>();
	private HashMap<Integer, HashMap<Integer, Terrain>> heights =new HashMap<>();
	private List<Vector2f> lastPosition = new ArrayList<>();
	private Light light;
	private Camera c;
	private Vector2f worldOffset = new Vector2f(1, 2);
	
	public TerrainScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		Audio.init();
		this.c = new FirstPerson(new Vector3f(0, 0, 0));
		Mouse.setGrabbed(this.c.getComponent(PlayerControlsComponent.class).isGrabbed);
		this.light = new Light(new Vector3f(c.getComponent(TransformationComponent.class).position.x, 100, c.getComponent(TransformationComponent.class).position.x), 
				new Vector3f(1.25f, 1.25f, 1.25f));
		this.lights.add(this.light);
		for(Vector2f p : getChunkPositionsInRadius(new Vector2f(0,0), 3)){
			addToWorld((int)p.x, (int)p.y);
			lastPosition.add(p);
		}
	}

	@Override
	public void update() {
		int chunkX = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.x / Terrain.SIZE);
		int chunkY = (int) Math.floor(this.c.getComponent(TransformationComponent.class).position.z / Terrain.SIZE);
		List<Vector2f> current = getChunkPositionsInRadius(new Vector2f(chunkX, chunkY), 3);
		List<Vector2f> chunkNeedToBeAdded = compareAdded(current, lastPosition);
		List<Vector2f> chunkNeedToBeRemoved = compareRemoved(current, lastPosition);
		for(Vector2f remove : chunkNeedToBeRemoved){
			removeTerrain((int)remove.x, (int)remove.y);
		}
		for(Vector2f add : chunkNeedToBeAdded){
			addToWorld((int)add.x, (int)add.y);
		}
		this.c.update(DisplayManager.DELTA);
		lastPosition = current;
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		for(Terrain t: terrain){
			this.render.processTerrain(t);
		}
		this.render.render(this.lights, this.c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		Audio.dispose();
	}
	
	public void addToWorld(int x, int z){
		HashMap<Integer, Terrain> batch;
		if(heights.containsKey(x)){
			batch = heights.get(x);
		}else{
			batch = new HashMap<>();
		}
		Terrain t = new PerlinTerrain(x, z, loader, new ModelTexture(loader.loadTexture("res/image/grass-plane.png")), 50, 123);
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
	
	public static List<Vector2f> compareAdded(List<Vector2f> a, List<Vector2f> b){
		List<Vector2f> union = new ArrayList<>(a);
		union.removeAll(b);
		return union;
	}
	public static List<Vector2f> compareRemoved(List<Vector2f> b, List<Vector2f> a){
		List<Vector2f> union = new ArrayList<>(a);
		union.removeAll(b);
		return union;
	}
}
