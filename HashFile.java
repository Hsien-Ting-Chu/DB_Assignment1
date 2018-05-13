import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private int pageSize;
	// Given that there are about 2.6 million records, and you want at most 70%
	// occupancy to avoid excessive collision costs - ie capacity for at least
	// 3.7 million records
	// final private int recordSize = 3700000;
	final private int hashIndexSize = 1023; // 2^10 -1
	// final private int bucketSize = recordSize / hashIndexSize;
	private static String HEAPFILE = "./heap.";
	private static String HASHFILE = "./hashindex/hash.";
	private Page currentPage;
	private File heapFile;
	private File hashFile;
	private int count = 0;
	private int recordNum = 0;
	private int pageNum = 0;
	HashTable hashTable = new HashTable();
	HashTable hashtable[];
	FileInputStream heapReader;
	BufferedReader hashReader;
	BufferedWriter hashWriter;

	public HashFile(int pageSize) {
		this.pageSize = pageSize;
		HEAPFILE = HEAPFILE + pageSize;
		heapFile = new File(HEAPFILE);
		hashtable = new HashTable[hashIndexSize];
		for (int i = 0; i < hashIndexSize; i++) {

			try {
				hashtable[i] = new HashTable();
				String hashfile = HASHFILE + i + "." + pageSize;
				hashFile = new File(hashfile);
				hashFile.createNewFile();
			} catch (FileNotFoundException e) {
				System.err.println("File not found:" + HASHFILE);
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Can not create hashFile:" + HASHFILE);
				e.printStackTrace();
			}
		}

	}

	public void importData(int pageSize) {
		try {
			// Read heap file
			heapReader = new FileInputStream(heapFile);
			// Read all data page by page
			ArrayList<Record> results = new ArrayList<Record>();
			byte[] pageBytes = new byte[pageSize];
			int readlen = heapReader.read(pageBytes, 0, pageSize);
			while (readlen > 0) {
				if (readlen != pageSize) {
					throw new Exception("Page Size Error");
				}
				currentPage = new Page(pageBytes, pageSize);
				count += currentPage.getRecordListSize();
				results.addAll(currentPage.getRecordList());
				for (Record result : results) {
					int hashCode = result.getBN_NAME().hashCode();
					int index = hashFunction(result.getBN_NAME());
					HashIndex hashIndex = new HashIndex(hashCode, pageNum, recordNum);
					hashtable[index].add(hashIndex, index);
					// To identify the record's location
					recordNum++;

				}
				results = new ArrayList<Record>();
				pageBytes = new byte[pageSize];
				readlen = heapReader.read(pageBytes, 0, pageSize);
				pageNum++;
				recordNum = 0;

			}
			for (int i = 0; i < hashIndexSize; i++) {
				ArrayList<HashIndex> hasharray = new ArrayList<HashIndex>();
				hasharray.addAll(hashtable[i].getHasharray());
				String hashFile = HASHFILE + i + "." + pageSize;
				hashWriter = new BufferedWriter(new FileWriter(hashFile));
				for (HashIndex hashindex : hasharray) {
					hashWriter.write(hashindex.toString());
					hashWriter.newLine();
					hashWriter.flush();

				}
				hashWriter.close();
			}
			System.out.println("total data:" + count);
		} catch (IOException e) {
			System.err.println("Load Page Byte Error");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Load Page Byte Error");
			e.printStackTrace();
		}
	}

	public void searchData(String keyword) {
		try {
			int tableindex = hashFunction(keyword);
			String hashFile = HASHFILE + tableindex + "." + pageSize;
			hashReader = new BufferedReader(new InputStreamReader(new FileInputStream(hashFile)));
			String reader = hashReader.readLine();
			while (reader != null) {
				String[] strArr = reader.split(",");
				int hashkey = Integer.parseInt(strArr[0]);
				int key = keyword.hashCode();
				if (hashkey == key) {
					int pageNum = Integer.parseInt(strArr[1]);
					int recordNum = Integer.parseInt(strArr[2]);
					heapReader = new FileInputStream(heapFile);
					//To locate specific page by skipping the byte
					int offset = pageNum * pageSize;
					byte[] pageBytes = new byte[pageSize];
					heapReader.skip(offset);
					heapReader.read(pageBytes, 0, pageSize);
					currentPage = new Page(pageBytes, pageSize);
					System.out.println(currentPage.getRecordByIndex(recordNum));
				}
				reader = hashReader.readLine();
			}
		} catch (IOException e) {
			System.err.println("Load Page Byte Error");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Load Page Byte Error");
			e.printStackTrace();
		}
	}

	public int hashFunction(String keyword) {
		int hashCode = Math.abs(keyword.hashCode() % hashIndexSize);
		return hashCode;
	}

	public void close() {
		try {
			if (heapReader != null)
				heapReader.close();
			 if (hashReader != null)
				 hashReader.close();
			 if(hashWriter != null)
				 hashWriter.close();
		} catch (IOException e) {
			System.err.println("close heapFile error");
			e.printStackTrace();
		}
	}

}
