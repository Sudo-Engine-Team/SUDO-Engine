package site.root3287.sudo.terrain;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.texture.ModelTexture;
import site.root3287.sudo.utils.LWJGLMaths;

public abstract class Terrain {
    public static int SIZE = 150;
    protected int lod;
    protected int vertexCount;
    protected float x;
    protected float z;
    protected int gridX;
    protected int gridZ;
    protected RawModel model;
    protected ModelTexture texture;
    protected float[][] heights;
    protected Loader loader;
    public Terrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount){
    	this.gridX = gridX;
    	this.gridZ = gridZ;
    	this.loader = loader;
    	this.vertexCount = vertexCount;
        this.texture = texture;
        this.x = gridX * SIZE;
        this.z = gridZ * SIZE;
        this.texture.setFakeLight(true);
    }
    
    public float getTerrainHeightByCoords(float worldX, float worldZ){
    	float terrainX = worldX - this.x;
		float terrainZ = worldZ - this.z;
		float gridSquareSize = SIZE / ((float) heights.length - 1);
		int gridX = (int) Math.floor(terrainX / gridSquareSize);
		int gridZ = (int) Math.floor(terrainZ / gridSquareSize);

		if (gridX >= heights.length - 1 || gridZ >= heights.length - 1 || gridX < 0 || gridZ < 0){
			return 0;
		}

		float xCoord = (terrainX % gridSquareSize) / gridSquareSize;
		float zCoord = (terrainZ % gridSquareSize) / gridSquareSize;
		float answer;

		if (xCoord <= (1 - zCoord)) {
			answer = LWJGLMaths.barryCentric(new Vector3f(0, heights[gridX][gridZ], 0), new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(0, heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			answer = LWJGLMaths.barryCentric(new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(1, heights[gridX + 1][gridZ + 1], 1), new Vector3f(0, heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}
		return answer;
    }
      
    public float getX() {
        return x;
    }
 
    public float getZ() {
        return z;
    }
 
    public RawModel getModel() {
        return model;
    }
 
    public ModelTexture getTexture() {
        return texture;
    }
    
    public int getGridX(){
    	return this.gridX;
    }
    public int getGridZ(){
    	return this.gridZ;
    }
    public abstract void update(float delta);
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Terrain){
    		if(((Terrain) obj).getGridX() == this.gridX && ((Terrain) obj).getGridZ() == this.gridZ){
    			return true;
    		}
    	}
    	return false;
    }
}
