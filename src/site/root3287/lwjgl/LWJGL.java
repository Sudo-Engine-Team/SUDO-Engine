package site.root3287.lwjgl;

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
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.shader.StaticShader;
import site.root3287.lwjgl.texture.ModelTexture;

public class LWJGL {
	public static int WIDTH = 900, HEIGHT = WIDTH/16*9, FPS_CAP = 500;
	public static String TITLE = "LWJGL ENGINE";
	public static float DELTA, lastTime;
	public static void main(String[] args){
		new LWJGL();
	}
	public LWJGL(){
		LWJGL.createDisplay();
		Camera c = new Camera(new Vector3f(0, 3.5f, 0));
		Mouse.setGrabbed(c.isGrabbed());
		StaticShader s = new StaticShader();
		Loader l = new Loader();
		Render r = new Render(s);
		
        RawModel model = OBJLoader.loadObjModel("res/model/standfordDragon/dragon.obj", l);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(l.loadTexture("res/image/image.png")));
        staticModel.getTexture().setReflectivity(10);
        staticModel.getTexture().setShineDamper(1);
        Entity e = new Entity(staticModel, new Vector3f(0,0,-1),0,0,0,1f);
		
        Light light = new Light(new Vector3f(0,0,0), new Vector3f(1, 1, 1));
		while(!Display.isCloseRequested()){
			c.update(LWJGL.DELTA);
			r.prepare();
			s.start();
			s.loadLight(light);
			s.loadViewMatrix(c);
			r.render(e,s);
			s.stop();
			LWJGL.updateDisplay();
		}
		s.dispose();
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
		float time = Sys.getTime();
        LWJGL.DELTA = (time - LWJGL.lastTime)/1000.0f;
        LWJGL.lastTime = time;
	}
	public static void closeDisplay(){
		Display.destroy();
	}
}
