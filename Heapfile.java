import java.io.*;
import java.util.*;

/**
 * @author Hsienting Chu
 *
 */
public class Heapfile {

	public int count = 0;

	
	
	public void inputData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("fileName"));
			String line = br.readLine();
			if(count == 0){
				count++;
				continue;
			}
			
			while (line != null) {
				String[] s = line.split("\t");
//				s[0] = record.add();
			}

			DataInputStream dos = new DataInputStream(new FileInputStream("binout.txt"));
			System.out.println(dos.readInt());
			System.out.println(dos.readUTF());
			System.out.println(dos.readInt());
			dos.close();

		} catch (Exception e) {
			System.err.println("Could not read the file");
		}

	}

	public void outputData() throws Exception {
		DataOutputStream fos = new DataOutputStream(new FileOutputStream("binout.txt"));
		fos.writeInt(1234);
		fos.writeUTF("abcd");
		fos.writeByte(1234);

		fos.close();

	}
}
