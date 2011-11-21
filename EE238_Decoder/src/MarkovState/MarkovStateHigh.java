package MarkovState;

import Common.Configuration;

public class MarkovStateHigh extends AbstractMarkovState{

	/**
	 * Buffer is in the HIGH state. Increase the output rate.
	 */
	@Override
	public void updateSleepTime() {
		// Same as Rate + (Rate * Attenuation Factor)
		double slowedRate = (1 + Configuration.INSTANCE.getAttenuationFactor())
				* Configuration.INSTANCE.getPacketRate();
		
		super.updateSleepTime(slowedRate);
	}

}
