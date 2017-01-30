package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Camera;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.server.Server;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.world.World;

public class Test extends Screen{
	
	private List<Terrain> allTerrain = new ArrayList<Terrain>();
	private Light light;
	private Camera c;
	private Server server;
	private Client client;
	private World world;

	public Test() {
		super();
	}

	public Test(Render render, Loader loader) {
		super(render, loader);
	}

	@Override
	public void init() {
		//this.server = new Server(8123);
		//this.client = new Client("127.0.0.1:8123");
		//server.start();
		//client.connect();
		
		this.c = new Camera(new Vector3f(0, 10f, 0));
		Mouse.setGrabbed(c.isGrabbed());
		
        this.light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        
        int seed = new Random().nextInt();
       
        this.world = new World(this.loader);
	}

	@Override
	public void update() {
		c.update(world.getTerrainForCollision(), DisplayManager.DELTA);
	}

	@Override
	public void render() {
		for(Terrain t: this.world.getTerrains()){
			this.render.processTerrain(t);
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
