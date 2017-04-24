package site.root3287.sudo.terrain.perlin;

import site.root3287.sudo.engine.Loader;
import site.root3287.sudo.terrain.Terrain;
import site.root3287.sudo.texture.ModelTexture;

public class PerlinTerrain extends Terrain{
	
	private int seed;
	public boolean updateMesh = false;
	private float cooldown = 0;
	private PerlinPlaneCreator plane;
	private HeightGenerator generator;
	
	public PerlinTerrain(int gridX, int gridZ, Loader loader, ModelTexture texture, int vertexCount, int seed){
		super(gridX, gridZ, loader, texture, vertexCount);
		this.lod = 1;
    	this.seed = seed;
        generator = new HeightGenerator(gridX, gridZ, this.vertexCount, this.seed);
        plane = new PerlinPlaneCreator();
        this.model = plane.generatePerlinPlane(loader, this.vertexCount, SIZE, generator, lod);
        this.heights = plane.heights;
    }

	@Override
	public void update(float delta) {
	/*	if(cooldown > 0){
			System.out.println(cooldown);
			this.cooldown -= delta;
		}
		if(cooldown < 0){
			cooldown = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_C) && cooldown == 0){
			lod++;
			this.loader.removeVAO(this.model.getVaoID());
			this.model = plane.generatePerlinPlane(loader, vertexCount, SIZE, generator, lod);
			this.heights = plane.heights;
			this.cooldown = 5;
		}
		*/
	}
}
