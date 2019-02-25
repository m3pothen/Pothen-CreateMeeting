package src;

import java.text.DecimalFormat;

public class Time {
	public int hour;
	public int minute;
	public Time(int h, int m) {
		hour = h;
		minute = m;
	}
	public String printString() {
		DecimalFormat f = new DecimalFormat("00");
		return f.format(hour) + ":" + f.format(minute);
		
	}
	
}
