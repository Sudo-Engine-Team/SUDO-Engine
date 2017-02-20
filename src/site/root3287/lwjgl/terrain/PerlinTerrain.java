package site.root3287.lwjgl.terrain;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.texture.ModelTexture;

public class PerlinTerrain extends Terrain{
	
	private int seed;
	
	public PerlinTerrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount, int seed){
		super(gridX, gridZ, loader, texture, vertexCount);
    	this.seed = seed;
        HeightGenerator generator = new HeightGenerator(gridX, gridZ, this.vertexCount, this.seed);
        this.model = generateTerrain(loader, generator);
        this.texture.setFakeLight(true);
    }
	private float getHeight(int x, int z, HeightGenerator generator){
    	return generator.generateHeight(x, z);
    }
	
	protected Vector3f calculateNormal(int x, int z, HeightGenerator generator){
    	float heightL = getHeight(x-1, z, generator);
    	float heightR = getHeight(x+1, z, generator);
    	float heightD = getHeight(x, z-1, generator);
    	float heightU = getHeight(x, z+1, generator);
    	Vector3f normal = new Vector3f(heightL-heightR, 2f, heightD-heightU);
    	normal.normalise();
    	return normal;
    }
	
	
	protected RawModel generateTerrain(Loader loader, HeightGenerator generator){
        int VERTEX_COUNT = this.vertexCount;
        int count = VERTEX_COUNT * VERTEX_COUNT;
        heights = new float[VERTEX_COUNT][VERTEX_COUNT];
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (VERTEX_COUNT - 1) * (VERTEX_COUNT * 1)];
        int vertexPointer = 0;
        
        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
            	
            	float height = getHeight(j, i, generator);
				heights[j][i] = height;
            	
            	vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer * 3 + 1] = height;
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
				
				Vector3f normal = calculateNormal(j, i, generator);
				normals[vertexPointer * 3] = normal.x;
				normals[vertexPointer * 3 + 1] = normal.y;
				normals[vertexPointer * 3 + 2] = normal.z;
				
				textureCoords[vertexPointer * 2] = (float) j / ((float) VERTEX_COUNT - 1);
				textureCoords[vertexPointer * 2 + 1] = (float) i / ((float) VERTEX_COUNT - 1);
				
				vertexPointer++;
            }
        }
        
        int pointer = 0;
        for (int gz = 0; gz < VERTEX_COUNT - 1; gz++) {
            for (int gx = 0; gx < VERTEX_COUNT - 1; gx++) {
                int topLeft = (gz * VERTEX_COUNT) + gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz + 1) * VERTEX_COUNT) + gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
        return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	@Override
	protected RawModel generateTerrain(Loader loader) {
		return null;
	}
}
