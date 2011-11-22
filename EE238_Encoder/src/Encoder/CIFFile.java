package Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Holds the raw byte stream of the CIF file.
 * @author Felix
 *
 */
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
		this._fileSize = fin.available();

		this._fileByteArray = new byte[this._fileSize];
		fin.read(this._fileByteArray);
	}

	public byte[] getFileByteArray() {
		return this._fileByteArray;
	}

	public int getFileSize() {
		return this._fileSize;
	}
}
