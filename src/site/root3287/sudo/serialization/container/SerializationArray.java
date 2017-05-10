package site.root3287.sudo.serialization.container;

import static site.root3287.sudo.serialization.container.SerializationUtils.*;

public class SerializationArray extends SerializationBase{
	public static final byte CONTAINER_TYPE = SerializationContainerType.ARRAY;
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
		pointer = write(dest, pointer, CONTAINER_TYPE);
		pointer = write(dest, pointer, nameLength);
		pointer = write(dest, pointer, name);
		pointer = write(dest, pointer, size);
		pointer = write(dest, pointer, type);
		pointer = write(dest, pointer, count);
		
		switch(type) {
		case SerializationFieldType.BYTE:
			pointer = write(dest, pointer, data);
			break;
		case SerializationFieldType.SHORT:
			pointer = write(dest, pointer, shortData);
			break;
		case SerializationFieldType.CHAR:
			pointer = write(dest, pointer, charData);
			break;
		case SerializationFieldType.INTEGER:
			pointer = write(dest, pointer, intData);
			break;
		case SerializationFieldType.LONG:
			pointer = write(dest, pointer, longData);
			break;
		case SerializationFieldType.FLOAT:
			pointer = write(dest, pointer, floatData);
			break;
		case SerializationFieldType.DOUBLE:
			pointer = write(dest, pointer, doubleData);
			break;
		case SerializationFieldType.BOOLEAN:
			pointer = write(dest, pointer, booleanData);
			break;
		}
		return pointer;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		switch(type) {
		case SerializationFieldType.BYTE:		return data.length * SerializationFieldType.getSize(SerializationFieldType.BYTE);
		case SerializationFieldType.SHORT:	return shortData.length * SerializationFieldType.getSize(SerializationFieldType.SHORT);
		case SerializationFieldType.CHAR:		return charData.length * SerializationFieldType.getSize(SerializationFieldType.CHAR);
		case SerializationFieldType.INTEGER:	return intData.length * SerializationFieldType.getSize(SerializationFieldType.INTEGER);
		case SerializationFieldType.LONG:		return longData.length * SerializationFieldType.getSize(SerializationFieldType.LONG);
		case SerializationFieldType.FLOAT:	return floatData.length * SerializationFieldType.getSize(SerializationFieldType.FLOAT);
		case SerializationFieldType.DOUBLE:	return doubleData.length * SerializationFieldType.getSize(SerializationFieldType.DOUBLE);
		case SerializationFieldType.BOOLEAN:	return booleanData.length * SerializationFieldType.getSize(SerializationFieldType.BOOLEAN);
		}
		return 0;
	}

	public static SerializationArray createByteArray(String name, byte[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.BYTE;
		array.count = data.length;
		array.data = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createShortArray(String name, short[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.SHORT;
		array.count = data.length;
		array.shortData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createCharArray(String name, char[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.CHAR;
		array.count = data.length;
		array.charData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createIntegerArray(String name, int[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.INTEGER;
		array.count = data.length;
		array.intData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createLongArray(String name, long[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.LONG;
		array.count = data.length;
		array.longData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createFloatArray(String name, float[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.FLOAT;
		array.count = data.length;
		array.floatData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createDoubleArray(String name, double[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.DOUBLE;
		array.count = data.length;
		array.doubleData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray createBooleanArray(String name, boolean[] data) {
		SerializationArray array = new SerializationArray();
		array.setName(name);
		array.type = SerializationFieldType.BOOLEAN;
		array.count = data.length;
		array.booleanData = data;
		array.updateSize();
		return array;
	}
	
	public static SerializationArray deserialize(byte[] data, int pointer) {
		byte SerializationContainerSerializationFieldType = data[pointer++];
		assert(SerializationContainerSerializationFieldType == CONTAINER_TYPE);
		
		SerializationArray result = new SerializationArray();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.type = data[pointer++];
		
		result.count = readInt(data, pointer);
		pointer += 4;
		
		switch(result.type) {
		case SerializationFieldType.BYTE:
			result.data = new byte[result.count];
			readBytes(data, pointer, result.data);
			break;
		case SerializationFieldType.SHORT:
			result.shortData = new short[result.count];
			readShorts(data, pointer, result.shortData);
			break;
		case SerializationFieldType.CHAR:
			result.charData = new char[result.count];
			readChars(data, pointer, result.charData);
			break;
		case SerializationFieldType.INTEGER:
			result.intData = new int[result.count];
			readInts(data, pointer, result.intData);
			break;
		case SerializationFieldType.LONG:
			result.longData = new long[result.count];
			readLongs(data, pointer, result.longData);
			break;
		case SerializationFieldType.FLOAT:
			result.floatData = new float[result.count];
			readFloats(data, pointer, result.floatData);
			break;
		case SerializationFieldType.DOUBLE:
			result.doubleData = new double[result.count];
			readDoubles(data, pointer, result.doubleData);
			break;
		case SerializationFieldType.BOOLEAN:
			result.booleanData = new boolean[result.count];
			readBooleans(data, pointer, result.booleanData);
			break;
		}
		
		pointer += result.count * SerializationFieldType.getSize(result.type);
		
		return result;
	}
	
	@Override
	public String toString() {
		return  getName();
	}

	public byte[] getData() {
		return data;
	}

	public short[] getShortData() {
		return shortData;
	}
	
	public char[] getCharData() {
		return charData;
	}

	public int[] getIntData() {
		return intData;
	}

	public long[] getLongData() {
		return longData;
	}

	public float[] getFloatData() {
		return floatData;
	}

	public double[] getDoubleData() {
		return doubleData;
	}

	public boolean[] getBooleanData() {
		return booleanData;
	}
}
