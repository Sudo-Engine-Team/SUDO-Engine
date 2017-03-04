package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.FirstPersonComponent;
import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.objConverter.ModelData;
import site.root3287.lwjgl.engine.objConverter.OBJFileLoader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.NullEntity;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.entities.model.StandfordBunny;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.world.World;

public class Test extends Screen{
	private List<Entity> allEntity = new ArrayList<Entity>();
	private Light light;
	private Camera c;
	private World world;
	private NullEntity entity;
	
	public Test(Render render, Loader loader) {
		super(render, loader);
	}

	@Override
	public void init() {
		//this.server = new Server(8123);
		//this.client = new Client("127.0.0.1:8123");
		//server.start();
		//client.connect();
		int seed = new Random().nextInt();
		//int seed = 123;
        this.world = new World(this.loader, seed);
		this.c = new FirstPerson(new Vector3f(0, 10f, 20));
		Mouse.setGrabbed(c.getComponent(FirstPersonComponent.class).isGrabbed);
        this.light = new Light(new Vector3f(20,100000000,20), new Vector3f(7, 7, 7));
        ModelData data = OBJFileLoader.loadOBJ("res/model/lowPolyTree.obj");
        for(int i = 0; i < 1000; i ++){
        	float x = new Random().nextFloat() * Terrain.SIZE ;
        	float y = new Random().nextFloat() * Terrain.SIZE ;
        	if(new Random().nextBoolean() == true){
        		x *= -1;
        		y *= -1;
        	}
        	int chunkX = (int) Math.floor(x / Terrain.SIZE);
        	int chunkY = (int) Math.floor(y/Terrain.SIZE);
        	if(this.world.getTerrainForCollision().containsKey(chunkX) && this.world.getTerrainForCollision().get(chunkX).containsKey(chunkY)){
        	Terrain collision = this.world.getTerrainForCollision().get(chunkX).get(chunkY);
        	float height = (collision == null)? 0 : this.world.getTerrainForCollision().get(chunkX).get(chunkY).getTerrainHeightByCoords(x, y )-2.0f;
	        this.entity = new NullEntity(
	        		new Vector3f(x, height, y), 
	        		new TexturedModel(loader.loadToVAO(
	        				data.getVertices(), 
	        				data.getTextureCoords(), 
	        				data.getNormals(), 
	        				data.getIndices()
	        		), 
	        		//new ModelTexture(loader.loadTexture("res/image/white.png")))
	        		new ModelTexture(loader.loadTexture("res/model/lowPolyTree.png")))
	        );
	        allEntity.add(this.entity);
        	}
        	//StandfordBunny bunny = new StandfordBunny(loader);
        	//allEntity.add(new StandfordBunny(loader));
        }
	}

	@Override
	public void update() {
		c.update(world.getTerrainForCollision(), DisplayManager.DELTA);
	}

	@Override
	public void render() {
		//GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		for(Terrain t: this.world.getTerrains()){
			this.render.processTerrain(t);
		}
		for(Entity e : this.allEntity){
			this.render.processEntity(e);
		}
		this.render.render(light, c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		//this.server.close();
	}
	
}
