package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

/*
Author: Melissa Pothen

txt filepath: /Users/melissapothen/eclipse-workspace/Practice/CreateMeeting1/src/src/imported.txt

*/

public class Meeting {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename");
		String f = sc.next();
		String l = "";
		BufferedReader b;
		try {
			b = new BufferedReader(new FileReader(f));
			while((l = b.readLine())!=null) {
				String[] s = l.split(", ");
				String start = s[0];
				String end = s[1];
				int day = convertDay(s[2]);
				int occur = countOccurrences(start,end,day);
				System.out.println("You will have " + occur + " meetings from " + start + " to " + end + " every " + s[2]);
			}
		}catch (FileNotFoundException n) {
			// TODO Auto-generated catch block
		    n.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		sc.close();
	}
				
	public static int countOccurrences(String start, String end, int day) {
		int occur = 0;
		
		//start date
		Calendar c1 = Calendar.getInstance();
		int[] s = convertDate(start); //{year,month,day}
		c1.set(s[0],s[1]-1,s[2]); //constants for months start at 0
				
		//end date
		Calendar c2 = Calendar.getInstance();
		int[] e = convertDate(end); //{year,month,day}
		c2.set(e[0],e[1]-1,e[2]); //constants for months start at 0
		
		if(c1.after(c2)) {
			throw new IllegalArgumentException("Invalid Date Range");
		} else {		
			while(c1.before(c2)) {
				if(c1.get(Calendar.DAY_OF_WEEK) == day) {
					occur++;
				}
				c1.add(Calendar.DATE, 1);
			}
		}
		return occur;
	}
	public static int[] convertDate(String s) {	//format = YYYY-MM-DD
		if(!(s.matches("\\d{4}-\\d{2}-\\d{2}")))  {
			throw new IllegalArgumentException("Invalid Date");
		}		 
		int year = Integer.parseInt(s.substring(0,4));
		int month =  Integer.parseInt(s.substring(5,7));
		int day = Integer.parseInt(s.substring(8));		
		
		int[] date = {year,month,day};
		return date;	
	}
	public static int convertDay(String day) {	
		if(day.equals("Sunday")) {
			return 1;
		} else if(day.equals("Monday")) {
			return 2;
		} else if(day.equals("Tuesday")) {
			return 3;
		} else if(day.equals("Wednesday")) {
			return 4;
		} else if(day.equals("Thursday")) {
			return 5;
		} else if(day.equals("Friday")) {
			return 6;
		} else if(day.equals("Saturday")) {
			return 7;
		} else {
			throw new IllegalArgumentException("Invalid Day");
		}
	}
	
}

