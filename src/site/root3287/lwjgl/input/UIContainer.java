package site.root3287.lwjgl.input;

import java.util.List;

public class UIContainer {
	private List<UIObject> object;
	private int x, y, width, height;
	public UIContainer(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void proccessObject(UIObject object){
		this.object.add(object);
	}
	public void render(){
		for(UIObject obj : object){
			obj.render();
		}
	}
	public void update(){
		for(UIObject obj : object){
			obj.update();
		}
	}
}
