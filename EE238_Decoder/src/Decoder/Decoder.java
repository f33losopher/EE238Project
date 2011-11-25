package Decoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import Common.Configuration;
import Common.Packet;
import Log.Logger;
import MarkovState.MarkovStateFactory;

/**
 * Decoder is a separate thread that continuously decodes data stored in it's
 * internal buffer.
 * 
 * @author Felix
 * 
 */
public class Decoder extends Thread {
	private SocketReader _socketReader;
	private boolean _continueDecode = true;
	private FileOutputStream _outFile;
	private MarkovStateFactory _markovFactory = MarkovStateFactory.INSTANCE;
	private long _bufferUnderflow = 0;
	private Logger _logger;

	public Decoder() throws SocketException, InterruptedException,
			FileNotFoundException {
		super("Decoder");
		this._socketReader = new SocketReader();
		this._outFile = new FileOutputStream(
				Configuration.INSTANCE.getOutputFile());
		this._logger = Logger.Instance(this);
		start();
	}

	public void run() {
		try {
			while (this._continueDecode) {
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
		while (this._continueDecode) {
			p = DecoderBuffer.INSTANCE.popPacket();
			simulateDisplay(p);
			this._markovFactory.getMarkovState().updateSleepTime();
			
			this._logger.logBufferAndDelay();

			Thread.sleep(Configuration.INSTANCE.getDecoderSleepTime()
					.getMilliSec(), Configuration.INSTANCE
					.getDecoderSleepTime().getNanoSec());

			if (DecoderBuffer.INSTANCE.getBufferSize() <= 0) {
				// This accounts for buffer underflow. Must go through
				// the pre-roll delay again.
				++this._bufferUnderflow;
				this._logger.logBufferUnderFlow();
				return;
			}
		}
	}

	// Simulate the decoder playing, but actually just writing the bytes
	// to file. This is so we can play the video with the player as well
	// as run lencod.exe on the file to analyze the data.
	public void simulateDisplay(Packet p) throws IOException {
		this._outFile.write(p.getPacket());
	}

	public void stopDecoder() {
		this._continueDecode = false;
	}

	public long getBufferUnderflow() {
		return this._bufferUnderflow;
	}
}
