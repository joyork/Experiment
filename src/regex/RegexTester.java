package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexTester {

	public static void main(String[] args) {
//		String input = "a234bcde98fg";
//		String pattern = "\\d{2}";
		String input = "/admin/poilist/233edit";
		String pattern = "^/admin/poilist/[^/]*$";
		Pattern p = Pattern.compile(pattern);
		Matcher match = p.matcher(input);
		while (match.find()) {
			String con = match.group();
			System.out.println(con); 
		}
	
//		String pattern = "$(<dd itemprop=\"softwareVersion\">)";
//		String content = "<dt>当前版本：</dt><dd itemprop=\"softwareVersion\">3.3.0</dd>";
//		String result = RegexTester.getTextBySelector(pattern, content);
//		System.out.println(result);
	
	}
	
	public static String parseByPattern(String pattern, String content){
		String result = "";
		Pattern p = Pattern.compile(pattern);
		Matcher match = p.matcher(content);
		while (match.find()) {
			
			result = match.group(1);
		}
		return result;
	}
	
	public static String getTextBySelector(String patternStr, String content) {
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

		// 去除无endtag的干扰
		content = content.replaceAll(startTag + "[^<>]*/>", "");
		// tag的起始支持正则
		int pos = -1;
		Matcher matcher = Pattern.compile(tagSelect).matcher(content);
		if (matcher.find()) {
			pos = matcher.end();
		}
		if (pos < 0) {
			return "";
		}
		int from = pos;
		// 为了和循环中的+1一致
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
}
