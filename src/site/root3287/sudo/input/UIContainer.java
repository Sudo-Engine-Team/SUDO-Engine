package site.root3287.sudo.input;

import java.util.List;

public class UIContainer {
	private List<UIObject> object;
	private int x, y, width, height;
	public UIContainer(int x, int y, int width, int height){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
