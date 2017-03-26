package site.root3287.sudo.input.objects;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import site.root3287.sudo.input.UIObject;

public class UISlider extends UIObject{

	private float sliderPosition;
	private boolean activate = false;
	
	@Override
	public void render() {
		
	}

	@Override
	public void update() {
		if(Mouse.getX()>getX() && Mouse.getX()<getX()+getWidth()){
            int mousey = Display.getHeight() - Mouse.getY();

            if(mousey>getY() && mousey<getY()+getHeight()){
                if(Mouse.isButtonDown(0)){
                    activate=true;
                }
            }
        }

        if(activate){
            if(!Mouse.isButtonDown(0)){
                activate=false;
            }
            else{
                sliderPosition = (Mouse.getX()-getX()) <= getWidth() ? (Mouse.getX()-getX()) : getWidth();
                sliderPosition = sliderPosition>=0? sliderPosition:0;
            }
		}
	}
	public void setSliderPosition(int percentage){
        sliderPosition = percentage;
	}
	public int getPercentage(){
        return (int)((sliderPosition/getWidth()) * 100);
    }

    public float getSliderPosition() {
        return sliderPosition;
    }
}
