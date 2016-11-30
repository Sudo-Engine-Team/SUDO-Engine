package site.root3287.lwjgl.texture;

public class ModelTexture {
	private int textureID;
	
	private float shineDamper;
	private float reflectivity;
	
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}

	public ModelTexture(int textureId){
		this.textureID = textureId;
	}
	
	public int getTextureID(){
		return this.textureID;
	}
}
