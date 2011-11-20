import java.io.FileNotFoundException;
import java.net.SocketException;

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
			
			while(true)
			{
				System.out.println("Buffer: " + DecoderBuffer.INSTANCE.getBufferSize());
				Thread.sleep(1000);
			}
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
