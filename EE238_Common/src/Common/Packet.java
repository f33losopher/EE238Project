package Common;

/**
 * A single packet of data contains the raw byte sequence of data as well
 * as the accessor to the data.
 * @author Felix
 *
 */
public class Packet {
	private byte[] _packet;
	
	public Packet(byte[] b, int begin)
	{
		int packetSize = Common.Configuration.INSTANCE.getPacketSize();
		this._packet = new byte[packetSize];
		System.arraycopy(b, begin, this._packet, 0, packetSize);
	}
	
	public byte[] getPacket()
	{
		return this._packet;
	}
	
	public int getPacketSize()
	{
		return this._packet.length;
	}
}
