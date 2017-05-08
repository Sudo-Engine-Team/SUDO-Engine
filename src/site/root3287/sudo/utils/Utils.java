package site.root3287.sudo.utils;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

public class Utils {
	/**
	 * Compare a List of Vector2f to a list of Vector2f
	 * @param a first input
	 * @param b second input
	 * @return a list of added item from a to b
	 */
	public static List<Vector2f> compareAdded(List<Vector2f> a, List<Vector2f> b){
		List<Vector2f> union = new ArrayList<>(a);
		union.removeAll(b);
		return union;
	}
	
	/**
	 * Compare a List of Vector2f to a list of Vector2f
	 * @param b - first input
	 * @param a - second input
	 * @return a list of removed items from b to a.
	 */
	public static List<Vector2f> compareRemoved(List<Vector2f> b, List<Vector2f> a){
		List<Vector2f> union = new ArrayList<>(a);
		union.removeAll(b);
		return union;
	}
	
	/**
	 * Get a list of Vector2f in a radius.
	 * @param chunkPosition - the coordnates of the center.
	 * @param radius - how far out
	 * @return a list of Vector2f positions.
	 */
	public static List<Vector2f> getPositionsInRadius(Vector2f chunkPosition, int radius){
	    List<Vector2f> result = new ArrayList<>();
	    for (int zCircle = -radius; zCircle <= radius; zCircle++){
	        for (int xCircle = -radius; xCircle <= radius; xCircle++){
	            if (xCircle * xCircle + zCircle * zCircle < radius * radius)
	                result.add(new Vector2f(chunkPosition.x + xCircle, chunkPosition.y + zCircle));
	        }
	    }
	    return result;
	}
}
