package demostration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;


public class Displayer {
	private static Calendar calendar = Calendar.getInstance();

	private static final Pattern SPACE = Pattern.compile("\t");

	public static void main(String[] args) {
		String users = "[\"a\",\"b\",\"c\",\"d\"]";
//		JSONObject userJson = JSON.parseObject(users);
		JSONArray ua = JSON.parseArray(users);
		for(int i=0;i<ua.size();i++){
			System.out.println(ua.get(i));
		}
			
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
