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

		// If everything is normal, check to see if we need to transition
		// into another action state.
		if (_action == ACTION.NORMAL) {
			if (bufferSize > Configuration.INSTANCE.getUpperThreshold()) {
				_prevState = _markovStateHigh;
				_action = ACTION.RECOVER;
				return _markovStateHigh;
			} else if (bufferSize < Configuration.INSTANCE.getLowerThreshold()) {
				_prevState = _markovStateLow;
				_action = ACTION.RECOVER;
				return _markovStateLow;
			} else {
				_prevState = _markovStateNormal;
				_action = ACTION.NORMAL;
				return _markovStateNormal;
			}
		} else {
			// In Recovery mode, so we want to get back near the ideal
			// buffer size.
			if (_prevState == _markovStateHigh) {
				if (bufferSize < (_idealBuffer + _idealBufferMargin)) {
					_prevState = _markovStateNormal;
					_action = ACTION.NORMAL;
					return _markovStateNormal;
				} else {
					return _markovStateHigh;
				}
			} else {
				if (bufferSize > (_idealBuffer - _idealBufferMargin)) {
					_prevState = _markovStateNormal;
					_action = ACTION.NORMAL;
					return _markovStateNormal;
				} else {	
					return _markovStateLow;
				}
			}
		}
	}
}
