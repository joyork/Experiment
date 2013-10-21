package utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 3);
		c.set(Calendar.MINUTE, 30);
		c.set(Calendar.SECOND, 0);
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(date)); 
		
		Time t = new Time(000000);
		
		System.out.println(t.toGMTString()); 
	}
}
