package Common;

public class SleepTime {

	private long _millisec;
	private int _nanosec;

	public SleepTime(long m, int n) {
		_millisec = m;
		_nanosec = n;
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
