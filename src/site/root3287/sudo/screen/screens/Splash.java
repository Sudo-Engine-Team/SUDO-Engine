package site.root3287.sudo.screen.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import site.root3287.sudo.engine.DisplayManager;
import site.root3287.sudo.engine.GameState;
import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.engine.render.Render;
import site.root3287.sudo.entities.Light;
import site.root3287.sudo.entities.Quad2D;
import site.root3287.sudo.entities.Camera.Camera;
import site.root3287.sudo.entities.Camera.Camera2D;
import site.root3287.sudo.font.FontText;
import site.root3287.sudo.font.FontType;
import site.root3287.sudo.font.GUIText;
import site.root3287.sudo.screen.Screen;

public class Splash extends Screen {
	private List<Quad2D> guis = new ArrayList<>();
	private Camera camera;
	private List<Light> suns = new ArrayList<>();
	private int messageCount = 0;
	private SoundSystem audio;
	public Splash(Render render, Loader loader, GameState state) {
		super(render, loader, state);
	}

	@Override
	public void init() {
		try {
			SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
			SoundSystemConfig.setCodec("ogg", CodecJOgg.class);
			SoundSystemConfig.setSoundFilesPackage("");
			SoundSystemConfig.setCodec("wav", CodecWav.class);
		} catch (SoundSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		audio = new SoundSystem();
		audio.backgroundMusic("HLHE", "Audio/Music/HE.ogg", true);
		FontText.init(loader);
		GUIText root = new GUIText("Root3287", 7, new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt"), 1, false), 
				new Vector2f(0,-0.75f), 1, true);
		GUIText message = new GUIText("Engine Made By:", 2, new FontType(loader.loadTexture("res/fonts/Arial/Arial.png"), new File("res/fonts/Arial/Arial.fnt"), 1, false), 
				new Vector2f(0,-0.70f), 1, true);
		root.setColour(1, 1, 1, 1f);
		message.setColour(1, 1, 1, 1);
		FontText.loadText(root);
		FontText.loadText(message);
		camera = new Camera2D(new Vector3f(0,0,0));
		
		Light light = new Light(new Vector3f(0,0,0), new Vector3f(0,0,0));
		suns.add(light);
		
		render.setBackgroundColour(new Vector4f(0,0,0,0));
	}

	@Override
	public void update() {
		GUIText primary = FontText.getAllText().get(0);
		GUIText above = FontText.getAllText().get(1);
		primary.setAlpha(primary.getAlpha()-0.005f);
		above.setAlpha(above.getAlpha()-0.005f);
		
		if(primary.getAlpha() < 0.0 && messageCount == 0){
			primary.setAlpha(1);
			primary.updateText("Sudo-Engine");
			messageCount++;
		}else if(primary.getAlpha() < 0.0 && messageCount == 1){
			primary.setAlpha(1);
			primary.updateText("");
			above.setAlpha(1);
			above.setFontSize(0.5f);
			above.updateText("Copyright (c) Timothy Gibbons. All Right Reserved."); 
			messageCount++;
		}else if(primary.getAlpha() < 0.0 && messageCount == 2){
			DisplayManager.setScreen(new TerrainScreen(render, loader, state));
		}
	}

	@Override
	public void render() {
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		for(Quad2D q : guis){
			render.processGUI(q);
		}
		render.render(suns, camera);
		FontText.render();
	}

	@Override
	public void dispose() {
		audio.cleanup();
		FontText.dispose();
	}

}
