import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Page;
import model.Record;

/**
 * @author Hsienting Chu
 *
 */
public class Heapfile {
	private static String HEAPFILE = "C://heapfile.dat";
	private int pageSize = 1024;
	private Page currentPage;
	private File heapFile;
	FileInputStream reader;
	FileOutputStream writter;

	// initiate file control tool
	public Heapfile() {
		heapFile = new File(HEAPFILE);
		try {
			if (!heapFile.exists()) {
				heapFile.createNewFile();
			}
			reader = new FileInputStream(heapFile);
			writter = new FileOutputStream(heapFile);
		} catch (FileNotFoundException e) {
			System.err.println("File not found:" + HEAPFILE);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Can not create heapFile:" + HEAPFILE);
			e.printStackTrace();
		}
	}

	public Heapfile(int pageSize) {
		this();
		this.pageSize = pageSize;
	}

	public void importData(Record record) {
		if (currentPage == null) {
			currentPage = new Page(pageSize);
		} else {
			if (currentPage.getSpace() < record.getSize()) {
				save();
				currentPage = new Page(pageSize);
			} else {
				currentPage.addRecord(record);
			}
		}
	}
	
	public void finishedImport() {
		save();
	}
	

	private void save() {
		byte[] writeBytes = new byte[pageSize];
		byte[] src = currentPage.getByteArray();
		System.arraycopy(src, 0, writeBytes, 0, src.length );
		try {
			writter.write(writeBytes);
		} catch (IOException e) {
			System.err.println("can not write heapFile");
			e.printStackTrace();
		}
	}

	public void searchData(String keyword) {

	}

}
