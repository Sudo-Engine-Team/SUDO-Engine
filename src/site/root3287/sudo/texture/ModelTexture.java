package site.root3287.sudo.texture;

public class ModelTexture {
	private int textureID;
    
    private float shineDamper = 1;
    private float reflectivity = 0;
    
    private boolean hasTranspancy = false;
    private boolean useFakeLight = false;
    
    public ModelTexture(int texture){
        this.textureID = texture;
    }
	public int getTextureID() {
		return textureID;
	}
	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}
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
	public boolean hasTranspancy() {
		return hasTranspancy;
	}
	public void setTranspancy(boolean hasTranspancy) {
		this.hasTranspancy = hasTranspancy;
	}
	public boolean useFakeLight() {
		return useFakeLight;
	}
	public void setFakeLight(boolean useFakeLight) {
		this.useFakeLight = useFakeLight;
	}
	
}
