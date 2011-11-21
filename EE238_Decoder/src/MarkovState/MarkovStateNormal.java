package MarkovState;

import Common.Configuration;

public class MarkovStateNormal extends AbstractMarkovState {

	/**
	 * Buffer is in the normal state. Maintain the default
	 * output rate.
	 */
	@Override
	public void updateSleepTime() {
		Configuration.INSTANCE.getDecoderSleepTime().setMilliSec(
				1000 / Configuration.INSTANCE.getPacketRate());
		Configuration.INSTANCE.getDecoderSleepTime().setNanoSec(0);
	}
}
