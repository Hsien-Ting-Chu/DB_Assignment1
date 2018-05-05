/**
 * @author Hsienting Chu
 *
 */

public class dbload {


	public static void main(String[] args) {
		try {
			int pageSize = Integer.parseInt(args[1]);
			String filePath = args[2];
			HeapFile heapFile = new HeapFile(pageSize);
			heapFile.importData(filePath);
			heapFile.close();
		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}
		
	}
}