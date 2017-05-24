package site.root3287.sudo.terrain.newPerlin;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;

public class PerlinTerrain extends Terrain{
	private long seed;
	private int x;
	private int y;
	private int lod;
	private PerlinTerrainGenerator generator;
	public PerlinTerrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount, long seed) {
		super(gridX, gridZ, loader, texture, vertexCount);
		this.seed = seed;
		generator = new PerlinTerrainGenerator(x, y, vertexCount, SIZE, lod, this.seed);
		Thread thread = new Thread(generator);
		thread.start();
		while(!generator.hasFinishedCalculations()){
			
		}
		this.model = generator.getTerrain();
		this.heights = generator.getHeights();
	}
	
	@Override
	public void update(float delta) {
		
	}
}
