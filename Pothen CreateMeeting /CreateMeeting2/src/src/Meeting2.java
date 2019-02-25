package src;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/*
Author: Melissa Pothen
*/

public class Meeting2 {
	
	public static HashMap<Date, Event> scheduler;
	
	public static void main(String[] args) {
		scheduler = new HashMap<Date, Event>();
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter 1 to schedule event, 2 to schedule recurring meeting, 3 to show Calendar and 4 close Scheduler: ");
		int choice = sc.nextInt();
		while(choice != 4) {
			if(choice == 1) { //single event
				
				System.out.println("Enter date in MM-DD-YYYY format:");
				String date = sc.next();
				Date d = convertDate(date);
				
				System.out.println("Enter start and end time in following format HH:MM,HH:MM: ");
				String time = sc.next();
				Time[] t = convertTime(time);
				Event e = new Event(d, t[0], t[1]);
				
				scheduleEvent(d,e);
						
				System.out.println("Your meeting has been scheduled.");

			} else if(choice == 2) { //recurring meeting
				
				System.out.println("Enter start date in MM-DD-YYYY format:");
				String start = sc.next();
				Date date = convertDate(start);
				
				System.out.println("Enter end date in MM-DD-YYYY format:");
				String end = sc.next();
				
				System.out.println("Enter day to schedule: ");
				String day = sc.next();
				
				System.out.println("Enter start and end time in following format HH:MM,HH:MM: ");
				String time = sc.next();
				Time[] t = convertTime(time); //gives start and end time as an array			
				
				Event e = new Event(date, t[0], t[1]);				
				int d = convertDay(day);
				int occur = countOccurrences(start,end,d,e);
				
				System.out.println("Your meetings have been scheduled. You will have " + occur + " meetings.");
				
			} else if(choice == 3) {
				
				showCalendar();
				
			} else {
				System.out.println("Invalid choice");
			}
			System.out.print("Enter 1 to schedule event, 2 to schedule recurring meeting, 3 to show Calendar and 4 close Scheduler: ");
		    choice = sc.nextInt();
		}	
		System.out.print("Scheduler closed");
		sc.close();
	}
				
	public static int countOccurrences(String start, String end, int day, Event event) {
		int occur = 0;
		
		//start date
		Calendar c1 = Calendar.getInstance();
		Date t = convertDate(start); 		
		c1.set(t.year, t.month-1, t.day); //{year,month,day}
		
		//end date
		Calendar c2 = Calendar.getInstance();
		Date t2 = convertDate(end);			
		c2.set(t2.year, t2.month-1, t2.day); //{year,month,day}

		while(c1.before(c2)) {
			if(c1.get(Calendar.DAY_OF_WEEK) == day) {
				occur++;
				//schedule event
				Date date = new Date(c1.get(Calendar.MONTH)+1, c1.get(Calendar.DATE), c1.get(Calendar.YEAR)); //constants for months start at 0
				scheduleEvent(date,event);
			}
			c1.add(Calendar.DATE, 1);
		}
		return occur;
	}
	public static Date convertDate(String s) { //format = MM-DD-YYYY
		if(!(s.matches("\\d{2}-\\d{2}-\\d{4}")))  {
			throw new IllegalArgumentException("Invalid Date");
		}		
		int month = Integer.parseInt(s.substring(0,2));
		int day =  Integer.parseInt(s.substring(3,5));
		int year = Integer.parseInt(s.substring(6));
		
		return new Date(month,day,year);	
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
	public static Time[] convertTime(String t){ //format = HH:MM,HH:MM
		if(!(t.matches("\\d{2}:\\d{2},\\d{2}:\\d{2}"))) {
			throw new IllegalArgumentException("Invalid Time");
		}
		int h1 = Integer.parseInt(t.substring(0,2));
		int m1 = Integer.parseInt(t.substring(3,5));
		int h2 = Integer.parseInt(t.substring(6,8));
		int m2 = Integer.parseInt(t.substring(9));
		
		Time[] arr = {new Time(h1,m1), new Time(h2,m2)};
		return arr;
	}
	public static void scheduleEvent(Date date, Event event) {
		scheduler.put(date, event);
	}
	public static void showCalendar() {
		for(Date d : scheduler.keySet()) {
			System.out.println("Date: " + d.printString() + ", Time: ["+ scheduler.get(d).printString() + "]");			
		}
		
	}
}

