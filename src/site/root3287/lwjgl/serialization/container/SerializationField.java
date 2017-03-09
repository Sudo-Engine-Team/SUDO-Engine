package site.root3287.lwjgl.serialization.container;

import static site.root3287.lwjgl.serialization.container.SerializationUtils.*;

public class SerializationField extends SerializationContainer {
	public static final byte CONTAINER_TYPE = ContainerType.FIELD;
	public byte type;
	public byte[] data;
	
	private SerializationField() {
	}
	
	public byte getByte() {
		return data[0];
	}
	
	public short getShort() {
		return readShort(data, 0);
	}
	
	public char getChar() {
		return readChar(data, 0);
	}
	
	public int getInt() {
		return readInt(data, 0);
	}
	
	public long getLong() {
		return readLong(data, 0);
	}
	
	public double getDouble() {
		return readDouble(data, 0);
	}
	
	public float getFloat() {
		return readFloat(data, 0);
	}
	
	public boolean getBoolean() {
		return readBoolean(data, 0);
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, type);
		pointer = writeBytes(dest, pointer, data);
		return pointer;
	}
	
	public int getSize() {
		assert(data.length == SerializationType.getSize(type));
		return 1 + 2 + name.length + 1 + data.length;
	}

	public static SerializationField Byte(String name, byte value) {
		SerializationField field = new SerializationField();
		field.type = SerializationType.BYTE;
		field.data = new byte[SerializationType.getSize(SerializationType.BYTE)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Short(String name, short value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.SHORT;
		field.data = new byte[SerializationType.getSize(SerializationType.SHORT)];
		writeBytes(field.data, 0, value);
		return field;
	}

	public static SerializationField Char(String name, char value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.CHAR;
		field.data = new byte[SerializationType.getSize(SerializationType.CHAR)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Integer(String name, int value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.INTEGER;
		field.data = new byte[SerializationType.getSize(SerializationType.INTEGER)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Long(String name, long value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.LONG;
		field.data = new byte[SerializationType.getSize(SerializationType.LONG)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Float(String name, float value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.FLOAT;
		field.data = new byte[SerializationType.getSize(SerializationType.FLOAT)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Double(String name, double value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.DOUBLE;
		field.data = new byte[SerializationType.getSize(SerializationType.DOUBLE)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Boolean(String name, boolean value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationType.BOOLEAN;
		field.data = new byte[SerializationType.getSize(SerializationType.BOOLEAN)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static SerializationField Deserialize(byte[] data, int pointer) {
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		SerializationField result = new SerializationField();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.type = data[pointer++];
		
		result.data = new byte[SerializationType.getSize(result.type)];
		readBytes(data, pointer, result.data);
		pointer += SerializationType.getSize(result.type);
		return result;
	}
}
