package Decoder;

import java.util.ArrayList;
import java.util.List;

import Common.Packet;

/**
 * Internal buffer for the decoder. Has a max size defined in the Configuration
 * file. Also maintains the number of dropped packets due to buffer overflow.
 * @author Felix
 *
 */
public enum DecoderBuffer {
	INSTANCE;

	private List<Packet> _packetBuffer;
	private long _numDroppedPackets;

	private DecoderBuffer() {
		this._packetBuffer = new ArrayList<Packet>();
		this._numDroppedPackets = 0;
	}

	/**
	 * Adds 1 packet to the buffer. If the buffer is full at the moment the
	 * current packet will be dropped.
	 * 
	 * @param p
	 */
	public synchronized void addPacket(Packet p) {
		if (this._packetBuffer.size() < Common.Configuration.INSTANCE
				.getMaxBufferSize()) {
			this._packetBuffer.add(p);
		} else {
			++this._numDroppedPackets;
		}
	}

	public synchronized Packet popPacket() {
		Packet p = null;
		if (this._packetBuffer.size() > 0) {
			p = this._packetBuffer.get(0);
			this._packetBuffer.remove(0);
		}

		return p;
	}

	public synchronized int getBufferSize() {
		return this._packetBuffer.size();
	}

	public synchronized long getNumDroppedPackets() {
		return this._numDroppedPackets;
	}
}
