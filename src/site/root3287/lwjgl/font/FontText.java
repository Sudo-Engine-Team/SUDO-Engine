package site.root3287.lwjgl.font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.text.Text;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.font.render.FontRender;

public class FontText {
	private static Loader loader;
	private static Map<FontType, List<GUIText>> texts = new HashMap<>();
	private static FontRender render;
	
	public static void init(Loader loader){
		FontText.loader = loader;
		FontText.render = new FontRender();
		
	}
	public static void loadText(GUIText text){
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null){
			textBatch = new ArrayList<>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public static void removeText(GUIText text){
		List<GUIText> textbatch = texts.get(text.getFont());
		textbatch.remove(text);
		if(textbatch.isEmpty()){
			texts.remove(text.getFont());
		}
	}
	public static void dispose(){
		FontText.render.dispose();
	}
	public static void render(){
		render.render(texts);
	}
}
