package MarkovState;

import Common.Configuration;

/**
 * Abstract class for all Markov States. Implements IMarkovState
 * 
 * @author Felix
 * 
 */
public abstract class AbstractMarkovState implements IMarkovState {

	protected void updateSleepTime(double newRate) {
		Configuration.INSTANCE.getDecoderSleepTime().setSleepTime(newRate);
	}
}
