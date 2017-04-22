package site.root3287.sudo.serialization.container;

import static site.root3287.sudo.serialization.container.SerializationUtils.readBoolean;
import static site.root3287.sudo.serialization.container.SerializationUtils.readBytes;
import static site.root3287.sudo.serialization.container.SerializationUtils.readChar;
import static site.root3287.sudo.serialization.container.SerializationUtils.readDouble;
import static site.root3287.sudo.serialization.container.SerializationUtils.readFloat;
import static site.root3287.sudo.serialization.container.SerializationUtils.readInt;
import static site.root3287.sudo.serialization.container.SerializationUtils.readLong;
import static site.root3287.sudo.serialization.container.SerializationUtils.readShort;
import static site.root3287.sudo.serialization.container.SerializationUtils.readString;
import static site.root3287.sudo.serialization.container.SerializationUtils.write;

import static site.root3287.sudo.serialization.container.SerializationFieldType.*;

public class SerializationField extends SerializationBase{
	public static final byte CONTAINER_TYPE = SerializationContainerType.FIELD;
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
		pointer = write(dest, pointer, CONTAINER_TYPE);
		pointer = write(dest, pointer, nameLength);
		pointer = write(dest, pointer, name);
		pointer = write(dest, pointer, type);
		pointer = write(dest, pointer, data);
		return pointer;
	}
	
	public int getSize() {
		assert(data.length == SerializationFieldType.getSize(type));
		return 1 + 2 + name.length + 1 + data.length;
	}

	public static SerializationField createByteField(String name, byte value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.BYTE;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.BYTE)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createShortField(String name, short value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.SHORT;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.SHORT)];
		write(field.data, 0, value);
		return field;
	}

	public static SerializationField createCharField(String name, char value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.CHAR;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.CHAR)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createIntegerField(String name, int value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.INTEGER;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.INTEGER)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createLongField(String name, long value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = LONG;
		field.data = new byte[SerializationFieldType.getSize(LONG)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createFloatField(String name, float value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.FLOAT;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.FLOAT)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createDoubleField(String name, double value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = SerializationFieldType.DOUBLE;
		field.data = new byte[SerializationFieldType.getSize(SerializationFieldType.DOUBLE)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField createBooleanField(String name, boolean value) {
		SerializationField field = new SerializationField();
		field.setName(name);
		field.type = BOOLEAN;
		field.data = new byte[SerializationFieldType.getSize(BOOLEAN)];
		write(field.data, 0, value);
		return field;
	}
	
	public static SerializationField deserialize(byte[] data, int pointer) {
		byte containerSerializationFieldType = data[pointer++];
		assert(containerSerializationFieldType == CONTAINER_TYPE);
		
		SerializationField result = new SerializationField();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.type = data[pointer++];
		
		result.data = new byte[SerializationFieldType.getSize(result.type)];
		readBytes(data, pointer, result.data);
		pointer += SerializationFieldType.getSize(result.type);
		return result;
	}
	
	@Override
	public String toString() {
		String name = new String(this.name);
		String value = "";
		switch(this.type){
			case 0:
				value = null;
				break;
			case 1:
				value = new String(Short.toString(getShort()));
				break;
			case 2:
				value = new String(Short.toString(getShort()));
				break;
			case 3:
				value = new String(Character.toString(getChar()));
				break;
			case 4:
				value = new String(Integer.toString(getInt()));
				break;
			case 5:
				value =new String(Long.toString(getLong()));
				break;
			case 6:
				value = new String(Float.toString(getFloat()));
				break;
			case 7:
				value = new String(Double.toString(getDouble()));
				break;
			case 8:
				value = new String(Boolean.toString(getBoolean()));
				break;
		}
		
		return name+" = "+value;
	}
}
