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
	private final int _maxBufferSize = 1000;

	// The upper threshold of the buffer where we want to speed up the
	// output rate to prevent buffer overflow;
	private final int _upperThreshold = (int) (_maxBufferSize * .8);

	// The lower threshold of the buffer where we want to decrease the
	// output rate to prevent buffer underflow
	private final int _lowerThreshold = (int) (_maxBufferSize * .2);

	// The rate (In Packets/Second) to send
	private final int _packetRate = 500;

	private final int SEC_TO_MILLISEC = 1000;
	// The sleep rate to meter how many packets the encoder sends per millisecond
	// and nanosecond
	private SleepTime _encoderSleepTime = new SleepTime(SEC_TO_MILLISEC/_packetRate, 0);
	
	// The sleep rate to meter how many packets the decoder sends per millisecond
	// and nanosecond
	private SleepTime _decoderSleepTime = new SleepTime(SEC_TO_MILLISEC/_packetRate, 0);

	// The Attenuation factor to increase/decrease the output rate
	private final double _attenuationFactor = 0.15;

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
	 * Returns the sleep time for the encoder
	 * 
	 * @return
	 */
	public SleepTime getEncoderSleepTime() {
		return _encoderSleepTime;
	}

	/**
	 * Returns the sleep time for the decoder
	 * @return
	 */
	public SleepTime getDecoderSleepTime() {
		return _decoderSleepTime;
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
