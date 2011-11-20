package Decoder;

import java.util.ArrayList;
import java.util.List;

import Common.Packet;

public enum DecoderBuffer {
	INSTANCE;

	private List<Packet> _packetBuffer;
	private long _numDroppedPackets;

	private DecoderBuffer() {
		_packetBuffer = new ArrayList<Packet>();
		_numDroppedPackets = 0;
	}

	/**
	 * Adds 1 packet to the buffer. If the buffer is full at the moment the
	 * current packet will be dropped.
	 * 
	 * @param p
	 */
	public synchronized void addPacket(Packet p) {
		if (_packetBuffer.size() < Common.Configuration.INSTANCE
				.getMaxBufferSize()) {
			_packetBuffer.add(p);
		} else {
			++_numDroppedPackets;
		}
	}

	public synchronized Packet popPacket() {
		Packet p = null;
		if (_packetBuffer.size() > 0) {
			p = _packetBuffer.get(0);
			_packetBuffer.remove(0);
		}

		return p;
	}

	public synchronized int getBufferSize() {
		return _packetBuffer.size();
	}

	public synchronized long getNumDroppedPackets() {
		return _numDroppedPackets;
	}
}
