package Encoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import Common.Packet;

public class Packager {
	private DatagramSocket _datagramSocket;
	private DatagramPacket _datagramPacket;
	private CIFFile _cifFile;
	private List<Packet> _packets;

	public Packager() throws IOException {
		_packets = new ArrayList<Packet>();
		_cifFile = new CIFFile();
		_datagramSocket = new DatagramSocket();

		createPackets();
	}

	private void createPackets() {
		int bytesRemaining = _cifFile.getFileSize();
		int packetSize = Common.Configuration.INSTANCE.getPacketSize();
		int index = 0;

		while (bytesRemaining > 0) {
			_packets.add(new Packet(_cifFile.getFileByteArray(), index));

			index += packetSize;
			bytesRemaining -= packetSize;
		}
	}

	public void sendFile() throws IOException, InterruptedException {
		for (Packet p : _packets) {
			_datagramPacket = new DatagramPacket(p.getPacket(),
					p.getPacketSize(), InetAddress.getLocalHost(),
					Common.Configuration.INSTANCE.getPort());

			_datagramSocket.send(_datagramPacket);
			
			Thread.sleep(Common.Configuration.INSTANCE.getSleepTime());
		}
	}
}
