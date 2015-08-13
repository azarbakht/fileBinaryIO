package fileBinaryIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileBinaryIO {

	public static void main(String[] args) throws IOException{

		output();
		input();
		dataStreamOutput();
		dataStreamInput();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		findEOF();
	}

	public static void output() throws IOException {
		// OUTPUT *********************************************************
		FileOutputStream output = new FileOutputStream("BinaryFile.dat");

		for (int i = 0; i <= 10; i++){
			output.write(i);
		}

		output.close();
	}

	public static void input() throws IOException {

		// INPUT -- for bytes only****************************************
		FileInputStream input = new FileInputStream("BinaryFile.dat");

		int value;
		while((value = input.read()) != -1){
			System.out.println(value + " \n");
		}

		input.close();	
	}

	public static void dataStreamOutput() throws IOException {
		// Data stream -- for IO beyond bytes, and do Int, short, UTF String, Boolean, etc. 
		DataOutputStream output = new DataOutputStream(new FileOutputStream("BinaryFile.dat"));

		output.writeUTF("Emerson Azarbakht");
		output.writeByte(2);
		output.writeShort(3);
		output.writeInt(79);
		output.writeChar('v');
		output.writeDouble(185.4);
		output.writeBoolean(true);
		output.writeUTF("That's it.");
		output.close();

	}

	public static void dataStreamInput() throws IOException {
		DataInputStream input = new DataInputStream(new FileInputStream("BinaryFile.dat"));

		System.out.println(input.readUTF() + "\t" + input.readByte());
		System.out.println("\t" + input.readShort()+ "\t" + input.readInt()+ "\t");
		System.out.println(input.readChar()+ "\t" + input.readDouble());
		System.out.println(input.readBoolean()+ "\t" + input.readUTF());

		input.close();

	}

	public static void findEOF() throws IOException{

		DataOutputStream output = new DataOutputStream(new FileOutputStream("BinaryFile.dat"));

		output.writeUTF("Emerson Azarbakht");
		output.writeByte(2);
		output.writeShort(3);
		output.writeInt(79);
		output.writeChar('v');
		output.writeDouble(185.4);
		output.writeBoolean(true);
		output.writeUTF("That's it.");
		output.close();

		DataInputStream input = new DataInputStream(new FileInputStream("BinaryFile.dat"));

		// Find EOF
		try {
			while(true){
				System.out.println(input.readByte());
			}
		} catch (EOFException e) {
			System.out.println("End of File: All data was read");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		input.close();
	}

}
