package Common;

public class Packet {
	private byte[] _packet;
	
	public Packet(byte[] b, int begin)
	{
		int packetSize = Common.Configuration.INSTANCE.getPacketSize();
		_packet = new byte[packetSize];
		System.arraycopy(b, begin, _packet, 0, packetSize);
	}
	
	public byte[] getPacket()
	{
		return _packet;
	}
	
	public int getPacketSize()
	{
		return _packet.length;
	}
}
