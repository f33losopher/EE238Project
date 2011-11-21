package Common;

public class SleepTime {

	private long _millisec;
	private int _nanosec;

	public SleepTime(long m, int n) {
		_millisec = m;
		_nanosec = n;
	}
	
	public SleepTime(double dataRate)
	{
		setSleepTime(dataRate);
	}

	public void setSleepTime(double dataRate)
	{
		double sleepTimeMilliSec = 1000 / dataRate;
		_millisec = (long) sleepTimeMilliSec;
		double sleepTimeNanoSec = (sleepTimeMilliSec - (double) _millisec) * 1000000;
		_nanosec = (int) sleepTimeNanoSec;	
	}
	
	public void setMilliSec(long m) {
		_millisec = m;
	}

	public long getMilliSec() {
		return _millisec;
	}

	public void setNanoSec(int n) {
		_nanosec = n;
	}

	public int getNanoSec() {
		return _nanosec;
	}
}
