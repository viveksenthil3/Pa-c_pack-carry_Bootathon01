package training;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

public class Date {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(dateFormat.format(cal.getTime()));
		
		cal.add(Calendar.DATE, -30);
		System.out.println(dateFormat.format(cal.getTime()));
		
	}

}
