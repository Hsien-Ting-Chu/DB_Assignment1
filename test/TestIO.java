package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import model.Page;
import model.Record;

/**
 * @author Hsienting Chu
 *
 */
public class TestIO {
	private static FileInputStream heapReader;
	private static String FILE_PATH = "D:\\My Documents\\RMIT\\2018 Semester 1\\Datebase System\\Assignment1\\heap.4096";
	private static File file = new File(FILE_PATH);
	private static String HEAPFILE = "./heap.";
	private static int pageSize = 4096 ;
	private static File heapFile;
	private static Page currentPage;
	private static Record currentRecord;
	private static int count = 0;
	public static void main(String[] args){
		
		
/*		
		HEAPFILE = HEAPFILE + pageSize;
		heapFile = new File(HEAPFILE);
		if (!heapFile.exists()) {
			System.out.println("No Heapfile");
		} else {
			try {
				heapReader = new FileInputStream(heapFile);
				ArrayList<Record> results = new ArrayList<Record>();
				byte[] pageBytes = new byte[pageSize];
				int readlen = heapReader.read(pageBytes, 0, 4096);
					currentRecord = new Record(pageBytes);
					System.out.println(currentRecord);
		
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
		*/
	}
		
	
//		TestIO t = new TestIO();
//		t.outputData();
//		t.inputData();

	public void inputData() throws IOException {
		int readReturn = -1;
		FileInputStream reader = new FileInputStream(file);
		byte[] intBytes = new byte[4];
		readReturn = reader.read(intBytes, 0, 4);
		System.out.println(readReturn);
		System.out.println(byteArrayToByteInt(intBytes));
		byte[] strBytes = new byte[14];
		readReturn = reader.read(strBytes, 0, 14);
		System.out.println(readReturn);
		System.out.println(new String(strBytes, StandardCharsets.UTF_8));
		reader.close();
	}

	public void outputData() throws IOException {
		FileOutputStream writer = new FileOutputStream(file);
		writer.write(intToByteArray(644477457));
		System.out.println(file.length());
		byte[] strBytes = "abc<1234".getBytes(StandardCharsets.UTF_8);
		writer.write(strBytes);
		System.out.println(strBytes.length);
		System.out.println(file.length());
		writer.close();
	}

	byte[] intToByteArray(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	int byteArrayToByteInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public void readSrc() {
		try {
			int count = 0;
			BufferedReader br = new BufferedReader(new FileReader("fileName"));
			String line = br.readLine();
			while (line != null) {
				if (count == 0) {
					count++;
					continue;
				}
				String[] s = line.split("\t");
				// s[0] = record.add();
			}
		} catch (Exception e) {
			System.err.println("Could not read the file");
		}
	}
}
