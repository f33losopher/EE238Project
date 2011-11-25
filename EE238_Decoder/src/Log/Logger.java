package Log;

import java.io.FileWriter;
import java.io.IOException;

import Common.Configuration;
import Decoder.Decoder;
import Decoder.DecoderBuffer;

public class Logger {

	private static Logger _logger = null;
	private FileWriter _logFile;
	private FileWriter _logFileExcel;
	private Decoder _decoder;
	private int _prevBufferSize = 0;

	/**
	 * Used to create the first instance of the Logger
	 * 
	 * @param decoder
	 * @return
	 */
	public static Logger Instance(Decoder decoder) {
		if (_logger == null) {
			_logger = new Logger(decoder);
		}
		return _logger;
	}

	/**
	 * Used to get an instance of the Logger if it has already been created.
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return _logger;
	}

	private Logger(Decoder decoder) {
		this._decoder = decoder;

		try {
			this._logFile = new FileWriter(Configuration.INSTANCE.getLogFile());
			this._logFileExcel = new FileWriter(
					Configuration.INSTANCE.getLogFileExcel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logs the Buffer size for each packet IF the buffer size has changed. Only
	 * logging changes because the file becomes to large if we log the buffer
	 * size for every packet.
	 * 
	 * @throws IOException
	 */
	public void logBufferAndDelay() throws IOException {
		int curBufferSize = DecoderBuffer.INSTANCE.getBufferSize();

		if (this._prevBufferSize != curBufferSize) {
			// Log the data in the format for Excel
			this._logFileExcel.write(curBufferSize + "\n");
			this._logFileExcel.flush();

			// Log the data in text file for readability
			this._logFile.write("Buffer Size: "
					+ curBufferSize
					+ " Millisec: "
					+ Configuration.INSTANCE.getDecoderSleepTime()
							.getMilliSec() + " Nanosec: "
					+ Configuration.INSTANCE.getDecoderSleepTime().getNanoSec()
					+ "\n");
			this._logFile.flush();

			this._prevBufferSize = curBufferSize;
		}
	}

	public void logBufferUnderFlow() throws IOException {
		this._logFile.write("BUFFER UNDERFLOW. Going through Pre-Roll Delay\n");
		this._logFile.flush();
	}

	public void logDroppedPacket() throws IOException {
		this._logFile.write("BUFFER OVERFLOW. Dropping Packets\n");
		this._logFile.flush();
	}

	public void logStatistics() throws IOException {
		// Subtract 1 from buffer underflow because the last one counts as
		// a buffer underflow to detect end of transmission.
		this._logFile
				.write("***************************************************\n");
		this._logFile.write("End of Transmission Detected\n");
		this._logFile.write("Input File:                                  "
				+ Configuration.INSTANCE.getInputFile() + "\n");
		this._logFile.write("Output File:                                 "
				+ Configuration.INSTANCE.getOutputFile() + "\n");
		this._logFile.write("Packet Size (Bytes):                         "
				+ Configuration.INSTANCE.getPacketSize() + "\n");
		this._logFile.write("Packet Rate (Packets/sec):                   "
				+ Configuration.INSTANCE.getPacketRate() + "\n");
		this._logFile.write("Max Buffer Size (Packets):                   "
				+ Configuration.INSTANCE.getMaxBufferSize() + "\n");
		this._logFile.write("Attenuation Factor:                          "
				+ Configuration.INSTANCE.getAttenuationFactor() + "\n");
		this._logFile.write("Number of Buffer Underflow Occurences:       "
				+ (this._decoder.getBufferUnderflow() - 1) + "\n");
		this._logFile.write("Number of Dropped Packets (Buffer Overflow): "
				+ DecoderBuffer.INSTANCE.getNumDroppedPackets() + "\n");
		this._logFile.flush();
	}
}
