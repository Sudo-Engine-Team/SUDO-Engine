package site.root3287.sudo.serialization.container;

import static site.root3287.sudo.serialization.container.SerializationUtils.*;

import static site.root3287.sudo.serialization.container.SerializationFieldType.*;

public class SerializationString extends SerializationBase{
	public static final byte CONTAINER_TYPE = SerializationContainerType.STRING;
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
	
	/**
	 * Get the byte length of the input.
	 * @param dest input
	 * @param pointer starting point.
	 * @return
	 */
	public int getBytes(byte[] dest, int pointer) {
		pointer = write(dest, pointer, CONTAINER_TYPE);
		pointer = write(dest, pointer, nameLength);
		pointer = write(dest, pointer, name);
		pointer = write(dest, pointer, size);
		pointer = write(dest, pointer, count);
		pointer = write(dest, pointer, characters);
		return pointer;
	}

	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		return characters.length * SerializationFieldType.getSize(CHAR);
	}
	
	/**
	 * Create string to serialize
	 * @param name key
	 * @param data data
	 * @return
	 */
	public static SerializationString create(String name, String data) {
		SerializationString string = new SerializationString();
		string.setName(name);
		string.count = data.length();
		string.characters = data.toCharArray();
		string.updateSize();
		return string;
	}
	
	/**
	 * deserialize data
	 * @param data input
	 * @param pointer starting.
	 * @return
	 */
	public static SerializationString deserialize(byte[] data, int pointer) {
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
		
		pointer += result.count * SerializationFieldType.getSize(SerializationFieldType.CHAR);
		return result;
	}
	
	@Override
	public String toString() {
		return this.getName() + " = " + getString();
	}
}
