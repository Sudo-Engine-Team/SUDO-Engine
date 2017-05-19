package site.root3287.sudo.input.objects;

import site.root3287.sudo.input.UIObject;

public abstract class UILabel extends UIObject{

	private float textX;
    private float textY;

    private String text;

    //TODO: the abstract should take and store the text.
    public UILabel(String string, float x, float y, float width, float height) {
        this.text = string;
    }

    @Override
    public void update() {
        //No need to update a label.
    }

    public abstract void render();
    /*
    protected abstract void calculateStartingPositions();


    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        super.setVerticalAlignment(verticalAlignment);
        calculateStartingPositions();
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        super.setHorizontalAlignment(horizontalAlignment);
        calculateStartingPositions();
    }
    
    public UILabel setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
        calculateStartingPositions();

        return this;
    }
*/
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public float getTextY() {
		return textY;
	}

	public void setTextY(float textY) {
		this.textY = textY;
	}

	public float getTextX() {
		return textX;
	}

	public void setTextX(float textX) {
		this.textX = textX;
	}

}
