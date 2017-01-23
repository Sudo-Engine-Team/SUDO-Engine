package site.root3287.lwjgl.utils;

import java.util.Random;

public class RandomNumberGenerator {
	private int seed;
	private Random random;
	public RandomNumberGenerator(){
		this.random = new Random();
		this.seed = this.random.nextInt();
		this.random.setSeed(this.seed);
	}
	public RandomNumberGenerator(int seed){
		this.seed = seed;
		this.random = new Random(this.seed);
	}
	public int getRNGInt(int low, int high){
		return this.random.nextInt() % ((high-low)+1)+low;
	}
	public float getRNGFloat(float low, float high){
		return this.random.nextFloat() * (high-low+1) + low;
	}
}
