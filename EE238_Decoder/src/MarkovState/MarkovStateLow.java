package MarkovState;

import Common.Configuration;

/**
 * Buffer is in LOW state. Slow down the output rate.
 * 
 * @author Felix
 * 
 */
public class MarkovStateLow extends AbstractMarkovState {

	/**
	 * Buffer is in the LOW state. Decrease the output rate.
	 */
	@Override
	public void updateSleepTime() {
		// Same as Rate - (Rate * Attenuation Factor)
		double slowedRate = (1.0 - Configuration.INSTANCE.getAttenuationFactor())
				* Configuration.INSTANCE.getPacketRate();

		super.updateSleepTime(slowedRate);
	}
}
