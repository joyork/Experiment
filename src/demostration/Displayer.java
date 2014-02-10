package demostration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.DataFormatException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;


public class Displayer {
	private static Calendar calendar = Calendar.getInstance();
	
	public static void main(String[] args) {
		
		Map<String, String> jsonmap = new HashMap<String, String>();
		jsonmap.put("pushtype", "doc");
		jsonmap.put("id", "TXWQSDGWS");
		jsonmap.put("content", "森林击败热火");
		JSONObject json = JSONObject.fromObject(jsonmap);

		String jsontr = json.toString();
		System.out.println(jsontr); 
		
		float f = 2.423f;
		String fstr = new Float(f).toString();
		BigDecimal r = new BigDecimal(fstr);
		System.out.println(r); 
		
		
		String engdate = "January 23, 2013";
		DateFormat engdateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
		try {
			Date ed = engdateFormat.parse(engdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
				
		String email = "baiyw@sina.com";
		System.out.println(email.matches("[^@]+@.*.[\\w]+"));
		
		Date d = new Date(1388073600000l);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(d));  
		Date dd;
		try {
			dd = dateFormat.parse("2013-03-13 00:00:00");
			System.out.println("dd after now:"+dd.after(new Date())); 
			System.out.println(dd.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content = "t>当前版本：</dt><dd itemprop=\"softwareVersion\">因设备而异</dd><dt ite";
		String cVersion = ParserUtils.getRegexValue("$(<dd itemprop=\"softwareVersion\">)", content);
		System.out.println(cVersion);
//		int dayspan = getDaySpan(1361030400l,1362067200l);
//		System.out.println(dayspan);
//		
//		List<String> dayList = parsePeriod(1361030400l, 1362067200l);
//		for (String day : dayList) {
//			System.out.println(day); 
//		}
    }

	
	public static  String strparse(String url,String uri){
		uri = StringUtils.removeStart(uri, "./");
		url = StringUtils.substringBeforeLast(url, "/");
		while (uri.startsWith("../")) {
			uri = StringUtils.removeStart(uri, "../");
			url = StringUtils.substringBeforeLast(url, "/");
		}
		return url + "/" + uri;
	}
	private static List<String> parsePeriod(Long start, Long end) {
		synchronized (calendar) {
			if (start == null || end == null)
				return new ArrayList<String>(0);
			calendar.setTimeInMillis(start * 1000L);
			Date first = calendar.getTime();
			calendar.setTimeInMillis(end * 1000L + 5);
			Date last = calendar.getTime();
			List<String> list = new ArrayList<String>();
			Date cur = first;
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
			calendar.setTime(cur);
			while (cur.before(last)) {
				list.add(dateFormat.format(cur));
				calendar.add(Calendar.DATE, 1);
				cur = calendar.getTime();
			}
			return list;
		}
	}

	private static int getDaySpan(Long start, Long end) {
		long daylong = 86400;

		return (int)((end-start)/daylong);
	}
}
