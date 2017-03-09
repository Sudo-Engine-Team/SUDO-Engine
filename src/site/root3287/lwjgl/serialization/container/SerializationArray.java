package site.root3287.lwjgl.serialization.container;

import static site.root3287.lwjgl.serialization.container.SerializationUtils.*;

public class SerializationArray extends SerializationContainer {
	public static byte CONTAINER_TYPE = ContainerType.ARRAY;
	public byte type;
	public int count;
	public byte[] data;
	
	private short[] shortData;
	private char[] charData;
	private int[] intData;
	private long[] longData;
	private float[] floatData;
	private double[] doubleData;
private boolean[] booleanData;
	
	private SerializationArray() {
		size += 1 + 1 + 4;
	}
	
	private void updateSize() {
		size += getDataSize();
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, count);
		
		switch(CONTAINER_TYPE) {
		case SerializationType.BYTE:
			pointer = writeBytes(dest, pointer, data);
			break;
		case SerializationType.SHORT:
			pointer = writeBytes(dest, pointer, shortData);
			break;
		case SerializationType.CHAR:
			pointer = writeBytes(dest, pointer, charData);
			break;
		case SerializationType.INTEGER:
			pointer = writeBytes(dest, pointer, intData);
			break;
		case SerializationType.LONG:
			pointer = writeBytes(dest, pointer, longData);
			break;
		case SerializationType.FLOAT:
			pointer = writeBytes(dest, pointer, floatData);
			break;
		case SerializationType.DOUBLE:
			pointer = writeBytes(dest, pointer, doubleData);
			break;
		case SerializationType.BOOLEAN:
			pointer = writeBytes(dest, pointer, booleanData);
			break;
		}
		return pointer;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		switch(CONTAINER_TYPE) {
		case SerializationType.BYTE:		return data.length * SerializationType.getSize(SerializationType.BYTE);
		case SerializationType.SHORT:	return shortData.length * SerializationType.getSize(SerializationType.SHORT);
		case SerializationType.CHAR:		return charData.length * SerializationType.getSize(SerializationType.CHAR);
		case SerializationType.INTEGER:	return intData.length * SerializationType.getSize(SerializationType.INTEGER);
		case SerializationType.LONG:		return longData.length * SerializationType.getSize(SerializationType.LONG);
		case SerializationType.FLOAT:	return floatData.length * SerializationType.getSize(SerializationType.FLOAT);
		case SerializationType.DOUBLE:	return doubleData.length * SerializationType.getSize(SerializationType.DOUBLE);
		case SerializationType.BOOLEAN:	return booleanData.length * SerializationType.getSize(SerializationType.BOOLEAN);
		}
		return 0;
	}

	public static SerializationArray Byte(String name, byte[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.BYTE;
		array.count = data.length;
		array.data = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Short(String name, short[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.SHORT;
		array.count = data.length;
		array.shortData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Char(String name, char[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.CHAR;
		array.count = data.length;
		array.charData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Integer(String name, int[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.INTEGER;
		array.count = data.length;
		array.intData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Long(String name, long[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.LONG;
		array.count = data.length;
		array.longData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Float(String name, float[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.FLOAT;
		array.count = data.length;
		array.floatData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Double(String name, double[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.DOUBLE;
		array.count = data.length;
		array.doubleData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Boolean(String name, boolean[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.CONTAINER_TYPE = SerializationType.BOOLEAN;
		array.count = data.length;
		array.booleanData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray Deserialize(byte[] data, int pointer) {
		byte containerSerializationType = data[pointer++];
		assert(containerSerializationType == CONTAINER_TYPE);
		
		SerializationArray result = new SerializationArray();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.CONTAINER_TYPE = data[pointer++];
		
		result.count = readInt(data, pointer);
		pointer += 4;
		
		switch(result.CONTAINER_TYPE) {
		case SerializationType.BYTE:
			result.data = new byte[result.count];
			readBytes(data, pointer, result.data);
			break;
		case SerializationType.SHORT:
			result.shortData = new short[result.count];
			readShorts(data, pointer, result.shortData);
			break;
		case SerializationType.CHAR:
			result.charData = new char[result.count];
			readChars(data, pointer, result.charData);
			break;
		case SerializationType.INTEGER:
			result.intData = new int[result.count];
			readInts(data, pointer, result.intData);
			break;
		case SerializationType.LONG:
			result.longData = new long[result.count];
			readLongs(data, pointer, result.longData);
			break;
		case SerializationType.FLOAT:
			result.floatData = new float[result.count];
			readFloats(data, pointer, result.floatData);
			break;
		case SerializationType.DOUBLE:
			result.doubleData = new double[result.count];
			readDoubles(data, pointer, result.doubleData);
			break;
		case SerializationType.BOOLEAN:
			result.booleanData = new boolean[result.count];
			readBooleans(data, pointer, result.booleanData);
			break;
		}
		
		pointer += result.count * SerializationType.getSize(result.CONTAINER_TYPE);
		
		return result;
	}
}
