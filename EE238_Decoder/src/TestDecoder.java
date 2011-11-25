import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import Common.Configuration;
import Decoder.Decoder;
import Decoder.DecoderBuffer;
import Log.Logger;

/**
 * Creates an instance of the decoder to wait and listen for packets on a port.
 * Prints out the size of the internal buffer 10 times per second.
 * 
 * @author Felix
 * 
 */
public class TestDecoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Decoder decoder = new Decoder();
			System.out.println("Made decoder");

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
