import java.io.*;
/**
 * @author Hsienting Chu
 *
 */
public class dbload {
	public static void main(String[] args) {
		try {
			String parameter = args[0];
			int pageSize = Integer.parseInt(args[1]);
			String heapFile = args[2];

		} catch (Exception e) {
			System.err.println("Parameter is wrong");
		}
		Heapfile heapfile = new Heapfile();
		
	}
}