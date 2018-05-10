import java.io.File;
import java.util.ArrayList;

import model.Record;
import model.Page;

/**
 * @author Hsienting Chu
 *
 */
public class hashload {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// To validate the parameter
		try {
			//int pageSize = Integer.parseInt(args[0]);
			int pageSize = 4096;
			HashFile hashFile = new HashFile();
			hashFile.importData(pageSize);

		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}

	}

}
