import java.util.ArrayList;
import model.HashIndex;

/**
 * @author Hsienting Chu
 *
 */
public class HashTable {
	private int size = 0;
	private int currentSize = 0;
	private int hashCode;
	private int pageNum;
	private int recordNum;
	ArrayList<HashIndex> hashArray;
	HashIndex hashIndex;

	public HashTable(int size) {
		this.size = size;
		hashArray = new ArrayList<HashIndex>();

	}

	public void add(int hashCode, int pageNum, int recordNum) {
		this.hashCode = hashCode;
		this.pageNum = pageNum;
		this.recordNum = recordNum;
		if (currentSize < size) {
			hashIndex.getHashCode();

		}
	}

	public int getSize() {
		return size;
	}

	public ArrayList<HashIndex> getHashIndex() {
		return hashArray;
	}
}
