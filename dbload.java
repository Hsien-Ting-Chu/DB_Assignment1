/**
 * @author Hsienting Chu
 *
 */

public class dbload {


	public static void main(String[] args) {
		try {
			//int pageSize = Integer.parseInt(args[1]);
			//String filePath = args[2];
//			HeapFile heapFile = new HeapFile(pageSize);
			HeapFile heapFile = new HeapFile(2048);
//			heapFile.importData(filePath);
			heapFile.importData("/Users/yin/Documents/workspace/DB_Assignment1/src_origin.csv");
			heapFile.close();
		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}
		
	}
}