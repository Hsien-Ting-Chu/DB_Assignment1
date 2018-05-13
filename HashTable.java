import java.io.BufferedWriter;
import java.util.ArrayList;

import model.HashIndex;

/**
 * @author Hsienting Chu
 *
 */
public class HashTable {
	private int bucketSize = 0;
	private int currentSize = 0;
	BufferedWriter hashWriter;
	ArrayList<HashIndex> hasharray;

	public HashTable() {
		hasharray = new ArrayList<HashIndex>();
	}

	public void add(HashIndex hashIndex, int index) {
		hasharray.add(hashIndex);

	}

	public int getcurrentSize() {
		return currentSize;
	}

	public ArrayList<HashIndex> getHasharray() {
		return hasharray;
	}
}
