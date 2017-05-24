package site.root3287.sudo.terrain.newPerlin;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.terrain.perlin.HeightGenerator;

public class PerlinTerrainGenerator implements Runnable{

	private float[][] heights;
	private float[] vertices;
	private float[] normals;
	private float[] textureCoords;
	private int[] indices;
	public int size;
	private int vpl;
	private HeightGenerator generator;
	public boolean finishedCalculations = false;
	
	public PerlinTerrainGenerator(int x, int y, int vertexCount, int size, int lod, long seed) {
		generator = new HeightGenerator(seed, vertexCount);
		this.size = size;
		int simplificationMultiplier = lod *2+1;
		vpl = vertexCount/simplificationMultiplier;
		int count = vpl * vpl;
		heights = new float[vpl][vpl];
		vertices = new float[count*3];
		normals = new float[count * 3];
		textureCoords = new float[count * 2];
		indices = new int[6 * (vpl - 1) * (vpl * 1)];
	}
	
	@Override
	public void run() {
		generateTerrain();
		setFinishedCalculations(true);
	}
	
	public synchronized float[][] getHeights() {
		return heights;
	}

	public synchronized void setHeights(float[][] heights) {
		this.heights = heights;
	}

	private void generateTerrain(){
		int vertexPointer = 0;
		for(int i =0; i < vpl; i++){
			for(int j = 0; j < vpl; j++){
				float height = generator.generateHeight(i, j);
				heights[j][i] = height;
            	
            	vertices[vertexPointer * 3] = (float) j / ((float) vpl - 1) * size;
				vertices[vertexPointer * 3 + 1] = height;
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) vpl - 1) * size;
				
				Vector3f normal = calculateNormal(j, i, generator);
				normals[vertexPointer * 3] = normal.x;
				normals[vertexPointer * 3 + 1] = normal.y;
				normals[vertexPointer * 3 + 2] = normal.z;
				
				textureCoords[vertexPointer * 2] = (float) j / ((float) vpl - 1);
				textureCoords[vertexPointer * 2 + 1] = (float) i / ((float) vpl - 1);
				
				vertexPointer++;
			}
		}
		
		int pointer = 0;
        for (int gz = 0; gz < vpl - 1; gz++) {
            for (int gx = 0; gx < vpl - 1; gx++) {
                int topLeft = (gz * vpl) + gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz + 1) * vpl) + gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
	}
	private Vector3f calculateNormal(int j, int i,HeightGenerator generator) {
		float heightL = generator.generateHeight(j-1, i);
    	float heightR = generator.generateHeight(j+1, i);
    	float heightD = generator.generateHeight(j, i-1);
    	float heightU = generator.generateHeight(j, i+1);
    	Vector3f normal = new Vector3f(heightL-heightR, 2f, heightD-heightU);
    	normal.normalise();
    	return normal;
	}
	
	public synchronized RawModel getTerrain(){
		if(!finishedCalculations){
			return null;
		}
		return Loader.getInstance().loadToVAO(vertices, textureCoords, normals, indices);
	}

	public synchronized boolean hasFinishedCalculations() {
		return finishedCalculations;
	}

	private synchronized void setFinishedCalculations(boolean finishedCalculations) {
		this.finishedCalculations = finishedCalculations;
	}
}
