
/**
 * @author Hsienting Chu
 *
 */
public class hashquery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long startTime=System.currentTimeMillis();
			String keyword = args[0];
			int pageSize = Integer.parseInt(args[1]);
			HashFile hashFile = new HashFile(pageSize);
			hashFile.searchData(keyword);
			hashFile.close();
			long endTime=System.currentTimeMillis();
			System.out.println("Number of milliseconds is: "+ (endTime-startTime)+ "ms");
		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}
	}

}
