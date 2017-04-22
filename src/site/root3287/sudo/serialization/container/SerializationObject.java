package site.root3287.sudo.serialization.container;

import static site.root3287.sudo.serialization.container.SerializationUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import site.root3287.sudo.serialization.container.SerializationArray;


public class SerializationObject extends SerializationBase{
	public static final byte CONTAINER_TYPE = SerializationContainerType.OBJECT;
	private short fieldCount;
	public List<SerializationField> fields = new ArrayList<SerializationField>();
	private short stringCount;
	public List<SerializationString> strings = new ArrayList<SerializationString>();
	private short arrayCount;
	public List<SerializationArray> arrays = new ArrayList<SerializationArray>();
	
	public SerializationObject() {
		size += 1+2+2+2;
		setName(UUID.randomUUID().toString());
	}
	
	public SerializationObject(String name) {
		size += 1 + 2 + 2 + 2;
		setName(name);
	}
	
	public void addField(SerializationField field) {
		fields.add(field);
		size += field.getSize();
		
		fieldCount = (short)fields.size();
	}
	
	public void addString(SerializationString string) {
		strings.add(string);
		size += string.getSize();
		
		stringCount = (short)strings.size();
	}

	public void addArray(SerializationArray array) {
		arrays.add(array);
		size += array.getSize();
		
		arrayCount = (short)arrays.size();
	}
	
	public int getSize() {
		return size;
	}
	
	public SerializationField findField(String name) {
		for (SerializationField field : fields) {
			if (field.getName().equals(name))
				return field;
		}
		return null;
	}
	
	public SerializationString findString(String name) {
		for (SerializationString string : strings) {
			if (string.getName().equals(name))
				return string;
		}
		return null;
	}

	public SerializationArray findArray(String name) {
		for (SerializationArray array : arrays) {
			if (array.getName().equals(name))
				return array;
		}
		return null;
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer = write(dest, pointer, CONTAINER_TYPE);
		pointer = write(dest, pointer, nameLength);
		pointer = write(dest, pointer, name);
		pointer = write(dest, pointer, size);
		
		pointer = write(dest, pointer, fieldCount);
		for (SerializationField field : fields)
			pointer = field.getBytes(dest, pointer);
		
		pointer = write(dest, pointer, stringCount);
		for (SerializationString string : strings)
			pointer = string.getBytes(dest, pointer);
		
		pointer = write(dest, pointer, arrayCount);
		for (SerializationArray array : arrays)
			pointer = array.getBytes(dest, pointer);
		
		return pointer;
	}
	
	public static SerializationObject deserialize(byte[] data, int pointer) {
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		SerializationObject result = new SerializationObject();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		// Early-out: pointer += result.size - sizeOffset - result.nameLength;
		
		result.fieldCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.fieldCount; i++) {
			SerializationField field = SerializationField.deserialize(data, pointer);
			result.fields.add(field);
			pointer += field.getSize();
		}
		
		result.stringCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.stringCount; i++) {
			SerializationString string = SerializationString.deserialize(data, pointer);
			result.strings.add(string);
			pointer += string.getSize();
		}

		result.arrayCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.arrayCount; i++) {
			SerializationArray array = SerializationArray.deserialize(data, pointer);
			result.arrays.add(array);
			pointer += array.getSize();
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
