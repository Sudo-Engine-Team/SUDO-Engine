package site.root3287.lwjgl.input.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.FontRenderer;
import site.root3287.lwjgl.fontMeshCreator.FontType;
import site.root3287.lwjgl.fontMeshCreator.GUIText;
import site.root3287.lwjgl.fontMeshCreator.TextMeshData;

public class UIText {
	private static Loader loader;
	public static HashMap<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer render;
	
	public static void init(Loader l){
		render = new FontRenderer();
		loader = l;
	}
	public static void loadText(GUIText text){
		FontType font = text.getFont();
		TextMeshData data= font.loadText(text);
		int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null){
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	public static void removeText(GUIText text){
		List<GUIText> batch = texts.get(text.getFont());
		batch.remove(text);
		if(batch.isEmpty()){
			texts.remove(text.getFont());
		}
	}
	public static void render(){
		render.render(texts);
	}
	public static void dispose(){
		render.dispose();
	}
}
