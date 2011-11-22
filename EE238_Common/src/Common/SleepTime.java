package Common;

/**
 * SleepTime controls the rate of output. This is the simple mechanism to 
 * coordinate the frequency of events.
 * @author Felix
 *
 */
public class SleepTime {

	private long _millisec;
	private int _nanosec;

	public SleepTime(long m, int n) {
		this._millisec = m;
		this._nanosec = n;
	}
	
	public SleepTime(double dataRate)
	{
		setSleepTime(dataRate);
	}

	/**
	 * Converts a double dataRate (Packets/second) into respective millisconds
	 * and nanoseconds
	 * @param dataRate
	 */
	public void setSleepTime(double dataRate)
	{
		double sleepTimeMilliSec = 1000 / dataRate;
		this._millisec = (long) sleepTimeMilliSec;
		double sleepTimeNanoSec = (sleepTimeMilliSec - this._millisec) * 1000000;
		this._nanosec = (int) sleepTimeNanoSec;	
	}
	
	public void setMilliSec(long m) {
		this._millisec = m;
	}

	public long getMilliSec() {
		return this._millisec;
	}

	public void setNanoSec(int n) {
		this._nanosec = n;
	}

	public int getNanoSec() {
		return this._nanosec;
	}
}
