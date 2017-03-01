package site.root3287.lwjgl.component;

import org.lwjgl.util.vector.Vector3f;

public class TransformationComponent implements Component{
	
	public Vector3f position = new Vector3f(0,0,0);
	public Vector3f rotation = new Vector3f(0, 0, 0);
	public float scale = 1;
}
