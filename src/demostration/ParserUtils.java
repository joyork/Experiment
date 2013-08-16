package demostration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 解析使用的工具方�?
 * @author dongliu
 *
 */
public class ParserUtils {

	private static final String replaceSep = " -> ";
	private static final String regexSep = "\n";
	private static final String selectorStart = "$(";
	private static final String selectorEnd = ")";
	private static final String andSep = " and ";
	private static final String orSep = " or ";

	private static final String hrefStr = "<a[^<>]+href=(?:'|\")?([^\\s'\"<>]+)(?:'|\")?[^<>]*>";
	private static final Pattern hrefPattern = Pattern.compile(hrefStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	//新手宝典中用于匹配一个图片组的正�?
	private static final String imgGroupStr = "<p>[^<>]*\\s<img[^<>]*\\ssrc=(?:'|\")?([^\\s'\"<>]+)(?:'|\")?[^<>]*>";
	private static final Pattern imgGroupPattern = Pattern.compile(imgGroupStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	
	private static final String recommendIds = "#\\d*#";
	private static final Pattern recommendIdsPattern = Pattern.compile(recommendIds, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	
	private static final String imgStr = "<img[^<>]*\\ssrc=(?:'|\")?([^\\s'\"<>]+)(?:'|\")?[^<>]*>";
	private static final Pattern imgPattern = Pattern.compile(imgStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	private static final String baseStr = "<base[^<>]+href=(?:'|\")?([^\\s<>'\"]+)(?:'|\")?[^<>]*>";
	private static final Pattern basePattern = Pattern.compile(baseStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	private static final String fullHrefStr = "<a[^<>]+href=(?:'|\")?([^\\s'\"<>]+)(?:'|\")?[^<>]*>(.*?)</a>";
	private static final Pattern fullHrefPattern = Pattern.compile(fullHrefStr, Pattern.DOTALL
			| Pattern.CASE_INSENSITIVE);

	private static final Pattern macroPattern = Pattern.compile("\\$\\{([^}]+)\\}");

	public static final Set<String> topDomainSet = new HashSet<String>();
	static {
		topDomainSet.add("com");
		topDomainSet.add("cn");
		topDomainSet.add("net");
		topDomainSet.add("us");
		topDomainSet.add("org");
		topDomainSet.add("edu");
		topDomainSet.add("gov");
		topDomainSet.add("cc");
	}

	/**
	 * 解析表达式序列，获得指定的�?.
	 * 表达式第�?��认为是获取数据，其后的认为是过滤.
	 * @param patternStr
	 * @param content
	 * @return
	 */
	public static String getRegexValue(String patternStr, String content) {
		if (StringUtils.isEmpty(patternStr) || StringUtils.isEmpty(content)) {
			return "";
		}
		String[] regexs = patternStr.split(regexSep);
		String valueRegexs = regexs[0].trim();
		String result = "";
		String[] valueRegexArray = valueRegexs.split(orSep);
		for (String valueRegex : valueRegexArray) {
			String s = getRegexValueInner(valueRegex, content);
			if(StringUtils.isNotEmpty(s)){
				result = s;
				break;
			}
		}
		if (StringUtils.isEmpty(result)) {
			return "";
		}
		// 处理删除/替换正则
		for (int i = 1; i < regexs.length; i++) {
			String regexStr = regexs[i].trim();
			if (StringUtils.isBlank(regexStr)) {
				continue;
			}
			String[] patterns = regexStr.split(replaceSep);
			if (StringUtils.isBlank(patterns[0])) {
				continue;
			}
			Pattern pattern = Pattern.compile(patterns[0].trim(), Pattern.DOTALL);
			Matcher matcher = pattern.matcher(result);
			if (patterns.length == 1) {
				result = matcher.replaceAll("");
			} else {
				result = matcher.replaceAll(patterns[1].trim());
			}
		}
		return result;
	}

	/**
	 * 解析包含and连接符的，表达式解析结果
	 * @param valueRegex
	 * @param content
	 * @return
	 */
	private static String getRegexValueInner(String valueRegexs, String content){
		StringBuilder sb = new StringBuilder();
		String[] valueRegexArray = valueRegexs.split(andSep);
		for (String valueRegex : valueRegexArray) {
			valueRegex = valueRegex.trim();
			String result;
			if (valueRegex.startsWith(selectorStart) && valueRegex.endsWith(selectorEnd)) {
				result = getTextBySelector(valueRegex, content);
			} else {
				result = getTextByRegex(valueRegex, content);
			}
			if (result != null) {
				sb.append(result);
			}
		}
		return sb.toString();
	}

	/**
	 * 解析�?��表达式提取的�?
	 * @param patternStr
	 * @param content
	 * @return
	 */
	private static String getTextByRegex(String patternStr, String content) {
		if (StringUtils.isEmpty(patternStr) || StringUtils.isEmpty(content)) {
			return "";
		}
		Pattern pattern = Pattern.compile(patternStr, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return matcher.group(1);
		}
		return "";
	}

	/**
	 * 解析�?��selector提取的�?.
	 * selector使用$()标记，区分大小写，可以是正则。如$(<div id=content>), $(<div [^>]+content[^>]+>�?.
	 * @param patternStr
	 * @param content
	 * @return
	 */
	private static String getTextBySelector(String patternStr, String content) {
		if (StringUtils.isEmpty(content)) {
			return "";
		}
		String tagSelect = StringUtils.substring(patternStr, 2, -1);
		if (!tagSelect.startsWith("<")) {
			return "";
		}
		String tag = StringUtils.substringBefore(tagSelect, " ").substring(1);
		tag = StringUtils.substringBefore(tag, ">");
		String startTag = "<" + tag;
		String endTag = "</" + tag + ">";
		if (StringUtils.isEmpty(tagSelect)) {
			return "";
		}

		// 去除无endtag的干�?
		content = content.replaceAll(startTag + "[^<>]*/>", "");
		// tag的起始支持正�?
		int pos = -1;
		Matcher matcher = Pattern.compile(tagSelect).matcher(content);
		if (matcher.find()) {
			pos = matcher.end();
		}
		if (pos < 0) {
			return "";
		}
		int from = pos;
		// 为了和循环中�?1�?��
		pos--;
		int depth = 1;
		while (depth > 0) {
			int startTagPos = content.indexOf(startTag, pos + 1);
			int endTagPos = content.indexOf(endTag, pos + 1);
			if (endTagPos < 0) {
				return "";
			}
			if (startTagPos > endTagPos || startTagPos < 0) {
				pos = endTagPos;
				depth--;
			} else if (startTagPos > 0) {
				pos = startTagPos;
				depth++;
			}
		}
		return content.substring(from, pos);

	}

	/**
	 * 抽取�?格式化日�?
	 * 认为日期格式字符串的�?���?��，是simple date formate的格�?
	 * @param pubTimePattern
	 * @param content
	 * @return
	 */
	public static Date getDateValue(String datePatternStr, String content) {
		if (StringUtils.containsNone(datePatternStr, regexSep)) {
			return null;
		}
		String dateRegexPatternStr = StringUtils.substringBeforeLast(datePatternStr, regexSep).trim();
		String dateFormateStr = StringUtils.substringAfterLast(datePatternStr, regexSep).trim();
		String dateStr = getRegexValue(dateRegexPatternStr, content);
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		dateStr = dateStr.trim();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormateStr);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取url的主�?
	 * @param urlStr
	 * @return
	 */
	public static String getDomainByUrl(String urlStr) {
		if (StringUtils.isEmpty(urlStr)) {
			return null;
		}
		urlStr = urlStr.replace("http://", "");
		urlStr = urlStr.replace("https://", "");
		urlStr = urlStr.split("/")[0];
		String[] strs = urlStr.split("\\.");
		if (strs.length <= 1) {
			return null;
		}
		String domain = strs[strs.length - 1];
		boolean flag = true;
		for (int i = strs.length - 2; flag && i >= 0; i--) {
			if (!topDomainSet.contains(strs[i])) {
				flag = false;
			}
			domain = strs[i] + "." + domain;
		}
		return domain;
	}

	/**
	 * 解析出内容中�?a>标签中的URL列表.
	 * @param content
	 * @param selfUrl
	 * @return
	 */
	public static Set<String> getUrlList(String content, String selfUrl) {
		return getUrlListByNum(content, selfUrl, -1);
	}

	/**
	 * 解析出内容中�?a>标签中的URL列表.
	 * @param content
	 * @param baseUrl
	 * @param maxUrlNum �?��只解析maxUrlNum个URL, -1代表不限�?
	 * @return
	 */
	public static Set<String> getUrlListByPattern(String urlPattern, String content, String baseUrl, int maxUrlNum) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		Pattern pattern;
		if (StringUtils.isNotEmpty(urlPattern)) {
			pattern = Pattern.compile(urlPattern, Pattern.DOTALL);
		} else {
			pattern = hrefPattern;
		}
		Matcher matcher = pattern.matcher(content);
		Set<String> urlList = new LinkedHashSet<String>();
		int count = 0;
		while (matcher.find()) {
			String url = matcher.group(1);
			url = joinUrl(baseUrl, url);
			urlList.add(url);
			count++;
			if (maxUrlNum > 0 && count >= maxUrlNum) {
				break;
			}
		}
		if (urlList.isEmpty()) {
			return null;
		}
		return urlList;
	}
	/**
	 * 根据正则, 获取�?��匹配的内�?
	 * @param patternStr
	 * @param content
	 * @param maxNum
	 * @return
	 */
	public static List<String> getMatcherListByPattern(String patternStr, String content, int maxNum){
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		Pattern pattern;
		if (StringUtils.isNotEmpty(patternStr)) {
			pattern = Pattern.compile(patternStr, Pattern.DOTALL);
		} else {
			return null;
		}
		Matcher matcher = pattern.matcher(content);
		List<String> matchList = new ArrayList<String>();
		int count = 0;
		while (matcher.find()) {
			String ms = matcher.group(1);
			if(!matchList.contains(ms)){
				matchList.add(ms);
			}
			count++;
			if (maxNum > 0 && count >= maxNum) {
				break;
			}
		}
		return matchList;
	}

	/**
	 * 解析出内容中�?a>标签中的URL列表.
	 * @param content
	 * @param baseUrl
	 * @param maxUrlNum �?��只解析maxUrlNum个URL, -1代表不限�?
	 * @return
	 */
	public static Set<String> getUrlListByNum(String content, String baseUrl, int maxUrlNum) {
		return getUrlListByPattern(null, content, baseUrl, maxUrlNum);
	}

	/**
	 * 解析出URL列表，同时解析出URL的文�?
	 * @param paperUrlPattern
	 * @param selectedContent
	 * @param baseUrl
	 * @param i
	 * @return
	 */
	public static Map<String, String> getUrlMap(String urlPattern, String content, String baseUrl, int maxUrlNum) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		Pattern pattern;
		if (StringUtils.isNotEmpty(urlPattern)) {
			pattern = Pattern.compile(urlPattern, Pattern.DOTALL);
		} else {
			pattern = fullHrefPattern;
		}
		Matcher matcher = pattern.matcher(content);
		Map<String, String> urlMap = new LinkedHashMap<String, String>();
		int count = 0;
		while (matcher.find()) {
			String url = matcher.group(1);
			url = joinUrl(baseUrl, url);
			String title = matcher.group(2);
			title = title.trim();
			urlMap.put(url, title);
			count++;
			if (maxUrlNum > 0 && count >= maxUrlNum) {
				break;
			}
		}
		if (urlMap.isEmpty()) {
			return null;
		}
		return urlMap;
	}

	/**
	 * 拼接URL.
	 * @param url
	 * @param uri
	 * @return
	 */
	public static String joinUrl(String url, String uri) {
		if (StringUtils.isEmpty(uri) || StringUtils.isEmpty(url) || url.indexOf("/", 8) < 0) {
			return uri;
		}
		// http�?��，无�?���?
		if (uri.startsWith("http") || uri.startsWith("HTTP")) {
			return uri;
		}
		// /�?��，绝对位�?
		if (uri.startsWith("/")) {
			int pos = url.indexOf("/", 8);
			return url.substring(0, pos) + uri;
		}
		// ?�??，相对于cgi
		if (uri.startsWith("?")){
			url = StringUtils.substringBefore(url, "?");
			return url + uri;
		}
		// �?�?��，相对位�?
		// ./ ../处理
		uri = StringUtils.removeStart(uri, "./");
		url = StringUtils.substringBeforeLast(url, "/");
		while (uri.startsWith("../")) {
			uri = StringUtils.removeStart(uri, "../");
			url = StringUtils.substringBeforeLast(url, "/");
		}
		return url + "/" + uri;
	}

	/**
	 * 替换图片地址为绝对地�?
	 * @param url
	 * @param content
	 */
	public static String replaceImgUrl(String url, String content) {
		if (StringUtils.containsNone(content, "<img") || StringUtils.containsNone(content, "<IMG")
				|| StringUtils.isEmpty(url)) {
			return content;
		}
		Matcher matcher = imgPattern.matcher(content);
		StringBuilder sb = new StringBuilder();
		int startPos = 0, endPos = 0;
		while (matcher.find()) {
			String imgUrl = matcher.group(1);
			endPos = matcher.start(1);
			sb.append(content.substring(startPos, endPos));
			startPos = matcher.end(1);
			imgUrl = joinUrl(url, imgUrl);
			sb.append(imgUrl);
		}
		sb.append(content.substring(startPos));
		return sb.toString();
	}

	/**
	 * 获取base标签中的url.
	 * @param content
	 * @return
	 */
	public static String getBaseUrl(String content) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		Matcher matcher = basePattern.matcher(content);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	/**
	 * 获得图片列表.
	 * @param content
	 * @return
	 */
	public static List<String> getImgList(String content) {
		List<String> imgList = null;
		Matcher matcher = imgPattern.matcher(content);
		while (matcher.find()) {
			String imgUrl = matcher.group(1);
			if (imgList == null) {
				imgList = new ArrayList<String>();
			}
			imgList.add(imgUrl);
		}
		return imgList;
	}

	/**
	 * URL变化，主要是解析其中的变量，主要是日期之类的URL中的变化部分.
	 * ${date?yyyy-MM/dd}
	 * @param paperUrl
	 * @return
	 */
	public static String transfromURL(String url) {
		if (StringUtils.isEmpty(url) || !url.contains("${")) {
			return url;
		}
		Matcher matcher = macroPattern.matcher(url);
		StringBuffer sb = new StringBuffer();
		int begin = 0;
		int end = 0;
		while (matcher.find()) {
			begin = matcher.start();
			sb.append(url.substring(end, begin));
			end = matcher.end();
			String macro = matcher.group(1);
			String type = StringUtils.substringBefore(macro, "?");
			if ("date".equals(type)) {
				// 日期变量
				String sdfStr = StringUtils.substringAfter(macro, "?");
				SimpleDateFormat sdf = new SimpleDateFormat(sdfStr);
				String dateStr = sdf.format(new Date());
				sb.append(dateStr);
			}
		}
		sb.append(url.substring(end));
		return sb.toString();
	}

	/**
	 * 获取url的域名部�?
	 * @param url
	 * @return
	 */
	public static String getFullDomainByUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		url = url.replace("http://", "");
		url = url.replace("https://", "");
		return StringUtils.substringBefore(url, "/");
	}

	public static Pattern getImgPattern() {
		return imgPattern;
	}
	
	public static Pattern getImgGroupPattern(){
		return imgGroupPattern;
	}
	
	public static Pattern getRecommendIdsPattern(){
		return recommendIdsPattern;
	}

}
