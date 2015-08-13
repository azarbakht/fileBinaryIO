package fileBinaryIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileBinaryIO {

	public static void main(String[] args) throws IOException{
		
		// OUTPUT ***************************************************************************
		FileOutputStream output = new FileOutputStream("BinaryFile.dat");
		
		for (int i = 0; i <= 10; i++){
			output.write(i);
		}
		
		output.close();
		
		// INPUT ****************************************************************************
		FileInputStream input = new FileInputStream("BinaryFile.dat");
		
		int value;
		while((value = input.read()) != -1){
			System.out.println(value + " \n");
		}
		
		input.close();
		
		// OUTPUT with append ***************************************************************
		
	}
}
