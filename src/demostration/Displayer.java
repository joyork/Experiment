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
		String man = "满12减6;满30减10(在线支付专享)";
		String minusCon = "0";
		String minus = man ;
		if(!minus.equals("NoMinus")){
			int indexOfJian = minus.indexOf("减");
			if(minus.indexOf("满")!=-1){
				minusCon = minus.substring(minus.indexOf("满")+1, indexOfJian);
				
				int indexOfBlance = minus.indexOf("(");
				int indexOfSep = minus.indexOf(";");
				int endManJian = -1;
				if(indexOfSep>0 && indexOfBlance>0){
					endManJian = Math.min(indexOfSep, indexOfBlance);
				}else{
					endManJian = Math.max(indexOfSep, indexOfBlance);
				}
				if(endManJian != -1){
					minus = minus.substring(indexOfJian+1,endManJian);
				} else {
					minus = minus.substring(indexOfJian+1,minus.length());
				}
			}else{
				minus = "0";
			}
		}else{
			minus = "0";
		}
		System.out.println(minus); 
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
