package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.LWJGL;
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

	public Test() {
		super();
	}

	public Test(Render render, Loader loader) {
		super(render, loader);
	}

	@Override
	public void init() {
		this.c = new Camera(new Vector3f(0, 3.5f, 0));
		Mouse.setGrabbed(c.isGrabbed());
		
		ModelData stallFile = OBJFileLoader.loadOBJ("res/model/standfordBunny/bunny.obj");
		RawModel stallModel = this.loader.loadToVAO(stallFile.getVertices(), stallFile.getTextureCoords(), stallFile.getNormals(), stallFile.getIndices());
		TexturedModel stallTexture = new TexturedModel(stallModel, new ModelTexture(this.loader.loadTexture("res/image/white.png")));
		this.e = new Entity(stallTexture, new Vector3f(0,0,0), 0, 0, 0, 1);
		
        this.light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        
        Terrain t1 = new Terrain(0,
        						0, 
        						this.loader, 
        						new ModelTexture(
        								this.loader.loadTexture("res/image/grass.png")
        						), 
        						64, 
        						new Random().nextInt()
        );
        //Terrain t2 = new Terrain(1,0, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
       // Terrain t3 = new Terrain(0,1, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
       // Terrain t4 = new Terrain(1,1, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
       allTerrain.add(t1);
       // allTerrain.add(t2);
       // allTerrain.add(t3);
       // allTerrain.add(t4);
        
        WaterShader ws = new WaterShader();
        WaterRender wr = new WaterRender(this.loader, ws, this.render.getProjectionMatrix());
        WaterTile w1 = new WaterTile(0, 0, 20);
        allWater.add(w1);
	}

	@Override
	public void update() {
		c.update(DisplayManager.DELTA);
	}

	@Override
	public void render() {
		this.render.processEntity(e);
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
	}
	
}
