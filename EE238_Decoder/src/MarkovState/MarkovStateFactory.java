package MarkovState;

import Common.Configuration;
import Decoder.DecoderBuffer;

public enum MarkovStateFactory {
	INSTANCE;

	private MarkovStateHigh _markovStateHigh = new MarkovStateHigh();
	private MarkovStateLow _markovStateLow = new MarkovStateLow();
	private MarkovStateNormal _markoveStateNormal = new MarkovStateNormal();

	public IMarkovState getMarkovState() {
		int bufferSize = DecoderBuffer.INSTANCE.getBufferSize();
		
		System.out.print("Buffer size: " + bufferSize + " ");
		System.out.print("Sleep milli: " + Configuration.INSTANCE.getDecoderSleepTime().getMilliSec() + " ");
		System.out.print("nano: " + Configuration.INSTANCE.getDecoderSleepTime().getNanoSec() + " ");

		if (bufferSize > Configuration.INSTANCE.getUpperThreshold()) {
			System.out.println("HIGH");
			return _markovStateHigh;
		} else if (bufferSize < Configuration.INSTANCE.getLowerThreshold()) {
			System.out.println("LOW");
			return _markovStateLow;
		} else {
			System.out.println("NORMAL");
			return _markoveStateNormal;
		}
	}
}
