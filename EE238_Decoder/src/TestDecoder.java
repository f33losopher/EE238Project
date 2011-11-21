import java.io.FileNotFoundException;
import java.net.SocketException;

import Common.Configuration;
import Decoder.Decoder;
import Decoder.DecoderBuffer;


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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
