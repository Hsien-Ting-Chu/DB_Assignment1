package model;

import java.util.ArrayList;

public class Page {
	private ArrayList<Record> recordList;
	private int pageSize = 0;
	
	public Page(int pageSize) {
		recordList = new ArrayList<Record>();
		this.pageSize = pageSize;
	}

	//Checking in HeapFile class, so don't check here
	public void addRecord(Record record) {
		recordList.add(record);
	}

	public int getSpace() {
		int sum = 0;
		for (Record record : recordList) {
			sum = sum + record.getSize();
		}
		return pageSize - sum;
	}

	public byte[] getByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
