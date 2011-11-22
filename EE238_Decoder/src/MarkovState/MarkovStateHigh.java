package MarkovState;

import Common.Configuration;

/**
 * Buffer is in HIGH state. Increase the output rate.
 * 
 * @author Felix
 * 
 */
public class MarkovStateHigh extends AbstractMarkovState {

	/**
	 * Buffer is in the HIGH state. Increase the output rate.
	 */
	@Override
	public void updateSleepTime() {
		// Same as Rate + (Rate * Attenuation Factor)
		double increasedRate = (1 + Configuration.INSTANCE
				.getAttenuationFactor())
				* Configuration.INSTANCE.getPacketRate();

		super.updateSleepTime(increasedRate);
	}

}
