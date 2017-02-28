package site.root3287.lwjgl.component;

import org.lwjgl.util.vector.Vector3f;

public class TransformationComponent extends Component{
	
	public Vector3f position = new Vector3f(0,0,0);
	public Vector3f rotation = new Vector3f(0, 0, 0);
	public float scale = 1;

	public TransformationComponent() {
		this.name ="transformation";
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void destroy() {
		
	}
}
