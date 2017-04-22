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
	 * Convert char[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, char[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert short[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, short[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert int[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, int[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert long[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, long[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert float[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, float[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert double[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, double[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
	}
	
	/**
	 * Convert boolean[] to bytes, then push it to bytes
	 * @param dest the list that we want to append
	 * @param pointer the last place we left off.
	 * @param value the item we want to append
	 * @return the pointer
	 */
	public static int write(byte[] dest, int pointer, boolean[] src) {
		assert(dest.length > pointer + src.length);
		for (int i = 0; i < src.length; i++)
			pointer = write(dest, pointer, src[i]);
		return pointer;
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
	
	/*public static void readBytes(byte[] src, int pointer, byte[] dest) {
		for (int i = 0; i < dest.length; i++)
			dest[i] = src[pointer + i];
	}*/
	
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
	 * Read Char[]
	 * @param src the data
	 * @param pointer where to start
	 * @param dest
	 */
	public static void readChars(byte[] src, int pointer, char[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readChar(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.CHAR);
		}
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
	
	public static void readBytes(byte[] src, int pointer, byte[] dest) {
		for (int i = 0; i < dest.length; i++)
			dest[i] = src[pointer + i];
	}
	
	public static void readShorts(byte[] src, int pointer, short[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readShort(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.SHORT);
		}
	}
	/**
	 * Read data and put it in a int[]
	 * @param src input
	 * @param pointer starting
	 * @param dest where to put the output.
	 */
	public static void readInts(byte[] src, int pointer, int[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readInt(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.INTEGER);
		}
	}
	/**
	 * Read longs and put it in an array list.
	 * @param src input
	 * @param pointer starting
	 * @param dest output array
	 */
	public static void readLongs(byte[] src, int pointer, long[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readLong(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.LONG);
		}
	}
	
	/**
	 * Read floats and put it in a float array
	 * @param src input
	 * @param pointer where to start
	 * @param dest
	 */
	public static void readFloats(byte[] src, int pointer, float[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readFloat(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.FLOAT);
		}
	}
	
	/**
	 * Read doubles from the data.
	 * @param src input
	 * @param pointer starting
	 * @param dest where to put it.
	 */
	public static void readDoubles(byte[] src, int pointer, double[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readDouble(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.DOUBLE);
		}
	}
	/**
	 * Read booleans from data
	 * @param src input
	 * @param pointer where to start
	 * @param dest where to put the ending.
	 */
	public static void readBooleans(byte[] src, int pointer, boolean[] dest) {
		for (int i = 0; i < dest.length; i++) {
			dest[i] = readBoolean(src, pointer);
			pointer += SerializationFieldType.getSize(SerializationFieldType.BOOLEAN);
		}
	}
	
	/**
	 * Print out the data that we made
	 * @param data
	 */
	public static void printBytes(byte[] data){
		for(int i =0; i<data.length; i++){
			System.out.printf("0x%x ", data[i]);
		}
	}
}
