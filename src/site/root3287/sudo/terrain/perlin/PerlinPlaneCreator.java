package site.root3287.sudo.terrain.perlin;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.model.RawModel;

public class PerlinPlaneCreator {
	public float[][] heights;
	public RawModel generatePerlinPlane(Loader loader, int vertexCount, int size, HeightGenerator generator, int lod){
		int simplificationMultiplier = lod *2+1;
		int vpl = vertexCount/simplificationMultiplier;
		int count = vpl * vpl;
        this.heights = new float[vpl][vpl];
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (vpl - 1) * (vpl * 1)];
        int vertexPointer = 0;
        
        for (int i = 0; i < vpl; i++) {
            for (int j = 0; j < vpl; j++) {
            	
            	float height = getHeight(j, i, generator);
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
        return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}

	private Vector3f calculateNormal(int j, int i,HeightGenerator generator) {
		float heightL = getHeight(j-1, i, generator);
    	float heightR = getHeight(j+1, i, generator);
    	float heightD = getHeight(j, i-1, generator);
    	float heightU = getHeight(j, i+1, generator);
    	Vector3f normal = new Vector3f(heightL-heightR, 2f, heightD-heightU);
    	normal.normalise();
    	return normal;
	}
	private float getHeight(int x, int z, HeightGenerator generator){
    	return generator.generateHeight(x, z);
    }
}
