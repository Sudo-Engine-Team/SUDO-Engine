package site.root3287.lwjgl.input.objects;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import site.root3287.lwjgl.input.UIObject;

public abstract class UITextbox extends UIObject {
	private  boolean active = false;
	protected int line = 0;
	protected boolean multiLine = false;
	private ArrayList<StringBuffer> stringBuffer = new ArrayList<StringBuffer>();
	
	@Override
	public void render() {
		
	}

	@Override
	public void update() {
		if(active){
			while(Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					String key = Keyboard.getKeyName(Keyboard.getEventKey());
					 if (key != null) {
						 if (key.toUpperCase().equals("SPACE")) {
							 stringBuffer.get(line).append(" ");
						 } else if (key.toUpperCase().equals("BACK")) {
							 if (stringBuffer.get(line).length() != 0) {
								 stringBuffer.get(line).deleteCharAt(stringBuffer.get(line).length() - 1);
							 } else {
								 if (multiLine) {
									 stringBuffer.remove(line);
									 line--;
								 }
							 }
						 } else if (key.toUpperCase().equals("LSHIFT") || key.toUpperCase().equals("RSHIFT") || key.toUpperCase().equals("LCONTROL") || key.toUpperCase().equals("RCONTROL")) {
	                            //NOTHING
						 } else if (key.toUpperCase().equals("RETURN")) {
	                            if (multiLine) {
	                                line++;
	                                stringBuffer.add(new StringBuffer());
	                            }
	                        } else {
	                            stringBuffer.get(line).append(key);
	                        }
					 }
				}
			}
		}
	}
	public void activate(){
		this.active = true;
	}
	public void deactivate(){
		this.active = false;
	}
}
