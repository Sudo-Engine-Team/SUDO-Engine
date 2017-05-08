package site.root3287.sudo.terrain.perlin;

import java.util.Random;

public class HeightGenerator {
	private static final float AMPLITUDE = 85;
    private static final int OCTAVES = 3;
    private static final float ROUGHNESS = 0.3f;
 
    private Random random = new Random();
    private long seed;
    private int xOffset = 0;
    private int zOffset = 0;
 
    public HeightGenerator() {
        this.seed = random.nextInt(1000000000);
    }
    
    public HeightGenerator(int gridX, int gridZ, int vertexCount, String seed){
    	for(char x : seed.toCharArray()){
    		int position = x-' ';
    		int temp = (int) Math.pow(x*31, position);
    		seed += temp;
    	}
    	xOffset = gridX * (vertexCount-1);
        zOffset = gridZ * (vertexCount-1);
    }
    public HeightGenerator(int gridX, int gridZ, int vertexCount, long seed){
    	this.seed = seed;
    	xOffset = gridX * (vertexCount-1);
        zOffset = gridZ * (vertexCount-1);
    }
     
    //only works with POSITIVE gridX and gridZ values!
    public HeightGenerator(int gridX, int gridZ, int vertexCount, long seed) {
        this.seed = seed;
        xOffset = gridX * (vertexCount-1);
        zOffset = gridZ * (vertexCount-1);
    }
 
    public float generateHeight(int x, int z) {
        float total = 0;
        float d = (float) Math.pow(2, OCTAVES-1);
        for(int i=0;i<OCTAVES;i++){
            float freq = (float) (Math.pow(2, i) / d);
            float amp = (float) Math.pow(ROUGHNESS, i) * AMPLITUDE;
            total += getInterpolatedNoise((x+xOffset)*freq, (z + zOffset)*freq) * amp;
        }
        return total;
    }
    public float generateHeight2(int x, int z) {
        float total = 0;
        total += getInterpolatedNoise(x+xOffset/4, z+zOffset/4) *AMPLITUDE;
        total += getInterpolatedNoise(x+xOffset/2, z+zOffset/2) *AMPLITUDE /3;
        total += getInterpolatedNoise(x+xOffset/1, z+zOffset/1) *AMPLITUDE /9;
        return total;
    }
    
    /**
     * Final perlin nose
     * @param x x position
     * @param z y position
     * @return float height
     */
    private float getInterpolatedNoise(float x, float z){
        int intX = (int) x;
        int intZ = (int) z;
        float fracX = x - intX;
        float fracZ = z - intZ;
         
        float v1 = getSmoothNoise(intX, intZ);
        float v2 = getSmoothNoise(intX + 1, intZ);
        float v3 = getSmoothNoise(intX, intZ + 1);
        float v4 = getSmoothNoise(intX + 1, intZ + 1);
        float i1 = interpolate(v1, v2, fracX);
        float i2 = interpolate(v3, v4, fracX);
        return interpolate(i1, i2, fracZ);
    }
     
    private float interpolate(float a, float b, float blend){
        double theta = blend * Math.PI;
        float f = (float)(1f - Math.cos(theta)) * 0.5f;
        return a * (1f - f) + b * f;
    }
 
    private float getSmoothNoise(int x, int z) {
        float corners = (getNoise(x - 1, z - 1) + getNoise(x + 1, z - 1) + getNoise(x - 1, z + 1)
                + getNoise(x + 1, z + 1)) / 16f;
        float sides = (getNoise(x - 1, z) + getNoise(x + 1, z) + getNoise(x, z - 1)
                + getNoise(x, z + 1)) / 8f;
        float center = getNoise(x, z) / 4f;
        return corners + sides + center;
    }
 
    private float getNoise(int x, int z) {
        random.setSeed(x * 49632 + z * 325176 + seed);
        return random.nextFloat() * 2f - 1f;
    }
}
