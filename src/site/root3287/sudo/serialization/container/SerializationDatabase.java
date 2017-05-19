package site.root3287.sudo.serialization.container;

import static site.root3287.sudo.serialization.container.SerializationUtils.readByte;
import static site.root3287.sudo.serialization.container.SerializationUtils.readInt;
import static site.root3287.sudo.serialization.container.SerializationUtils.readShort;
import static site.root3287.sudo.serialization.container.SerializationUtils.readString;
import static site.root3287.sudo.serialization.container.SerializationUtils.write;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializationDatabase extends SerializationBase{
	public static final byte[] HEADER = "SUDO".getBytes();
	public static final short VERSION = 0x0100;
	public static final byte CONTAINER_TYPE = SerializationContainerType.DATABASE;
	private short objectCount;
	public List<SerializationObject> objects = new ArrayList<>();
	
	private SerializationDatabase() {
	}
	
	public SerializationDatabase(String name) {
		setName(name);
		
		size += HEADER.length + 2 + 1 + 2;
	}
	
	public void addObject(SerializationObject object) {
		objects.add(object);
		size += object.getSize();
		
		objectCount = (short)objects.size();
	}
	
	public int getSize() {
		return size;
	}

	public int getBytes(byte[] dest, int pointer) {
		pointer = write(dest, pointer, HEADER);
		pointer = write(dest, pointer, VERSION);
		pointer = write(dest, pointer, CONTAINER_TYPE);
		pointer = write(dest, pointer, nameLength);
		pointer = write(dest, pointer, name);
		pointer = write(dest, pointer, size);
		
		pointer = write(dest, pointer, objectCount);
		for (SerializationObject object : objects)
			pointer = object.getBytes(dest, pointer);
		
		return pointer;
	}
	
	public static SerializationDatabase deserialize(byte[] data) {
		int pointer = 0;
		assert(readString(data, pointer, HEADER.length).equals(HEADER));
		pointer += HEADER.length;
		
		if (readShort(data, pointer) != VERSION) {
			System.err.println("Invalid version!");
			return null;
		}
		pointer += 2;
		
		byte containerType = readByte(data, pointer++);
		assert(containerType == CONTAINER_TYPE);
		
		SerializationDatabase result = new SerializationDatabase();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		result.objectCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.objectCount; i++) {
			SerializationObject object = SerializationObject.deserialize(data, pointer);
			result.objects.add(object);
			pointer += object.getSize(); 
		}
		
		return result;
	}
	
	public SerializationObject findObject(String name) {
		for (SerializationObject object : objects) {
			if (object.getName().equals(name))
				return object;
		}
		return null;
	}

	public static SerializationDatabase deserializeFile(String path) {
		byte[] buffer = null;
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			buffer = new byte[stream.available()];
			stream.read(buffer);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return deserialize(buffer);
	}
	
	public void serializeFile(String path) {
		byte[] data = new byte[getSize()];
		getBytes(data, 0);
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
