package site.root3287.sudo.serialization;

import site.root3287.sudo.serialization.container.SerializationUtils;

public class Main {
	public static void main(String[] args){
		byte[] data = new byte[128];
		int pointer = SerializationUtils.write(data, 0, 89);
		pointer = SerializationUtils.write(data, pointer, "Hello World!");
		SerializationUtils.printBytes(data);
	}
}
