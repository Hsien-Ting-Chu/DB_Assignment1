package model;

/**
 * 
 * @author Hsienting Chu
 */
import java.util.ArrayList;

public class Page extends ByteProcessor {
	private ArrayList<Record> recordList;
	private int pageSize = 0;
	
	public Page(int pageSize) {
		recordList = new ArrayList<Record>();
		this.pageSize = pageSize;
	}
	
	public Page(byte[] pageBytes, int pageSize) {
		recordList = new ArrayList<Record>();
		this.pageSize = pageSize;
		//load record
		int nowIndex = 0;
		byte[] dataLenBytes = getPartBytes(pageBytes, nowIndex, INT_BYTE_LENGTH);
		nowIndex += INT_BYTE_LENGTH;
		int recordLen = bytesToInt(dataLenBytes);
		byte[] recordBytes = getPartBytes(pageBytes, nowIndex, recordLen);
		nowIndex += recordLen;
		while(recordLen > 0){
			Record record = new Record(recordBytes);
			recordList.add(record);
			//when data fill up to the end of page, it will try to read next len data
			//so it will cause ArrayIndexOutOfBoundsException  
			if(nowIndex + INT_BYTE_LENGTH >= pageSize){
				break;
			}
			dataLenBytes = getPartBytes(pageBytes, nowIndex, INT_BYTE_LENGTH);
			nowIndex += INT_BYTE_LENGTH;
			recordLen = bytesToInt(dataLenBytes);
			recordBytes = getPartBytes(pageBytes, nowIndex, recordLen);
			nowIndex += recordLen;
		}
	}

	//Checking in HeapFile class, so don't check here
	public void addRecord(Record record) {
		recordList.add(record);
	}
	
	public int getRecordListSize() {
		return recordList.size();
	}

	public int getSize() {
		int sum = 0;
		for (Record record : recordList) {
			sum = sum + record.getSize();
		}
		//should plus space for record total length for each record
		return sum + recordList.size() * INT_BYTE_LENGTH;
	}

	// need keep a space for data len
	public int getSpace() {
		return pageSize - (getSize() + INT_BYTE_LENGTH);
	}

	public byte[] getByteArray() {
		int len = recordList.size();
		byte[] data = new byte[getSize()];
		Record record;
		int nowIndex = 0;
		for (int i = 0; i < len; i++) {
			record = recordList.get(i);
			System.arraycopy(intToBytes(record.getSize()), 0, data, nowIndex, INT_BYTE_LENGTH);
			nowIndex += INT_BYTE_LENGTH;
			System.arraycopy(record.getBytes(), 0, data, nowIndex, record.getSize());
			nowIndex += record.getSize();
		}
		return data;
	}
	
	public ArrayList<Record> searchData(String keyword) {
		ArrayList<Record> results = new ArrayList<Record>();
		for (Record record : recordList) {
			if(record.getBN_NAME().indexOf(keyword) > -1){
				results.add(record);
			}
		}
		return results;
	}

}
