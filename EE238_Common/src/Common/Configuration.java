package Common;

public enum Configuration {
	INSTANCE;

	// Name of the Input File
	private final String _inputFile = "src/Inputs/UH_ext304_silent_30Hz_300fs.cif";

	// Name of the Output File
	private final String _outputFile = "src/Outputs/Silent_Transmitted.cif";

	// Size (In Bytes) of each packet to send to network
	private final int _packetSize = 512;

	// Max buffer size in packets
	private final int _maxBufferSize = 100;

	// The upper threshold of the buffer where we want to speed up the
	// output rate to prevent buffer overflow;
	private final int _upperThreshold = (int) (_maxBufferSize * .08);

	// The lower threshold of the buffer where we want to decrease the
	// output rate to prevent buffer underflow
	private final int _lowerThreshold = (int) (_maxBufferSize * .02);

	// The rate (In Packets/Second) to send
	private final int _packetRate = 100;

	// The sleep rate to meter how many packets we send/read per millisecond
	private final long _sleepTime = (1000 / _packetRate);

	// The Attenuation factor to increase/decrease the output rate
	private final double _attenuationFactor = 0.02;

	// The port to send/receive packets
	private final int _port = 62000;

	// Socket Read Timeout in milliseconds
	private final int _socketTimeout = 5000;

	// IP Address of the decoder
	private final byte[] _decoderAddr = { (byte) 0xC0, (byte) 0xA8, (byte) 0x1,
			(byte) 0x64 };

	/**
	 * Returns the name of the Input File
	 * 
	 * @return
	 */
	public String getInputFile() {
		return _inputFile;
	}

	/**
	 * Returns the name of the Output File
	 * 
	 * @return
	 */
	public String getOutputFile() {
		return _outputFile;
	}

	/**
	 * Returns the size of each created packet in Bytes
	 * 
	 * @return
	 */
	public int getPacketSize() {
		return _packetSize;
	}

	/**
	 * Returns the max buffer size in packets
	 * 
	 * @return
	 */
	public int getMaxBufferSize() {
		return _maxBufferSize;
	}

	/**
	 * Returns the upper threshold (in packets) where we want to speed up the
	 * output rate to prevent buffer overflow
	 * 
	 * @return
	 */
	public int getUpperThreshold() {
		return _upperThreshold;
	}

	/**
	 * Returns the lower threshold (in packets) where we want to decrease the
	 * output rate to prevent buffer underflow
	 * 
	 * @return
	 */
	public int getLowerThreshold() {
		return _lowerThreshold;
	}

	/**
	 * Returns the rate packets are sent (Packets/Second)
	 * 
	 * @return
	 */
	public int getPacketRate() {
		return _packetRate;
	}

	/**
	 * Returns the sleep time for sending each packet to ensure we match the
	 * packet rate
	 * 
	 * @return
	 */
	public long getSleepTime() {
		return _sleepTime;
	}

	/**
	 * The Attenuation factor to increase/decrease the output rate
	 * 
	 * @return
	 */
	public double getAttenuationFactor() {
		return _attenuationFactor;
	}

	/**
	 * Returns the port to send/receive packets
	 * 
	 * @return
	 */
	public int getPort() {
		return _port;
	}

	/**
	 * Returns the socket read timeout in milliseconds
	 * 
	 * @return
	 */
	public int getSocketTimeout() {
		return _socketTimeout;
	}
	
	/**
	 * Returns the Decoder address in a byte array
	 * @return
	 */
	public byte[] getDecoderAddr()
	{
		return _decoderAddr;
	}
}
