package site.root3287.sudo.screen.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.audio.Audio;
import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.NullEntity;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.entities.model.CubeEntity;
import site.root3287.sudo.font.FontText;
import site.root3287.sudo.font.FontType;
import site.root3287.sudo.font.GUIText;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.world.World;

public class Test extends Screen{
	private List<Entity> allEntity = new ArrayList<Entity>();
	private List<Light> lights = new ArrayList<Light>();
	private List<GUIText> text = new ArrayList<>();
	private Light light;
	private Camera c;
	private World world;
	private NullEntity entity;
	
	public Test(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		Audio.init();
		FontText.init(loader);
		FontType fontType = new FontType(loader.loadTexture("res/fonts/DistanceFields/Arial/Arial.png"), new File("res/fonts/DistanceFields/Arial/Arial.fnt"), 8, true);
		// text 0
		FontText.loadText(new GUIText("Version Alpha 0.0.1", 
				1, 
				fontType, 
				new Vector2f(0, 0), 1, true));
		
		//int seed = new Random().nextInt();
		int seed = -1251497298;
        this.world = new World(this.loader, seed);
		this.c = new FirstPerson(new Vector3f(0, 0f, 0));
		Mouse.setGrabbed(c.getComponent(PlayerControlsComponent.class).isGrabbed);
        this.light = new Light(new Vector3f(10000, 10000, 10000), new Vector3f(2, 2, 2));
        this.lights.add(light);
       // allEntity.add(new StandfordBunny(loader));
        
        for(int i = 0; i < 200; i++){
        	Random rand = new Random();
        	float valuex = (rand.nextFloat() * 2) -1;
        	float valuez = (rand.nextFloat() * 2) -1;
        	float x = (rand.nextFloat() * 500) * valuex;
        	float z = (rand.nextFloat() * 500)*valuez;
        	int xChunk = (int) Math.floor(x/Terrain.SIZE);
        	int yChunk = (int) Math.floor(z/Terrain.SIZE);
        	float y = this.world.getTerrainForCollision().get(xChunk).get(yChunk).getTerrainHeightByCoords(x, z);
        	allEntity.add(new CubeEntity(loader, new Vector3f(x, y, z)));
        }
        
        //Text 1
        FontText.loadText(new GUIText("X: "+ c.getComponent(TransformationComponent.class).position.x + " Y: "+ c.getComponent(TransformationComponent.class).position.y + " Z: "+ c.getComponent(TransformationComponent.class).position.z, 
				1, 
				fontType,
				new Vector2f(0f, 0), 1, false));
        //Text 2
        FontText.loadText(new GUIText("Delta Time: "+ DisplayManager.DELTA, 
				1, 
				fontType,
				new Vector2f(0.25f, 0), 1, true));
    }

	@Override
	public void update() {
		c.update(world.getTerrainForCollision(), DisplayManager.DELTA);
		allEntity.get(0).update(DisplayManager.DELTA);
		FontText.getAllText().get(1).updateText("X: "+ c.getComponent(TransformationComponent.class).position.x + " \nY: "+ c.getComponent(TransformationComponent.class).position.y + " \nZ: "+ c.getComponent(TransformationComponent.class).position.z);
		FontText.getAllText().get(2).updateText("Delta Time: "+ DisplayManager.DELTA);
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
		FontText.render();
	}

	@Override
	public void dispose() {
		FontText.dispose();
		for(Entity e : allEntity){
			e.dispose();
		}
		Audio.dispose();
		this.render.dispose();
		this.loader.destory();
	}
}
