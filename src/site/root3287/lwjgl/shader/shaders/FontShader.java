package site.root3287.lwjgl.shader.shaders;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.shader.Shader;

public class FontShader extends Shader{

	private static final String VERTEX_FILE = "res/shaders/fonts/fontVertex.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/fonts/fontFragment.glsl";
	
	private int location_colour;
	private int location_translation;
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_colour = getUniformLocation("colour");
		location_translation = getUniformLocation("translation");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
	public void loadColour(Vector3f colour){
		super.loadVector(location_colour, colour);
	}
	public void loadTranslation(Vector2f tranlation){
		super.loadVector(location_translation, tranlation);
	}

}
