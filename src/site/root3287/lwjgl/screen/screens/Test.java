package site.root3287.lwjgl.screen.screens;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.audio.Audio;
import site.root3287.lwjgl.component.PlayerControlsComponent;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.GameState;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.NullEntity;
import site.root3287.lwjgl.entities.Camera.Camera;
import site.root3287.lwjgl.entities.Camera.FirstPerson;
import site.root3287.lwjgl.entities.model.StandfordBunny;
import site.root3287.lwjgl.font.FontText;
import site.root3287.lwjgl.font.FontType;
import site.root3287.lwjgl.font.GUIText;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.world.World;

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
		
		// text 0
		FontText.loadText(new GUIText("Version Alpha 0.0.1", 
				1, 
				new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt")), 
				new Vector2f(0, 0), 1, true));
		
		//int seed = new Random().nextInt();
		int seed = -1251497298;
        this.world = new World(this.loader, seed);
		this.c = new FirstPerson(new Vector3f(0, 10f, 20));
		Mouse.setGrabbed(c.getComponent(PlayerControlsComponent.class).isGrabbed);
        this.light = new Light(new Vector3f(20,100000000,20), new Vector3f(7, 7, 7));
        this.lights.add(light);
        allEntity.add(new StandfordBunny(loader));
        
        //Text 1
        FontText.loadText(new GUIText("X: "+ c.getComponent(TransformationComponent.class).position.x + " Y: "+ c.getComponent(TransformationComponent.class).position.y + " Z: "+ c.getComponent(TransformationComponent.class).position.z, 
				1, 
				new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt")), 
				new Vector2f(-0.25f, 0), 1, true));
        //Text 2
        FontText.loadText(new GUIText("Delta Time: "+ DisplayManager.DELTA, 
				1, 
				new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt")), 
				new Vector2f(0.25f, 0), 1, true));
    }

	@Override
	public void update() {
		c.update(world.getTerrainForCollision(), DisplayManager.DELTA);
		allEntity.get(0).update(DisplayManager.DELTA);
		FontText.getAllText().get(1).updateText("X: "+ c.getComponent(TransformationComponent.class).position.x + " Y: "+ c.getComponent(TransformationComponent.class).position.y + " Z: "+ c.getComponent(TransformationComponent.class).position.z);
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
