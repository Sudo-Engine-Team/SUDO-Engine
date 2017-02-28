package site.root3287.lwjgl.shader.shaders;

import site.root3287.lwjgl.shader.Shader;

public class OldShader extends Shader{

	public OldShader() {
		super("res/shaders/old/vertex.glsl", "res/shaders/old/fragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

}
