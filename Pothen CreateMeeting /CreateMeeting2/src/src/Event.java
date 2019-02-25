package src;

public class Event {
	public Date myDate;
	public Time startTime;
	public Time endTime;
	public Event(Date date, Time s, Time e) {
		myDate = date;
		startTime = s;
		endTime = e;
	}
	
	public String printString() {
		return startTime.printString() + "," + endTime.printString();	
	}
}

