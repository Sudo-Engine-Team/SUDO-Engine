package site.root3287.sudo.serialization;

import site.root3287.sudo.serialization.container.SerializationArray;
import site.root3287.sudo.serialization.container.SerializationDatabase;
import site.root3287.sudo.serialization.container.SerializationField;
import site.root3287.sudo.serialization.container.SerializationObject;
import site.root3287.sudo.serialization.container.SerializationString;

public class Main {
	public static void main(String[] args){
		SerializationDatabase database = new SerializationDatabase("testDatabase");
		
		SerializationObject player = new SerializationObject("Player");
		player.addField(SerializationField.createFloatField("x", 1f));
		player.addField(SerializationField.createFloatField("y", 0));
		player.addField(SerializationField.createFloatField("z", 1f));
		
		SerializationObject terrain1 = new SerializationObject("terrain-1");
		terrain1.addString(SerializationString.create("test", "asdf"));
		
		SerializationObject obj = new SerializationObject();
		obj.addField(SerializationField.createFloatField("phi", (float) 1.618033989749894848208));
		
		database.addObject(player);
		database.addObject(terrain1);
		database.addObject(obj);
		
		database.serializeFile("res/testDatabase.dat");
		
		SerializationDatabase d2 = SerializationDatabase.deserializeFile("res/testDatabase.dat");
		System.out.println(d2.objects);
		for(SerializationObject o : d2.objects){
			System.out.println(o);
			System.out.println("\tString: ");
			for(SerializationString stringArray : o.strings){
				System.out.println("\t\t"+stringArray);
			}
			System.out.println("\tFields: ");
			for(SerializationField f: o.fields){
				System.out.println("\t\t"+f);
			}
			System.out.println("\tArrays:");
			for(SerializationArray a : o.arrays){
				System.out.println("\t\t"+a);
			}
		}
	}
}
