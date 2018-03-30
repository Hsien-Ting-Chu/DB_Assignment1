/**
 * @author Hsienting Chu
 *
 */

public class dbload {

	public static String fileName;
	public static String parameter;
	public static int pageSize;

	public static void main(String[] args) {
		try {
			parameter = args[0];
			pageSize = Integer.parseInt(args[1]);
			fileName = args[2];

		} catch (Exception e) {
			System.err.println("Parameter is wrong");
		}
		
		dbload dbload = new dbload();
		
	}
}