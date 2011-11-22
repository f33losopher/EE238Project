package MarkovState;

import Common.Configuration;

/**
 * Buffer is in the NORMAL state. Maintain the default output rate.
 * @author Felix
 *
 */
public class MarkovStateNormal extends AbstractMarkovState {

	/**
	 * Buffer is in the normal state. Maintain the default output rate.
	 */
	@Override
	public void updateSleepTime() {
		super.updateSleepTime(Configuration.INSTANCE.getPacketRate());
	}
}
