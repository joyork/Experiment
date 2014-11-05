package demostration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
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

import utils.HttpUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;


public class Displayer {
	private static Calendar calendar = Calendar.getInstance();

	private static final Pattern SPACE = Pattern.compile("\t");

	public static void main(String[] args) {
		String mac = "998877665544";
		String upMac = mac.toUpperCase();
		char[] mcs = upMac.toCharArray();
		System.out.println(mcs); 
		String sepMac = ""+mcs[0]+mcs[1]+":"+
				mcs[2]+mcs[3]+":"+
				mcs[4]+mcs[5]+":"+
				mcs[6]+mcs[7]+":"+
				mcs[8]+mcs[9]+":"+
				mcs[10]+mcs[11];
		System.out.println(sepMac); 
		
		ArrayList<String> macList =  new ArrayList<String>();
		macList.add("998877665544");
		macList.add("8CBEBEF6760B");

		JSONArray array = new JSONArray();
		array.add("998877665544");
		array.add("8CBEBEF6760B");
		JSONObject object = new JSONObject();
		JSONObject wlmac = new JSONObject();
		wlmac.put("remove", array);
		object.put("whitelist_mac", wlmac);
		System.out.println(object.toJSONString().replace("\"", ""));
		System.out.println(object.toJSONString()); 
		System.out.println(object.toString()); 
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
