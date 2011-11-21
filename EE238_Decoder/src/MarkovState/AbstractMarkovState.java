package MarkovState;

import Common.Configuration;

public abstract class AbstractMarkovState implements IMarkovState {

	protected void updateSleepTime(double newRate) {
		
		Configuration.INSTANCE.getDecoderSleepTime().setSleepTime(newRate);
	}
}
