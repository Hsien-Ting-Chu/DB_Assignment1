package model;

import java.nio.charset.StandardCharsets;

/**
 * 
 * @author Hsienting Chu
 *
 *	byte's order follow this matrix
 *
 *	columns						TYPE			bytes
 *	Record Length				Integer			4
 *	Follow String Length Array	Integer x 8		32
 *	REGISTER_NAME 				String			var
 *	BN_NAME						String			var
 *	BN_STATUS 					String			var
 *	BN_REG_DT 					String			var
 *	BN_CANCEL_DT 				String			var
 *	BN_RENEW_DT					String			var
 *	BN_STATE_NUM 				String			var
 *	BN_STATE_OF_REG				String			var
 *	BN_ABN						Integer			4
 */
public class Record {
	
	private String REGISTER_NAME;
	private String BN_NAME;
	private String BN_STATUS;
	private String BN_REG_DT;
	private String BN_CANCEL_DT;
	private String BN_RENEW_DT;
	private String BN_STATE_NUM;
	private String BN_STATE_OF_REG;
	private String BN_ABN;

	public int getSize() {
		return 4 + 32 + 4 +
				REGISTER_NAME.getBytes(StandardCharsets.UTF_8).length +
				BN_NAME.getBytes(StandardCharsets.UTF_8).length +
				BN_STATUS.getBytes(StandardCharsets.UTF_8).length +
				BN_REG_DT.getBytes(StandardCharsets.UTF_8).length +
				BN_CANCEL_DT.getBytes(StandardCharsets.UTF_8).length +
				BN_RENEW_DT.getBytes(StandardCharsets.UTF_8).length +
				BN_STATE_NUM.getBytes(StandardCharsets.UTF_8).length +
				BN_STATE_OF_REG.getBytes(StandardCharsets.UTF_8).length;
	}

}
