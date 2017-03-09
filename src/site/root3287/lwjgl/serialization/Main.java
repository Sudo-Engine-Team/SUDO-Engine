package site.root3287.lwjgl.serialization;

import java.util.Random;

import site.root3287.lwjgl.serialization.container.SerializationArray;
import site.root3287.lwjgl.serialization.container.SerializationDatabase;
import site.root3287.lwjgl.serialization.container.SerializationField;
import site.root3287.lwjgl.serialization.container.SerializationObject;
import site.root3287.lwjgl.serialization.container.SerializationString;

public class Main {

	static Random random = new Random();
	
	static void printBytes(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.printf("0x%x ", data[i]);
		}
	}
	
	public static void serializationTest() {
		int[] data = new int[50000];
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt();
		}
		
		SerializationDatabase database = new SerializationDatabase("Database");
		SerializationArray array = SerializationArray.Integer("RandomNumbers", data);
		SerializationField field = SerializationField.Integer("Integer", 8);
		SerializationField positionx = SerializationField.Short("xpos", (short)2);
		SerializationField positiony = SerializationField.Short("ypos", (short)43);
		
		SerializationObject object = new SerializationObject("Entity");
		object.addArray(array);
		//object.addArray(SerializationArray.Char("String", "Hello World!".toCharArray()));
		object.addField(field);
		object.addField(positionx);
		object.addField(positiony);
		object.addString(SerializationString.Create("Example String", "Testing our RCString class!"));
			
		database.addObject(object);
		database.addObject(new SerializationObject("root3287"));
		database.addObject(new SerializationObject("root32871"));
		SerializationObject c = new SerializationObject("root32872");
		c.addField(SerializationField.Boolean("a", false));
		database.addObject(c);
		database.addObject(new SerializationObject("root32873"));

		database.serializeToFile("res/test.dat");
	}

	public static void deserializationTest() {
		SerializationDatabase database = SerializationDatabase.DeserializeFromFile("res/test.dat");
		System.out.println("Database: " + database.getName());
		for (SerializationObject object : database.objects) {
			System.out.println("\t" + object.getName());
			for (SerializationField field : object.fields)
				System.out.println("\t\t" + field.getName());
			System.out.println();
			for (SerializationString string : object.strings)
				System.out.println("\t\t" + string.getName() + " = " + string.getString());
			System.out.println();
			for (SerializationArray array : object.arrays)
				System.out.println("\t\t" + array.getName());
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args){
		serializationTest();
		deserializationTest();
	}
}
