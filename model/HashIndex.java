package model;

/**
 * 
 * @author Hsienting Chu
 */
public class HashIndex {

	private int hashCode;
	private int pageNum;
	private int index;
	public HashIndex(int hashCode, int pageNum, int index){
		this.hashCode = hashCode;
		this.pageNum = pageNum;
		this.index = index; 
	}

	public int getHashCode() {
		return hashCode;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getIndex() {
		return index;
	}

}
