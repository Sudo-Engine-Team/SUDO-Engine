package site.root3287.lwjgl;

import java.util.ArrayList;
import java.util.List;

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
import site.root3287.lwjgl.engine.OBJLoader;
import site.root3287.lwjgl.engine.render.EntityRender;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.texture.ModelTexture;

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
		EntityRender er = new EntityRender();
		
        RawModel model = OBJLoader.loadObjModel("res/model/standfordDragon/dragon.obj", l);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(l.loadTexture("res/image/image.png")));
        staticModel.getTexture().setReflectivity(0.2f);
        staticModel.getTexture().setShineDamper(1);
        Entity e = new Entity(staticModel, new Vector3f(0,0,-1),0,0,0,1f);
		List<Entity> allEntities = new ArrayList<Entity>();
		allEntities.add(e);
        
        Light light = new Light(new Vector3f(0,0,0), new Vector3f(1, 1, 1));
		while(!Display.isCloseRequested()){
			LWJGL.DELTA = getDelta();
			c.update(LWJGL.DELTA);
			//System.out.println(LWJGL.DELTA);
			for(Entity e1:allEntities){
				er.processEntity(e1);
			}
			er.render(light, c);
			LWJGL.updateDisplay();
		}
		er.dispose();
		l.destory();
		LWJGL.closeDisplay();
	}
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
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
