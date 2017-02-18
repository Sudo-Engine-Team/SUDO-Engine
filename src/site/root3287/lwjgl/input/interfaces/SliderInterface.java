package site.root3287.lwjgl.input.interfaces;

import java.util.List;

import site.root3287.lwjgl.texture.Texture2D;

public interface SliderInterface {
	int value = 0;
	int max_value = 100;
	void getValue();
	void setValue(int value);
	void show(List<Texture2D> guiTextures);
	void hide(List<Texture2D> guiTextures);
	void onClick();
	void onDrag();
	void update();
}
