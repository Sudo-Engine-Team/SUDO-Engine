package site.root3287.lwjgl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.Entities.Camera;
import site.root3287.lwjgl.Entities.Entity;
import site.root3287.lwjgl.Entities.Light;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.objConverter.ModelData;
import site.root3287.lwjgl.engine.objConverter.OBJFileLoader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.water.WaterRender;
import site.root3287.lwjgl.water.WaterShader;
import site.root3287.lwjgl.water.WaterTile;

public class LWJGL {
	public static int WIDTH = 900, HEIGHT = WIDTH/16*9, FPS_CAP = 500;
	public static String TITLE = "LWJGL ENGINE";
	public static long lastTime;
	public static float DELTA;
	public static void main(String[] args){
		new LWJGL();
	}
	public LWJGL(){
		LWJGL.createDisplay();
		Camera c = new Camera(new Vector3f(0, 3.5f, 0));
		Mouse.setGrabbed(c.isGrabbed());
		Loader l = new Loader();
		Render r = new Render();
		
		ModelData stallFile = OBJFileLoader.loadOBJ("res/model/standfordBunny/bunny.obj");
		RawModel stallModel = l.loadToVAO(stallFile.getVertices(), stallFile.getTextureCoords(), stallFile.getNormals(), stallFile.getIndices());
		TexturedModel stallTexture = new TexturedModel(stallModel, new ModelTexture(l.loadTexture("res/image/white.png")));
        Entity e = new Entity(stallTexture, new Vector3f(0,0,0), 0, 0, 0, 1);
        Light light = new Light(new Vector3f(0,100000000,0), new Vector3f(5, 5, 5));
        
        List<Terrain> allTerrain = new ArrayList<Terrain>();
        Terrain t1 = new Terrain(0,0, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, new Random().nextInt());
        //Terrain t2 = new Terrain(1,0, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
       // Terrain t3 = new Terrain(0,1, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
       // Terrain t4 = new Terrain(1,1, l, new ModelTexture(l.loadTexture("res/image/grass.png")), 64, 1234);
        allTerrain.add(t1);
       // allTerrain.add(t2);
       // allTerrain.add(t3);
       // allTerrain.add(t4);
        
        WaterShader ws = new WaterShader();
        WaterRender wr = new WaterRender(l, ws, r.getProjectionMatrix());
        List<WaterTile> allWater = new ArrayList<WaterTile>();
        WaterTile w1 = new WaterTile(0, 0, 20);
        allWater.add(w1);
		while(!Display.isCloseRequested()){
			LWJGL.DELTA = getDelta();
			c.update(LWJGL.DELTA);
			r.processEntity(e);
			for(Terrain t:allTerrain){
				r.processTerrain(t);
			}
			r.render(light, c);
			wr.render(allWater, c);
			LWJGL.updateDisplay();
		}
		r.dispose();
		l.destory();
		LWJGL.closeDisplay();
	}
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setResizable(true);
			Display.setDisplayMode(new DisplayMode(LWJGL.WIDTH, LWJGL.HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(LWJGL.TITLE);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, LWJGL.WIDTH, LWJGL.HEIGHT);
	}
	public static void updateDisplay(){
		if(Display.wasResized()){
			LWJGL.WIDTH = Display.getWidth();
			LWJGL.HEIGHT = Display.getHeight();
			GL11.glViewport(0, 0, LWJGL.WIDTH, LWJGL.HEIGHT);
		}
		Display.sync(LWJGL.FPS_CAP);
		Display.update();
	}
	public static void closeDisplay(){
		Display.destroy();
	}
	public float getDelta() {
	    long time = getTime();
	    float delta = (int) (time - LWJGL.lastTime)/1000f;
	    LWJGL.lastTime = time;

	    return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
