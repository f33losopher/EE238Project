package Decoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Common.Configuration;
import Common.Packet;
import Log.Logger;

/**
 * SocketReader is a separate thread that listens to a port and reads
 * data as it arrives. Immediately sends the data to the DecoderBuffer.
 * @author Felix
 *
 */
public class SocketReader extends Thread {
	boolean _readSocket;
	DatagramSocket _datagramSocket;
	DatagramPacket _datagramPacket;
	byte[] _readBuffer;

	public SocketReader() throws SocketException {
		super("SocketReader");
		this._readSocket = true;

		this._datagramSocket = new DatagramSocket(Configuration.INSTANCE.getPort());
		this._datagramSocket.setSoTimeout(Configuration.INSTANCE.getSocketTimeout());
		this._readBuffer = new byte[Configuration.INSTANCE.getPacketSize()];
		this._datagramPacket = new DatagramPacket(this._readBuffer, this._readBuffer.length);
		start();
	}

	/**
	 * Continually reads any data that arrives at the socket.
	 */
	public void run() {
		while (this._readSocket) {
			try {
				this._datagramSocket.receive(this._datagramPacket);
				Packet p = new Packet(this._readBuffer, 0);
				DecoderBuffer.INSTANCE.addPacket(p);

			} catch (IOException e) {
				// Exceptionally Long Timeout. End of transmission.
				// Write the statistics
				try {
					Logger.getLogger().logStatistics();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void stopReadSocket() {
		this._readSocket = false;
	}
}
