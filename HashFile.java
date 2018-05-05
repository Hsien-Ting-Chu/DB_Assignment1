import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Record;

/**
 * 
 */

/**
 * @author Hsienting Chu
 *
 */
public class HashFile {
	// Given that there are about 2.6 million records, and you want at most 70%
	// occupancy to avoid excessive collision costs - ie capacity for at least
	// 3.7 million records
	final private int indexSize = 37000;
	final private int bucketSize = 100;
	private static String HEAPFILE = "./heap";
	private File heapFile;
	FileInputStream heapReader;
	FileOutputStream hashWritter;
	HashMap hashBucket = new HashMap<String,Record>();
	public void importData(int pageSize) {
		HEAPFILE = HEAPFILE + "." + pageSize;
		heapFile = new File(HEAPFILE);
		if (!heapFile.exists()) {
			System.out.println("No Heapfile");
			try {
				heapReader = new FileInputStream(heapFile);
				ArrayList<Record> results = new ArrayList<Record>();
				byte[] pageBytes = new byte[pageSize];
				int readlen = heapReader.read(pageBytes, 0, pageSize);
				while (readlen > 0) {
					if (readlen != pageSize) {
						throw new Exception("Page Size Error");
					}
				}
			} catch (IOException e) {
				System.err.println("Load Page Byte Error");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Load Page Byte Error");
				e.printStackTrace();
			}

		}
	}
	public void hashTable(){
		
	}
	
	
}
