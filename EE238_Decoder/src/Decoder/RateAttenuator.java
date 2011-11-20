package Decoder;

import Common.Configuration;

public class RateAttenuator {

	private final static int _idealBuffer = Configuration.INSTANCE
			.getMaxBufferSize() / 2;
	private final static int _bufferMargin = (int) ((Configuration.INSTANCE
			.getMaxBufferSize() / 2) * .1);

	public static long updateSleepTime() {
		int bufferSize = DecoderBuffer.INSTANCE.getBufferSize();
		long sleepTime = Configuration.INSTANCE.getSleepTime();

		int packetRate = Configuration.INSTANCE.getPacketRate();
		double attenuationFactor = Configuration.INSTANCE
				.getAttenuationFactor();
		if (bufferSize > Configuration.INSTANCE.getUpperThreshold()) {
			sleepTime = (long) (1000 / (packetRate + (packetRate * attenuationFactor)));
		} else if (bufferSize < Configuration.INSTANCE.getLowerThreshold()) {
			sleepTime = (long) (1000 / (packetRate - (packetRate * attenuationFactor)));
		}

		if ((bufferSize > (_idealBuffer - _bufferMargin))
				|| (bufferSize < (_idealBuffer + _bufferMargin))) {
			sleepTime = Configuration.INSTANCE.getSleepTime();
		}

		return sleepTime;
	}
}
