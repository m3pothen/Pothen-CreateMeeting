package src;

public class Date {
	public int month;
	public int day;
	public int year;
	
	public Date(int m, int d, int y) {
		month = m;
		day = d;
		year = y;	
	}
	public String printString() {
		return month + "/" + day + "/" + year;
	}
	
}
