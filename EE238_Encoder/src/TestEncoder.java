import java.io.IOException;

import Encoder.CIFFile;
import Encoder.Packager;



public class TestEncoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			CIFFile cif = new CIFFile();
			System.out.println("Sending Packets");
			System.out.println("Size in bytes " + Integer.toString(cif.getFileSize()));
			
			Packager packager = new Packager();
			packager.sendFile();
			
			System.out.println("Finished Sending Packets");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
