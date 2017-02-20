package site.root3287.lwjgl.screen.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Camera;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.entities.FirstPerson;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.entities.Quad2D;
import site.root3287.lwjgl.fontMeshCreator.FontType;
import site.root3287.lwjgl.fontMeshCreator.GUIText;
import site.root3287.lwjgl.input.objects.UIText;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.net.client.Client;
import site.root3287.lwjgl.net.server.Server;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.texture.Texture2D;
import site.root3287.lwjgl.world.World;

public class Test extends Screen{
	
	@SuppressWarnings("unused")
	private List<Terrain> allTerrain = new ArrayList<Terrain>();
	private List<Entity> allEntity = new ArrayList<Entity>();
	private Light light;
	private Camera c;
	@SuppressWarnings("unused")
	private Server server;
	@SuppressWarnings("unused")
	private Client client;
	private World world;
	@SuppressWarnings("unused")
	private Texture2D texture;
	@SuppressWarnings("unused")
	private Quad2D quad;
	
	private RawModel cup;
	UIText text;
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
		
		//int seed = new Random().nextInt();
		int seed = 123;
        this.world = new World(this.loader, seed);
		
		this.c = new FirstPerson(new Vector3f(0, 10f, 20));
		Mouse.setGrabbed(((FirstPerson) c).isGrabbed());
		
        this.light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        
        this.quad = new Quad2D(this.loader, 
        				new Texture2D(this.loader.loadTexture("res/image/grass.png"), 
        				new Vector2f(0.25f, 0.25f), 
        				new Vector2f(1,1))
        				);
        RawModel cup = OBJLoader.loadObjModel("res/model/Cup/cup.obj", loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("res/model/Cup/cup.png"));
        TexturedModel model = new TexturedModel(cup, texture);
        Entity e = new Entity(model, new Vector3f(0, 0, 0), 0, 0, 0, 1);
        this.allEntity.add(e);
        UIText.init(loader);
		FontType font = new FontType(loader.loadTexture("res/Fonts/Times New Roman/TNR.png"), new File("res/Fonts/Times New Roman/TNR.fnt"));
		GUIText text = new GUIText("this is a test", 12, font, new Vector2f(0, 0), 1f, true);
		text.setColour(1, 0, 1);
		UIText.loadText(text);
	}

	@Override
	public void update() {
		c.update(world.getTerrainForCollision(), DisplayManager.DELTA);
	}

	@Override
	public void render() {
		GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
		for(Terrain t: this.world.getTerrains()){
			this.render.processTerrain(t);
		}
		for(Entity e : this.allEntity){
			this.render.processEntity(e);
		}
		//this.render.proccess2D(this.quad);
		this.render.render(light, c);
		UIText.render();
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
		UIText.dispose();
		//this.server.close();
	}
	
}
