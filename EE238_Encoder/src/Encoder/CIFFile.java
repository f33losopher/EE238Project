package Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CIFFile {
	// Byte array of the file
	private byte[] _fileByteArray;

	// Size of the file in Bytes
	private int _fileSize;

	public CIFFile() throws IOException {
		initialize();

	}

	public void initialize() throws FileNotFoundException, IOException {
		File file = new File(Common.Configuration.INSTANCE.getInputFile());
		FileInputStream fin = new FileInputStream(file);
		_fileSize = fin.available();

		_fileByteArray = new byte[_fileSize];
		fin.read(_fileByteArray);
	}

	public byte[] getFileByteArray() {
		return _fileByteArray;
	}

	public int getFileSize() {
		return _fileSize;
	}
}
