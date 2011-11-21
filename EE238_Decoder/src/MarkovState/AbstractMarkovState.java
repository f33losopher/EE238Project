package MarkovState;

import Common.Configuration;

public abstract class AbstractMarkovState implements IMarkovState {

	protected void updateSleepTime(double newRate) {
		double sleepTimeMilliSec = 1000 / newRate;
		long millisec = (long) sleepTimeMilliSec;
		double sleepTimeNanoSec = (sleepTimeMilliSec - (double) millisec) * 1000000;
		int nanosec = (int) sleepTimeNanoSec;

		Configuration.INSTANCE.getDecoderSleepTime().setMilliSec(millisec);
		Configuration.INSTANCE.getDecoderSleepTime().setNanoSec(nanosec);
	}
}
