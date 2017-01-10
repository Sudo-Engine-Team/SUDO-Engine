package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.objConverter.ModelData;
import site.root3287.lwjgl.engine.objConverter.OBJFileLoader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Camera;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.server.Server;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.water.WaterRender;
import site.root3287.lwjgl.water.WaterShader;
import site.root3287.lwjgl.water.WaterTile;

public class Test extends Screen{
	
	private List<Terrain> allTerrain = new ArrayList<Terrain>();
	private List<WaterTile> allWater = new ArrayList<WaterTile>();
	private Light light;
	private Entity e;
	private WaterRender wr;
	private Camera c;
	private Terrain[][] terrainForCollision;
	private Terrain currentTerrain;
	private Server server;
	private Client client;

	public Test() {
		super();
	}

	public Test(Render render, Loader loader) {
		super(render, loader);
	}

	@Override
	public void init() {
		this.server = new Server(8123);
		this.client = new Client("127.0.0.1:8123");
		server.start();
		client.connect();
		
		terrainForCollision = new Terrain[255][255];
		this.c = new Camera(new Vector3f(0, 10f, 0));
		Mouse.setGrabbed(c.isGrabbed());
		
        this.light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        
        int seed = new Random().nextInt();
       
        for(int tX = 0; tX <= 5; tX++){
        	for(int tY = 0; tY <= 5; tY++){
        		System.out.println("Processing terrain for "+tX+" "+tY);
        		Terrain t1 = new Terrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass.png")
						), 
						64, 
						seed
        				);
        		allTerrain.add(t1);
        		terrainForCollision[tX][tY] = t1;
        	}
        }
        
        WaterShader ws = new WaterShader();
        WaterRender wr = new WaterRender(this.loader, ws, this.render.getProjectionMatrix());
        WaterTile w1 = new WaterTile(0, 0, 20);
        allWater.add(w1);
	}

	@Override
	public void update() {
		c.update(terrainForCollision, DisplayManager.DELTA);
		System.out.println(c.getYaw() +" "+ c.getPitch());
	}

	@Override
	public void render() {
		for(Terrain t:allTerrain){
			this.render.processTerrain(t);
		}
		this.render.render(light, c);
		//wr.render(allWater, c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		this.server.close();
	}
	
}
