package Common;

/**
 * Contains the configuration parameters for the Encoder/Decoder. This is a
 * singleton class.
 * 
 * @author Felix
 * 
 */
public enum Configuration {
	INSTANCE;
	
	// Name of the Input File
	private final String _inputFile = "src/Inputs/UH_ext304_silent_30Hz_300fs.cif";

	// Size (In Bytes) of each packet to send to network
	private final int _packetSize = 256;

	// Max buffer size in packets
	private final int _maxBufferSize = 500;

	// The upper threshold of the buffer where we want to speed up the
	// output rate to prevent buffer overflow;
	private final int _upperThreshold = (int) (this._maxBufferSize * .8);

	// The lower threshold of the buffer where we want to decrease the
	// output rate to prevent buffer underflow
	private final int _lowerThreshold = (int) (this._maxBufferSize * .2);

	// The rate (In Packets/Second) to send
	private final int _packetRate = 300;

	// Conversion from Seconds to Milliseconds
	private final int SEC_TO_MILLISEC = 1000;

	// The sleep rate to meter how many packets the decoder sends per
	// millisecond
	// and nanosecond
	private SleepTime _decoderSleepTime = new SleepTime(this._packetRate);

	// The port to send/receive packets
	private final int _port = 62000;

	// Socket Read Timeout in milliseconds
	private final int _socketTimeout = 5000;

	// IP Address of the decoder
	private final byte[] _decoderAddr = { (byte) 0xC0, (byte) 0xA8, (byte) 0x1,
			(byte) 0x64 };
	
	// ///////////////////////////////////////
	// Parameters to change for each trial ///
	
	// Name of the Output File
	private final String _outputFile = "src/Outputs/Silent_Transmitted.cif";

	// Name of the Log File
	private final String _logFile = "src/Outputs/Log.txt";	
	
	// Name of the Log File with just the buffer size. Formatted for Excel
	private final String _logFileExcel = "src/Outputs/Log_Excel.txt";	
	
	// The sleep rate to meter how many packets the encoder sends per
	// millisecond
	// and nanosecond
	private SleepTime _encoderSleepTime = new SleepTime(this._packetRate);	
	
	// The Attenuation factor to increase/decrease the output rate
	private final double _attenuationFactor = 0.02;
	
	//////////////// END /////////////////////
	//////////////////////////////////////////

	/**
	 * Returns the name of the Input File
	 * 
	 * @return
	 */
	public String getInputFile() {
		return this._inputFile;
	}

	/**
	 * Returns the name of the Output File
	 * 
	 * @return
	 */
	public String getOutputFile() {
		return this._outputFile;
	}

	/**
	 * Returns the name of the Log File
	 * 
	 * @return
	 */
	public String getLogFile() {
		return this._logFile;
	}

	/**
	 * Returns the name of the Log File formatted for Excel
	 * @return
	 */
	public String getLogFileExcel() {
		return this._logFileExcel;
	}

	/**
	 * Returns the size of each created packet in Bytes
	 * 
	 * @return
	 */
	public int getPacketSize() {
		return this._packetSize;
	}

	/**
	 * Returns the max buffer size in packets
	 * 
	 * @return
	 */
	public int getMaxBufferSize() {
		return this._maxBufferSize;
	}

	/**
	 * Returns the upper threshold (in packets) where we want to speed up the
	 * output rate to prevent buffer overflow
	 * 
	 * @return
	 */
	public int getUpperThreshold() {
		return this._upperThreshold;
	}

	/**
	 * Returns the lower threshold (in packets) where we want to decrease the
	 * output rate to prevent buffer underflow
	 * 
	 * @return
	 */
	public int getLowerThreshold() {
		return this._lowerThreshold;
	}

	/**
	 * Returns the rate packets are sent (Packets/Second)
	 * 
	 * @return
	 */
	public int getPacketRate() {
		return this._packetRate;
	}

	/**
	 * Returns conversion factor from Seconds to Milliseconds
	 * 
	 * @return
	 */
	public int getSec2Millisec() {
		return this.SEC_TO_MILLISEC;
	}

	/**
	 * Returns the sleep time for the encoder
	 * 
	 * @return
	 */
	public SleepTime getEncoderSleepTime() {
		return this._encoderSleepTime;
	}

	/**
	 * Returns the sleep time for the decoder
	 * 
	 * @return
	 */
	public SleepTime getDecoderSleepTime() {
		return this._decoderSleepTime;
	}

	/**
	 * The Attenuation factor to increase/decrease the output rate
	 * 
	 * @return
	 */
	public double getAttenuationFactor() {
		return this._attenuationFactor;
	}

	/**
	 * Returns the port to send/receive packets
	 * 
	 * @return
	 */
	public int getPort() {
		return this._port;
	}

	/**
	 * Returns the socket read timeout in milliseconds
	 * 
	 * @return
	 */
	public int getSocketTimeout() {
		return this._socketTimeout;
	}

	/**
	 * Returns the Decoder address in a byte array
	 * 
	 * @return
	 */
	public byte[] getDecoderAddr() {
		return this._decoderAddr;
	}
}
