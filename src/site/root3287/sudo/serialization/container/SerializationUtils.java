package site.root3287.sudo.serialization.container;

public class SerializationUtils {
	/**
	 * A 8-bit identifier
	 */
	public static byte[] HEADER = "--------".getBytes(); //8-bit id
	
	/**
	 * Current version
	 */
	public static short VERSION = 0x0200;
	
	/**
	 * Anything special that we need to know
	 */
	public static byte flags = 0x000;
	
	/**
	 * Add a byte to the already made list
	 * @param dest the list that we want to add the bytes in
	 * @param pointer the place we want to write from
	 * @param value the thing that we need to write in.
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, byte value){
		dest[pointer++] = value;
		return pointer;
	}
	
	/**
	 * Add a byte array to the already made list
	 * @param dest the list that we want to add the bytes in
	 * @param pointer the place we want to write from
	 * @param value the thing that we need to write in.
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, byte[] value){
		for(int i = 0; i< value.length; i++){
			pointer = write(dest, pointer, value[i]);
		}
		return pointer;
	}
	
	/**
	 * Convert short to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, short value){
		dest[pointer++] = (byte) ((value>>8) & 0xff);
		dest[pointer++] = (byte) ((value>>0) & 0xff);
		return pointer;
	}
	
	/**
	 * Convert char to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, char value){
		dest[pointer++] = (byte) ((value>>8) & 0xff);
		dest[pointer++] = (byte) ((value>>0) & 0xff);
		return pointer;
	}
	
	/**
	 * Convert int to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, int value){
		dest[pointer++] = (byte) ((value>>24) & 0xff);
		dest[pointer++] = (byte) ((value>>16) & 0xff);
		dest[pointer++] = (byte) ((value>>8) & 0xff);
		dest[pointer++] = (byte) ((value>>0) & 0xff);
		return pointer;
	}
	
	/**
	 * Convert long to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, long value){
		dest[pointer++] = (byte) ((value>>56) & 0xff);
		dest[pointer++] = (byte) ((value>>48) & 0xff);
		dest[pointer++] = (byte) ((value>>40) & 0xff);
		dest[pointer++] = (byte) ((value>>32) & 0xff);
		dest[pointer++] = (byte) ((value>>24) & 0xff);
		dest[pointer++] = (byte) ((value>>16) & 0xff);
		dest[pointer++] = (byte) ((value>>8) & 0xff);
		dest[pointer++] = (byte) ((value>>0) & 0xff);
		return pointer;
	}
	
	/**
	 * Convert float to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, float value){
		return write(dest, pointer, Float.floatToIntBits(value));
	}
	
	/**
	 * Convert double to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, double value){
		return write(dest, pointer, Double.doubleToLongBits(value));
	}
	
	/**
	 * Convert boolean to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, boolean value){
		return write(dest, pointer, (byte)((value)?1:0));
	}
	
	/**
	 * Convert String to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, String value){
		pointer = write(dest, pointer, (short)value.length());
		return write(dest, pointer, value.getBytes());
	}
	
	/**
	 * Read bytes
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static byte readByte(byte[] src, int pointer){
		return src[pointer];
	}
	
	/**
	 * Read char
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static char readChar(byte[] src, int pointer){
		return (char) (src[pointer] << 8 | src[pointer+1]);
	}
	
	/**
	 * Read short
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static short readShort(byte[] src, int pointer){
		return (short) (src[pointer] << 8 | src[pointer+1]);
	}
	
	/**
	 * Read int
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static int readInt(byte[] src, int pointer){
		return src[pointer] << 24 | src[pointer+1] << 16 | src[pointer+2] << 8 | src[pointer+3]; 
	}
	
	/**
	 * Read long
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static long readLong(byte[] src, int pointer){
		return src[pointer] << 56 | src[pointer+1] << 48 | src[pointer+2] << 40 | src[pointer+3] >> 32 | src[pointer+4] >> 24 | src[pointer+5] >> 16 | src[pointer+6] >> 8 | src[pointer+7]; 
	}
	
	/**
	 * Read float
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static float readFloat(byte[] src, int pointer){
		return Float.intBitsToFloat(readInt(src, pointer));
	}
	
	/**
	 * Read double
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static double readDouble(byte[] src, int pointer){
		return Double.longBitsToDouble(readLong(src, pointer));
	}
	
	/**
	 * Read boolean
	 * @param src the data
	 * @param pointer where to start
	 * @return data
	 */
	public static boolean readBoolean(byte[] src, int pointer){
		return (readByte(src, pointer) == 0)? false: true;
	}
	
	public static String readString(byte[] src, int pointer, int length){
		return new String(src, pointer, length);
	}
	
	public static void printBytes(byte[] data){
		for(int i =0; i<data.length; i++){
			System.out.printf("0x%x ", data[i]);
		}
	}
}
