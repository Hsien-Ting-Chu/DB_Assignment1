/**
 * @author Hsienting Chu
 *
 */
public class dbquery {

	public static void main(String[] args) {
		try {
//			int pageSize = Integer.parseInt(args[1]);
//			String keyword = args[2];
//			HeapFile heapFile = new HeapFile(pageSize);
			HeapFile heapFile = new HeapFile(2048);
//			heapFile.searchData(keyword);
			heapFile.searchData("big");
			heapFile.close();
		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}
	}
}
