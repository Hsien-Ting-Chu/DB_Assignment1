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
			long startTime=System.currentTimeMillis();
			int pageSize = Integer.parseInt(args[0]);
			HashFile hashFile = new HashFile(pageSize);
			hashFile.importData(pageSize);
			hashFile.close();
			long endTime=System.currentTimeMillis();
			System.out.println("Number of milliseconds is: "+ (endTime-startTime)+ "ms");
			
		} catch (Exception e) {
			System.err.println("Parameter is wrong");
			e.printStackTrace();
		}

	}

}
