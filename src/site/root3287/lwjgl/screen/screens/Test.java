package site.root3287.lwjgl.screen.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.component.FirstPersonComponent;
import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.FontRenderer;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.NullEntity;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.entities.model.StandfordBunny;
import site.root3287.lwjgl.fontMeshCreator.FontType;
import site.root3287.lwjgl.fontMeshCreator.GUIText;
import site.root3287.lwjgl.input.objects.UIText;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.world.World;

public class Test extends Screen{
	private List<Entity> allEntity = new ArrayList<Entity>();
	private List<Light> lights = new ArrayList<Light>();
	private Map<FontType, List<GUIText>> fonts = new HashMap<>();
	private Light light;
	private Camera c;
	private World world;
	private NullEntity entity;
	private List<EventListener> listeners = new ArrayList<>();
	private GUIText text;
	
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
        //Light l2 = new Light(new Vector3f(0,0,0), new Vector3f(8, 0, 0));
        this.lights.add(light);
//        /this.lights.add(l2);
        //StandfordBunny bunny = new StandfordBunny(loader);
        allEntity.add(new StandfordBunny(loader));
        
        UIText.init(loader);
        text = new GUIText("hi", 12, new FontType(loader.loadTexture("res/fonts/Times New Roman/TNR.png"), new File("res/fonts/Times New Roman/TNR.fnt")), new Vector2f(0, 0), 0, true);
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
		this.render.render(lights, c);
		UIText.render();
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		//this.server.close();
	}
}
