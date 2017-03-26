package site.root3287.sudo.component;

import org.lwjgl.util.vector.Vector3f;

public class TransformationComponent extends Component{
	
	public Vector3f position = new Vector3f(0,0,0);
	public Vector3f velocity = new Vector3f(0, 0, 0);
	public Vector3f rotation = new Vector3f(0, 0, 0);
	public float scale = 1, pitch = 0, yaw = 0, roll = 0;
	public int direction = 0;
}
