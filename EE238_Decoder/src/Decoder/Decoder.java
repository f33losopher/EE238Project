package Decoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import Common.Configuration;
import Common.Packet;

public class Decoder extends Thread {
	private SocketReader _socketReader;
	private boolean _continueDecode = true;
	private FileOutputStream _outFile;
	private long _sleepTime;


	public Decoder() throws SocketException, InterruptedException,
			FileNotFoundException {
		super("Decoder");
		_socketReader = new SocketReader();
		_outFile = new FileOutputStream(Configuration.INSTANCE.getOutputFile());
		_sleepTime = Configuration.INSTANCE.getSleepTime();
		start();
	}

	public void run() {
		try {
			while (_continueDecode) {
				decode();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void decode() throws InterruptedException, IOException {
		// This is for the initial pre-roll delay. We want the buffer
		// size to get to 50% before starting to read from the buffer
		while (DecoderBuffer.INSTANCE.getBufferSize() < (Configuration.INSTANCE
				.getMaxBufferSize() / 2)) {
			// Do nothing, wait for buffer to fill up
		}

		readBuffer();
	}

	public void readBuffer() throws InterruptedException, IOException {
		Packet p;
		while (_continueDecode) {
			p = DecoderBuffer.INSTANCE.popPacket();
			simulateDisplay(p);
			attenuateOutputRate();
			Thread.sleep(_sleepTime);

			if (DecoderBuffer.INSTANCE.getBufferSize() <= 0) {
				// This accounts for buffer underflow. Must go through
				// the pre-roll delay again.
				return;
			}
		}
	}

	public void attenuateOutputRate() {	
		_sleepTime = RateAttenuator.updateSleepTime();
	}

	// Simulate the decoder playing, but actually just writing the bytes
	// to file. This is so we can play the video with the player as well
	// as run lencod.exe on the file to analyze the data.
	public void simulateDisplay(Packet p) throws IOException {
		_outFile.write(p.getPacket());
	}

	public void stopDecoder() {
		_continueDecode = false;
	}
}
