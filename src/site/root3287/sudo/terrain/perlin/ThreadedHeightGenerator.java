package site.root3287.sudo.terrain.perlin;

import site.root3287.sudo.event.events.FinishedHeightMappingEvent;

public class ThreadedHeightGenerator implements Runnable{
	public HeightGenerator generator;
	public boolean isComleted;
	private Thread thread;
	private int x, y, vertexCount;
	private long seed;
	private float[][] height;
	public ThreadedHeightGenerator(int x, int y, int vertexCount, long seed){
		generator = new HeightGenerator(x, y, vertexCount, seed);
		isComleted = false;
		this.thread = new Thread(this);
		thread.setDaemon(true);
		this.x = x;
		this.y = y;
		this.seed = seed;
		this.vertexCount = vertexCount;
		height = new float[vertexCount][vertexCount];
	}
	
	public void start(){
		thread.start();
	}

	@Override
	public void run() {
		for(int i = 0; i < this.vertexCount; i++){
			for(int j = 0; j < this.vertexCount; i++){
				this.height[i][j] = generator.generateHeight(i, j);
			}
		}
		PerlinWorld.finishedHeightMapDispatcher.dispatch(new FinishedHeightMappingEvent(getHeight(), this.x, this.y, this.vertexCount, this.seed));
	}
	
	public synchronized float[][] getHeight(){
		return this.height;
	}
}
