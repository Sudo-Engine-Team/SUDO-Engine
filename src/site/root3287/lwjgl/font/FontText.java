package site.root3287.lwjgl.font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.font.render.FontRender;

public class FontText {
	public static Loader loader;
    private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
    private static List<GUIText> allText = new ArrayList<>();
    private static FontRender render;
     
    public static void init(Loader theLoader){
        render = new FontRender();
        loader = theLoader;
    }
     
    public static void render(){
        render.render(texts);
    }
    
    /**
     * Load the text to the rendering system...
     * @param text The loaded text
     * @return int Text id;
     */
    public static int loadText(GUIText text){
        FontType font = text.getFont();
        TextMeshData data = font.loadText(text);
        int vao = loader.loadText(data.getVertexPositions(), data.getTextureCoords());
        text.setMeshInfo(vao, data.getVertexCount());
        List<GUIText> textBatch = texts.get(font);
        if(textBatch == null){
            textBatch = new ArrayList<GUIText>();
            texts.put(font, textBatch);
        }
        textBatch.add(text);
        allText.add(text);
        return allText.size();
    }
     
    public static void removeText(int index){
    	GUIText text = allText.get(index);
        List<GUIText> textBatch = texts.get(text.getFont());
        textBatch.remove(text);
        loader.removeTextFromMemory(text.getMesh());
        if(textBatch.isEmpty()){
            texts.remove(texts.get(text.getFont()));
        }
        allText.remove(index);
    }
    
    public static void updateText(GUIText text){
    	 FontType font = text.getFont();
         TextMeshData data = font.loadText(text);
         int vao = loader.loadText(data.getVertexPositions(), data.getTextureCoords());
         text.setMeshInfo(vao, data.getVertexCount());
    }
    
    public static List<GUIText> getAllText(){
    	return allText;
    }
    
	public static void dispose(){
		FontText.render.dispose();
	}
}
