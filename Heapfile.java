import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Page;
import model.Record;

/**
 * @author Hsienting Chu
 *
 */
public class HeapFile {
	private static String HEAPFILE = "/Users/yin/Documents/workspace/DB_Assignment1/heapFile";
	private int pageSize = 1024;
	private Page currentPage;
	private File heapFile;
	private int count = 0;
	FileInputStream heapReader;
	FileOutputStream heapWritter;

	// initiate file control tool
	public HeapFile() {
		heapFile = new File(HEAPFILE);
		try {
			if (!heapFile.exists()) {
				heapFile.createNewFile();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found:" + HEAPFILE);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Can not create heapFile:" + HEAPFILE);
			e.printStackTrace();
		}
	}

	public HeapFile(int pageSize) {
		this();
		this.pageSize = pageSize;
	}

	public void importData(String filePath) {
		try {
			heapWritter = new FileOutputStream(heapFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String recordStr = reader.readLine();
			//call twice, that would remove the first line for column name
			recordStr = reader.readLine();
			ArrayList<String> failList = new ArrayList<String>();
			while (recordStr != null && recordStr.length() > 0) {
				String[] strArr = recordStr.split("\t");
				if (strArr.length != 9){
					failList.add(recordStr);
				}else{
					Record record = new Record(strArr);
					addRecord(record);
					count++;
				}
				recordStr = reader.readLine();
			}
			reader.close();
			//SAVE FOR LAST PAGE
			save();
			System.err.println("fail list:");
			for (int i = 0; i < failList.size(); i++) {
				System.err.println(failList.get(i));
			}
			System.out.println("Total count:" + count);
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found, FilePath:" + filePath);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("importData Fail, FilePath:" + filePath);
			e.printStackTrace();
		}
	}

	public void addRecord(Record record) {
		if (currentPage == null) {
			currentPage = new Page(pageSize);
		} else {
			if (currentPage.getSpace() <= record.getSize()) {
				save();
				currentPage = new Page(pageSize);
			}
		}
		currentPage.addRecord(record);
	}

	private void save() {
		byte[] writeBytes = new byte[pageSize];
		byte[] src = currentPage.getByteArray();
		System.arraycopy(src, 0, writeBytes, 0, src.length);
		try {
			heapWritter.write(writeBytes);
		} catch (IOException e) {
			System.err.println("can not write heapFile");
			e.printStackTrace();
		}
	}

	public void searchData(String keyword) {
		if (!heapFile.exists()) {
			System.out.println("No database");
		} else {
			try {
				heapReader = new FileInputStream(heapFile);
				ArrayList<Record> results = new ArrayList<Record>();
				byte[] pageBytes = new byte[pageSize];
				int readlen = heapReader.read(pageBytes, 0, pageSize);
				while (readlen > 0) {
					if (readlen != pageSize) {
						throw new Exception("Page Size Error");
					}
					currentPage = new Page(pageBytes, pageSize);
					count += currentPage.getRecordListSize();
					results.addAll(currentPage.searchData(keyword));
					pageBytes = new byte[pageSize];
					readlen = heapReader.read(pageBytes, 0, pageSize);
				}
				for (Record result : results) {
					System.out.println(result.toString());
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

	public void close() {
		try {
			if (heapReader != null)
				heapReader.close();
			if (heapWritter != null)
				heapWritter.close();
		} catch (IOException e) {
			System.err.println("close heapFile error");
			e.printStackTrace();
		}
	}

}
