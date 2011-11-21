package MarkovState;

import Common.Configuration;

public class MarkovStateNormal extends AbstractMarkovState {

	/**
	 * Buffer is in the normal state. Maintain the default output rate.
	 */
	@Override
	public void updateSleepTime() {
		Configuration.INSTANCE.getDecoderSleepTime().setSleepTime(
				Configuration.INSTANCE.getPacketRate());
	}
}
