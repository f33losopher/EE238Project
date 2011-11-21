package MarkovState;

import Common.Configuration;
import Decoder.DecoderBuffer;

public enum MarkovStateFactory {
	INSTANCE;

	enum ACTION {
		NORMAL, RECOVER
	}

	private final static int _idealBuffer = Configuration.INSTANCE
			.getMaxBufferSize() / 2;
	private final static int _idealBufferMargin = (int) ((Configuration.INSTANCE
			.getMaxBufferSize() / 2) * 0.1);

	private final MarkovStateHigh _markovStateHigh = new MarkovStateHigh();
	private final MarkovStateLow _markovStateLow = new MarkovStateLow();
	private final MarkovStateNormal _markovStateNormal = new MarkovStateNormal();
	private IMarkovState _prevState = _markovStateNormal;
	private ACTION _action = ACTION.NORMAL;

	public IMarkovState getMarkovState() {
		int bufferSize = DecoderBuffer.INSTANCE.getBufferSize();

		System.out.print("Buffer size: " + bufferSize + " ");
		System.out.print("Sleep milli: "
				+ Configuration.INSTANCE.getDecoderSleepTime().getMilliSec()
				+ " ");
		System.out.print("nano: "
				+ Configuration.INSTANCE.getDecoderSleepTime().getNanoSec()
				+ " ");

		// If everything is normal, check to see if we need to transition
		// into another action state.
		if (_action == ACTION.NORMAL) {
			if (bufferSize > Configuration.INSTANCE.getUpperThreshold()) {
				System.out.println("HIGH");

				_prevState = _markovStateHigh;
				_action = ACTION.RECOVER;
				return _markovStateHigh;
			} else if (bufferSize < Configuration.INSTANCE.getLowerThreshold()) {
				System.out.println("LOW");

				_prevState = _markovStateLow;
				_action = ACTION.RECOVER;
				return _markovStateLow;
			} else {
				System.out.println("NORMAL");

				_prevState = _markovStateNormal;
				_action = ACTION.NORMAL;
				return _markovStateNormal;
			}
		} else {
			// In Recovery mode, so we want to get back near the ideal
			// buffer size.
			if (_prevState == _markovStateHigh) {
				if (bufferSize < (_idealBuffer + _idealBufferMargin)) {
					System.out.println("BACK TO NORMAL");
					
					_prevState = _markovStateNormal;
					_action = ACTION.NORMAL;
					return _markovStateNormal;
				} else {
					System.out.println("HIGH");
					
					return _markovStateHigh;
				}
			} else {
				if (bufferSize > (_idealBuffer - _idealBufferMargin)) {
					System.out.println("BACK TO NORMAL");
					
					_prevState = _markovStateNormal;
					_action = ACTION.NORMAL;
					return _markovStateNormal;
				} else {
					System.out.println("LOW");
					
					return _markovStateLow;
				}
			}
		}
	}
}
