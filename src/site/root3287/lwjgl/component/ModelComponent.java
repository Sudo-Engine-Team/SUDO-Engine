package site.root3287.lwjgl.component;

import site.root3287.lwjgl.model.TexturedModel;

public class ModelComponent extends Component{
	private TexturedModel model; 
	private static final String NAME = "model";
	public ModelComponent() {
		super(NAME);
	}

	@Override
	protected void start() {
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void destroy() {
		
	}
	
	public void setTexturedModel(TexturedModel model){
		this.model = model;
	}
	public TexturedModel getTexturedModel(){
		return this.model;
	}

}
