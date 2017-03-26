package site.root3287.sudo.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.component.PlayerControlsComponent;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.objConverter.ModelData;
import site.root3287.sudo.engine.objConverter.OBJFileLoader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.NullEntity;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.FirstPerson;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.model.TexturedModel;
import site.root3287.sudo.screen.Screen;
import site.root3287.sudo.texture.ModelTexture;

public class ModelScreen extends Screen {
	public ModelScreen(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	private Camera camera;
	private Light sun;
	private List<Entity> allEntity = new ArrayList<Entity>();
	private List<Light> lights = new ArrayList<Light>();
	private RawModel model;
	@Override
	public void init() {
		sun = new Light(new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		lights.add(sun);
		camera = new FirstPerson(new Vector3f(0,0,0));
		
		Mouse.setGrabbed(camera.getComponent(PlayerControlsComponent.class).isGrabbed);
		ModelData data = OBJFileLoader.loadOBJ("res/model/standfordDragon/dragon.obj");
		RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("res/image/white.png")));
		Random rand = new Random();
		for(int i = 0; i < 1; i ++){
			Entity entity = new NullEntity(new Vector3f(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()), texturedModel);
			allEntity.add(entity);
		}
	}

	@Override
	public void update() {
		camera.update(DisplayManager.DELTA);
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		for(Entity e : this.allEntity){
			render.processEntity(e);
		}
		render.render(this.lights, camera);
	}

	@Override
	public void dispose() {
		loader.destory();
		render.dispose();
	}

}
