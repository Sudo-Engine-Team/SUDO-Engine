package site.root3287.sudo.input;

public interface UIActions {
	void onClick();
	void onClickDrag();
	boolean isHover(int x, int y);
	void onHover();
	void offHover();
	void whileHover();
	void update();
}
