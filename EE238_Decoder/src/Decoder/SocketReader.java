package Decoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import Common.Configuration;
import Common.Packet;

public class SocketReader extends Thread {
	boolean _readSocket;
	DatagramSocket _datagramSocket;
	DatagramPacket _datagramPacket;
	byte[] _readBuffer;

	public SocketReader() throws SocketException {
		super("SocketReader");
		_readSocket = true;

		_datagramSocket = new DatagramSocket(Configuration.INSTANCE.getPort());
		_datagramSocket.setSoTimeout(Configuration.INSTANCE.getSocketTimeout());
		_readBuffer = new byte[Configuration.INSTANCE.getPacketSize()];
		_datagramPacket = new DatagramPacket(_readBuffer, _readBuffer.length);
		start();
	}

	public void run() {
		while (_readSocket) {
			try {
				_datagramSocket.receive(_datagramPacket);
				Packet p = new Packet(_readBuffer, 0);
				DecoderBuffer.INSTANCE.addPacket(p);

			} catch (IOException e) {
			}
		}
	}

	public void stopReadSocket() {
		_readSocket = false;
	}
}
