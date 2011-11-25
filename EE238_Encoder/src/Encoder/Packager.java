package Encoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import Common.Configuration;
import Common.Packet;

/**
 * Takes the CIF file and packages the data to be sent to the 
 * network. Sends each packet at the rate specified in the
 * Configuration file.
 * 
 * @author Felix
 *
 */
public class Packager {
	private DatagramSocket _datagramSocket;
	private DatagramPacket _datagramPacket;
	private CIFFile _cifFile;
	private List<Packet> _packets;

	public Packager() throws IOException {
		this._packets = new ArrayList<Packet>();
		this._cifFile = new CIFFile();
		this._datagramSocket = new DatagramSocket();

		createPackets();
	}

	private void createPackets() {
		int bytesRemaining = this._cifFile.getFileSize();
		int packetSize = Common.Configuration.INSTANCE.getPacketSize();
		int index = 0;

		while (bytesRemaining > 0) {
			this._packets.add(new Packet(this._cifFile.getFileByteArray(), index));

			index += packetSize;
			bytesRemaining -= packetSize;
		}
	}

	public void sendFile() throws IOException, InterruptedException {
		for (Packet p : this._packets) {
			this._datagramPacket = new DatagramPacket(p.getPacket(),
					p.getPacketSize(),
					InetAddress.getByAddress(Configuration.INSTANCE
							.getDecoderAddr()),
					Common.Configuration.INSTANCE.getPort());

			this._datagramSocket.send(this._datagramPacket);
	
			Thread.sleep(Configuration.INSTANCE.getEncoderSleepTime().getMilliSec(),
					Configuration.INSTANCE.getEncoderSleepTime().getNanoSec());
		}
	}
}
