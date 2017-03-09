package site.root3287.lwjgl.serialization.container;

import static site.root3287.lwjgl.serialization.container.SerializationUtils.*;

public class SerializationString extends SerializationContainer {

	public static final byte CONTAINER_TYPE = ContainerType.STRING;
	public int count;
	private char[] characters;
	
	private SerializationString() {
		size += 1 + 4;
	}
	
	public String getString() {
		return new String(characters);
	}
	
	private void updateSize() {
		size += getDataSize();
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);
		pointer = writeBytes(dest, pointer, count);
		pointer = writeBytes(dest, pointer, characters);
		return pointer;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		return characters.length * SerializationType.getSize(SerializationType.CHAR);
	}
	
	public static SerializationString Create(String name, String data) {
		SerializationString string = new SerializationString();
		string.setName(name);
		string.count = data.length();
		string.characters = data.toCharArray();
		string.updateSize();
		return string;
	}
	
	public static SerializationString Deserialize(byte[] data, int pointer) {
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		SerializationString result = new SerializationString();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.count = readInt(data, pointer);
		pointer += 4;
		
		result.characters = new char[result.count];
		readChars(data, pointer, result.characters);
		
		pointer += result.count * SerializationType.getSize(SerializationType.CHAR);
		return result;
}

}
