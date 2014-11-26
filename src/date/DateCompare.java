package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateCompare {
	
	private static Calendar calendar = Calendar.getInstance();
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String todayStr = sdf.format(today);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -365); 
		Date beday = cal.getTime();
		String before = sdf.format(beday);
		
		System.out.println(todayStr);
		System.out.println(before); 
		
		System.out.println("is same day:"+isSameDay(today, beday));
		System.out.println("is same date:"+isSameDate(today, beday));
		
		try {
			Date zeroToday = sdf.parse(todayStr);
			System.out.println(zeroToday.before(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断两个日期是否同日，不一定同年、同月
	 * 
	 * @param day
	 * @param compareDay
	 * @return
	 */
	public static boolean isSameDay(Date day, Date compareDay) {
		synchronized (calendar) {
			calendar.setTime(day);
			int day1 = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.setTime(compareDay);
			int day2 = calendar.get(Calendar.DAY_OF_MONTH);
			return day1 == day2;
		}
	}
	
	/**
	 * 判断两个日期是否 同年同月同日
	 * 
	 * @param day
	 * @param compareDay
	 * @return
	 */
	public static boolean isSameDate(Date day, Date compareDay) {
		synchronized (calendar) {
			calendar.setTime(day);
			int day1 = calendar.get(Calendar.DAY_OF_YEAR);
			int year1 = calendar.get(Calendar.YEAR);
			calendar.setTime(compareDay);
			int day2 = calendar.get(Calendar.DAY_OF_YEAR);
			int year2 = calendar.get(Calendar.YEAR);
			System.out.println(year1+" vs "+year2); 
			System.out.println(day1+" vs "+day2); 
			return year1 == year2 && day1 == day2;
		}
	}
}
