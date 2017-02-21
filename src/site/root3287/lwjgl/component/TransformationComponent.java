package site.root3287.lwjgl.component;

import org.lwjgl.util.vector.Vector3f;

public class TransformationComponent extends Component{

	private static final String NAME = "transformation";
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f rotation = new Vector3f(0, 0, 0);
	private float scale = 1;

	public TransformationComponent() {
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
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
}
