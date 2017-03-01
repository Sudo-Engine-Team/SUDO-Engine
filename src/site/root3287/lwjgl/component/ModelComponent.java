package site.root3287.lwjgl.component;

import site.root3287.lwjgl.model.TexturedModel;

public class ModelComponent implements Component{
	public TexturedModel model; 
	public ModelComponent(TexturedModel model) {
		this.model = model;
	}
}
