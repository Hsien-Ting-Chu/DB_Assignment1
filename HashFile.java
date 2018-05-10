import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import model.HashIndex;
import model.Page;
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
	final private int hashTableSize = 32767; // 2^16 -1
	final private int bucketSize = 113; // 3600000 / 32767 = 112.9
	private static String HEAPFILE = "./heap.";
	private Page currentPage;
	private File heapFile;
	private int count = 0;
	private int recordNum = 0;
	private int pageNum = 0;
	FileInputStream heapReader;
	FileOutputStream hashWritter;

	public void importData(int pageSize) {
		HEAPFILE = HEAPFILE + pageSize;
		heapFile = new File(HEAPFILE);
		if (!heapFile.exists()) {
			System.out.println("No Heapfile");
		} else {
			try {
				heapReader = new FileInputStream(heapFile);
				ArrayList<Record> results = new ArrayList<Record>();
				byte[] pageBytes = new byte[pageSize];
				int readlen = heapReader.read(pageBytes, 0, pageSize);
				while (readlen > 0 && pageNum < 1) {
					if (readlen != pageSize) {
						throw new Exception("Page Size Error");
					}
					currentPage = new Page(pageBytes, pageSize);
					count += currentPage.getRecordListSize();
					results.addAll(currentPage.getRecordList());
					for (Record result : results) {
						HashTable[] hashTable = new HashTable[hashTableSize];
						int hashCode = result.getBN_NAME().hashCode();
						hashTable[hashCode].add(hashCode, pageNum, recordNum);
						// System.out.println("PageNum : " + pageNum + "
						// RecordNum : " + record + " name : " + result);
						recordNum++;
					}
					results = new ArrayList<Record>();
					pageBytes = new byte[pageSize];
					readlen = heapReader.read(pageBytes, 0, pageSize);
					pageNum++;
					recordNum = 0;
				}

				System.out.println("result data:" + results.size());
				System.out.println("total data:" + count);
			} catch (IOException e) {
				System.err.println("Load Page Byte Error");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Load Page Byte Error");
				e.printStackTrace();
			}
		}
	}

}
