package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

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
		//int seed = new Random().nextInt();
		int seed = 123;
        this.world = new World(this.loader, seed);
		this.c = new FirstPerson(new Vector3f(0, 10f, 20));
		Mouse.setGrabbed(((FirstPerson) c).isGrabbed());
        this.light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        ModelData data = OBJFileLoader.loadOBJ("res/model/lowPolyTree.obj");
        this.entity = new NullEntity(
        		new Vector3f(0, this.world.getTerrainForCollision().get(0).get(0).getTerrainHeightByCoords(0, 0), 0), 
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
