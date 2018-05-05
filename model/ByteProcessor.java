package model;

/**
 * 
 * @author Hsienting Chu
 */
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteProcessor {

	protected static int INT_BYTE_LENGTH = 4;
	protected static int LONG_BYTE_LENGTH = 8;

	protected byte[] stringToBytes(String src) {
		return src.getBytes(StandardCharsets.UTF_8);
	}
	
	protected int stringBytesLen(String src) {
		return src.getBytes(StandardCharsets.UTF_8).length;
	}

	protected byte[] stringLenBytes(String src) {
		return intToBytes(src.getBytes(StandardCharsets.UTF_8).length);
	}

	protected byte[] intToBytes(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	protected int bytesToInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}
	
	protected byte[] longToBytes(long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}

	protected long bytesToLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}
	
	protected byte[] getPartBytes(byte[] bytesSrc, int pos, int len) {
		byte[] data = new byte[len];
		System.arraycopy(bytesSrc, pos, data, 0, len);
		return data;
	}
}
