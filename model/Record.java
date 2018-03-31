package model;

import java.nio.charset.StandardCharsets;

/**
 * 
 * @author Hsienting Chu
 *
 *	byte's order follow this matrix
 *
 *	columns						TYPE			bytes
 *	Follow String Length Array	Integer * 8		32
 *	REGISTER_NAME 				String			var
 *	BN_NAME						String			var
 *	BN_STATUS 					String			var
 *	BN_REG_DT 					String			var
 *	BN_CANCEL_DT 				String			var
 *	BN_RENEW_DT					String			var
 *	BN_STATE_NUM 				String			var
 *	BN_STATE_OF_REG				String			var
 *	BN_ABN						Integer			8
 */
public class Record extends ByteProcessor {

	private String REGISTER_NAME;
	private String BN_NAME;
	private String BN_STATUS;
	private String BN_REG_DT;
	private String BN_CANCEL_DT;
	private String BN_RENEW_DT;
	private String BN_STATE_NUM;
	private String BN_STATE_OF_REG;
	private long BN_ABN;
	
	public String getREGISTER_NAME() {
		return REGISTER_NAME;
	}

	public String getBN_NAME() {
		return BN_NAME;
	}

	public String getBN_STATUS() {
		return BN_STATUS;
	}

	public String getBN_REG_DT() {
		return BN_REG_DT;
	}

	public String getBN_CANCEL_DT() {
		return BN_CANCEL_DT;
	}

	public String getBN_RENEW_DT() {
		return BN_RENEW_DT;
	}

	public String getBN_STATE_NUM() {
		return BN_STATE_NUM;
	}

	public String getBN_STATE_OF_REG() {
		return BN_STATE_OF_REG;
	}

	public long getBN_ABN() {
		return BN_ABN;
	}

	public int getLength() {
		return length;
	}

	private int length = 0;

	public Record(String[] strArr) {
		super();
		
		REGISTER_NAME = strArr[0];
		BN_NAME = strArr[1];
		BN_STATUS = strArr[2];
		BN_REG_DT = strArr[3];
		BN_CANCEL_DT = strArr[4];
		BN_RENEW_DT = strArr[5];
		BN_STATE_NUM = strArr[6];
		BN_STATE_OF_REG = strArr[7];
		BN_ABN = Long.parseLong(strArr[8]);
	}

	public Record(byte[] bytesSrc) {
		int nowIndex = 0;
		byte[] temp;
		int[] stringLenArr = new int[8];
		for (int i = 0; i < 8; i++) {
			temp = getPartBytes(bytesSrc, nowIndex, INT_BYTE_LENGTH);
			nowIndex += INT_BYTE_LENGTH;
			stringLenArr[i] = bytesToInt(temp);
		}
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[0]);
		nowIndex += stringLenArr[0];
		REGISTER_NAME = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[1]);
		nowIndex += stringLenArr[1];
		BN_NAME = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[2]);
		nowIndex += stringLenArr[2];
		BN_STATUS = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[3]);
		nowIndex += stringLenArr[3];
		BN_REG_DT = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[4]);
		nowIndex += stringLenArr[4];
		BN_CANCEL_DT = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[5]);
		nowIndex += stringLenArr[5];
		BN_RENEW_DT = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[6]);
		nowIndex += stringLenArr[6];
		BN_STATE_NUM = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, stringLenArr[7]);
		nowIndex += stringLenArr[7];
		BN_STATE_OF_REG = new String(temp, StandardCharsets.UTF_8);
		temp = getPartBytes(bytesSrc, nowIndex, LONG_BYTE_LENGTH);
		nowIndex += LONG_BYTE_LENGTH;
		BN_ABN = bytesToLong(temp);
		
	}

	public int getSize() {
		if (length == 0) {
			length = INT_BYTE_LENGTH * 8 
					+ REGISTER_NAME.getBytes(StandardCharsets.UTF_8).length
					+ BN_NAME.getBytes(StandardCharsets.UTF_8).length
					+ BN_STATUS.getBytes(StandardCharsets.UTF_8).length
					+ BN_REG_DT.getBytes(StandardCharsets.UTF_8).length
					+ BN_CANCEL_DT.getBytes(StandardCharsets.UTF_8).length
					+ BN_RENEW_DT.getBytes(StandardCharsets.UTF_8).length
					+ BN_STATE_NUM.getBytes(StandardCharsets.UTF_8).length
					+ BN_STATE_OF_REG.getBytes(StandardCharsets.UTF_8).length 
					+ LONG_BYTE_LENGTH;
		}
		return length;
	}

	public byte[] getBytes() {
		byte[] data = new byte[getSize()];
		int nowIndex = 0;
		System.arraycopy(stringLenBytes(REGISTER_NAME), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_NAME), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_STATUS), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_REG_DT), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_CANCEL_DT), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_RENEW_DT), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_STATE_NUM), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		System.arraycopy(stringLenBytes(BN_STATE_OF_REG), 0, data, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;


		System.arraycopy(stringToBytes(REGISTER_NAME), 0, data, nowIndex, stringBytesLen(REGISTER_NAME));
		nowIndex += stringBytesLen(REGISTER_NAME);
		System.arraycopy(stringToBytes(BN_NAME), 0, data, nowIndex, stringBytesLen(BN_NAME));
		nowIndex += stringBytesLen(BN_NAME);
		System.arraycopy(stringToBytes(BN_STATUS), 0, data, nowIndex, stringBytesLen(BN_STATUS));
		nowIndex += stringBytesLen(BN_STATUS);
		System.arraycopy(stringToBytes(BN_REG_DT), 0, data, nowIndex, stringBytesLen(BN_REG_DT));
		nowIndex += stringBytesLen(BN_REG_DT);
		System.arraycopy(stringToBytes(BN_CANCEL_DT), 0, data, nowIndex, stringBytesLen(BN_CANCEL_DT));
		nowIndex += stringBytesLen(BN_CANCEL_DT);
		System.arraycopy(stringToBytes(BN_RENEW_DT), 0, data, nowIndex, stringBytesLen(BN_RENEW_DT));
		nowIndex += stringBytesLen(BN_RENEW_DT);
		System.arraycopy(stringToBytes(BN_STATE_NUM), 0, data, nowIndex, stringBytesLen(BN_STATE_NUM));
		nowIndex += stringBytesLen(BN_STATE_NUM);
		System.arraycopy(stringToBytes(BN_STATE_OF_REG), 0, data, nowIndex, stringBytesLen(BN_STATE_OF_REG));
		nowIndex += stringBytesLen(BN_STATE_OF_REG);
		System.arraycopy(longToBytes(BN_ABN), 0, data, nowIndex, LONG_BYTE_LENGTH);

		return data;
	}

	public String toString(){
		return  REGISTER_NAME + ", "
				+ BN_NAME + ", "
				+ BN_STATUS + ", "
				+ BN_REG_DT + ", "
				+ BN_CANCEL_DT + ", "
				+ BN_RENEW_DT + ", "
				+ BN_STATE_NUM + ", "
				+ BN_STATE_OF_REG + ", "
				+ BN_ABN;
	}
}
