package MarkovState;

import Common.Configuration;
import Decoder.DecoderBuffer;

/**
 * Factory Object to get the current Markov State
 * @author Felix
 *
 */
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
	private IMarkovState _prevState = this._markovStateNormal;
	private ACTION _action = ACTION.NORMAL;

	public IMarkovState getMarkovState() {
		int bufferSize = DecoderBuffer.INSTANCE.getBufferSize();
		IMarkovState currentState;

		// If everything is normal, check to see if we need to transition
		// into another action state.
		if (this._action == ACTION.NORMAL) {
			if (bufferSize > Configuration.INSTANCE.getUpperThreshold()) {
				this._prevState = this._markovStateHigh;
				this._action = ACTION.RECOVER;
				currentState = this._markovStateHigh;
			} else if (bufferSize < Configuration.INSTANCE.getLowerThreshold()) {
				this._prevState = this._markovStateLow;
				this._action = ACTION.RECOVER;
				currentState = this._markovStateLow;
			} else {
				this._prevState = this._markovStateNormal;
				this._action = ACTION.NORMAL;
				currentState = this._markovStateNormal;
			}
		} else {
			// In Recovery mode, so we want to get back near the ideal
			// buffer size.
			if (this._prevState == this._markovStateHigh) {
				if (bufferSize < (_idealBuffer + _idealBufferMargin)) {
					this._prevState = this._markovStateNormal;
					this._action = ACTION.NORMAL;
					currentState = this._markovStateNormal;
				} else {
					currentState = this._markovStateHigh;
				}
			} else {
				if (bufferSize > (_idealBuffer - _idealBufferMargin)) {
					this._prevState = this._markovStateNormal;
					this._action = ACTION.NORMAL;
					currentState = this._markovStateNormal;
				} else {	
					currentState = this._markovStateLow;
				}
			}
		}
		
		return currentState;
	}
}
