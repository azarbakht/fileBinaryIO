package fileBinaryIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class FileBinaryIO {

	public static void main(String[] args) throws IOException, ClassNotFoundException{

		output();
		input();

		dataStreamOutput();
		dataStreamInput();

		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

		findEOF();

		objectOutputStreaming();
		objectInputStreaming();

		randomAccessFileIO();
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


	public static void objectOutputStreaming() throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("fileWithObjectsInIt.dat"));

		output.writeUTF("Emerson saving objects here...");
		output.writeDouble(14);
		output.writeObject(new java.util.Date());

		output.close();
	}

	public static void objectInputStreaming() throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("fileWithObjectsInIt.dat"));

		String stringUTFcontent = input.readUTF();
		double adad = input.readDouble();
		java.util.Date date = (java.util.Date)(input.readObject());
		System.out.println(stringUTFcontent + "\n" + adad  + "\n" + date);

		input.close();
	}

	public static void randomAccessFileIO() throws IOException {

		RandomAccessFile inputOutput = new RandomAccessFile("randomAccessFile.dat", "rw");

		inputOutput.setLength(0);

		System.out.println("the current randomAccessFile's length is = " + inputOutput.length());

		inputOutput.seek(0);
		
		for (int i = 1; i < 100; i++) {
			inputOutput.writeInt(i);
		}
		
		inputOutput.seek(0 * 4);
		System.out.println("The first number stored is: " + inputOutput.readInt());

		inputOutput.seek(1 * 4);
		System.out.println("The 2nd number stored is: " + inputOutput.readInt());

		inputOutput.seek(2 * 4);
		System.out.println("The third number stored is: " + inputOutput.readInt());

		inputOutput.seek(9 * 4);
		System.out.println("The tenth number stored is: " + inputOutput.readInt());

		inputOutput.writeInt(143);
		
		inputOutput.seek(10 * 4);
		System.out.println("the eleventh number stored is: " + inputOutput.readInt());
		
		inputOutput.seek(inputOutput.length());
		
		System.out.println("current file length is: " + inputOutput.length());
		inputOutput.writeInt(918);
		System.out.println("Now, the updated file length is: " + inputOutput.length());
		
		inputOutput.close();
	}

}
