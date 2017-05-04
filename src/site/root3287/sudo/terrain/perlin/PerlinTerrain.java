package site.root3287.sudo.terrain.perlin;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.model.RawModel;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;

public class PerlinTerrain extends Terrain{
	
	private long seed;
	private HeightGenerator generator;
	public PerlinTerrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount, long seed, float[][] heights){
		super(gridX, gridZ, loader, texture, vertexCount);
    	this.seed = seed;
        this.heights = heights;
        generator = new HeightGenerator(gridX, gridZ, this.vertexCount, this.seed);
        this.model = generateTerrain(loader, true);
        this.texture.setFakeLight(true);
    }
	
	public PerlinTerrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount, long seed){
		super(gridX, gridZ, loader, texture, vertexCount);
    	this.seed = seed;
    	generator = new HeightGenerator(gridX, gridZ, this.vertexCount, this.seed);
        this.model = generateTerrain(loader);
        this.texture.setFakeLight(true);
    }
	
	private float getHeight(int x, int z){
    	return heights[x][z];
    }
	
	protected Vector3f calculateNormal(int x, int z){
		float heightL = heights[x-1][z];
    	float heightR = heights[x+1][z];
    	float heightD = heights[x][z-1];
    	float heightU = heights[x][z+1];
    	Vector3f normal = new Vector3f(heightL-heightR, 1f, heightD-heightU);
    	normal.normalise();
    	return normal;
    }
	
	protected RawModel generateTerrain(Loader loader, boolean l){
        int VERTEX_COUNT = this.vertexCount;
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (VERTEX_COUNT - 1) * (VERTEX_COUNT * 1)];
        int vertexPointer = 0;
        
        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
            	heights[i][j] = generator.generateHeight(i, j);
            	
            	vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer * 3 + 1] = heights[i][j];
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
				
				Vector3f normal = calculateNormal(j, i);
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
	
	protected RawModel generateTerrain(Loader loader){
        int VERTEX_COUNT = this.vertexCount;
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (VERTEX_COUNT - 1) * (VERTEX_COUNT * 1)];
        int vertexPointer = 0;
        this.heights = new float[vertexCount][vertexCount];
        
        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
            	heights[i][j] = generator.generateHeight(i, j);
            	
            	vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer * 3 + 1] = heights[i][j];
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
				
				Vector3f normal = calculateNormal(j, i);
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
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
