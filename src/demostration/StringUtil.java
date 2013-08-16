package demostration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class StringUtil {
	public static void main(String[] args) {
//		List<String> strs = new ArrayList<String>();
//		strs.add("good");
//		strs.add("days");
//		strs.add("starud");
//		strs.add("ilkdf");
//		StringBuffer out = new StringBuffer();
//		int i=0;
//		for (String str : strs) {
//			if(i<3&& i<strs.size()){
//				out.append(str);
//			}
//			if(i<2 && i< strs.size()-1){
//				out.append(",");
//			}		
//			i++;
//		}
//		System.out.println(out);
		
//		System.out.println(show("good","tail")); 
		
		String[] array = new String[]{"one","two","three"};
		List<String> alist = StringUtil.asList(array);
		
		System.out.println("wode(°®µÄ)".length()); 
		int i = 9;
		System.out.println("2022"+(i+10)); 
		Date d = new Date();
		
		System.out.println(d.getTime()); 
	}
	private static String show(String a,String anchor){
		anchor = (anchor==null?"":"#"+anchor);
		return a+anchor;
	}

	private static String maxChineseSize(String orig, int maxSize) {
		String desStr = null;
		int i = 0;
		char[] ca = orig.toCharArray();
		int realLength = 0;
		int dbNum = 0;
		while (i < ca.length && realLength < maxSize * 2) {
			if (ca[i] < 255) {
				realLength += 1;
			} else {
				if (realLength == maxSize * 2 - 1) {
					break;
				}
				realLength += 2;
				dbNum++;
			}
			i++;
		}
		int delta = 1;
		if (dbNum < i && ca[i - 1] < 255) {
			delta = 2;
		}
		if (i < ca.length || realLength > maxSize * 2) {
			desStr = orig.substring(0, i - delta) + "..";
		} else {
			desStr = orig.substring(0, i);
		}
		return desStr;
	}

	/**
	 * @param orig
	 */
	private static String maxString(String orig, int maxSize) {
		Date sd = new Date();
		String newString = orig;

		int pace = 1;

		if (orig.matches("^[0-9a-zA-Z]*")) {
			pace *= 2;
			maxSize *= 2;
		}
		if (orig.length() > maxSize) {
			newString = orig.substring(0, maxSize - pace) + "..";
		}
		Date end = new Date();
		System.out.println("ÓÃÊ±£º" + (end.getTime() - sd.getTime()));
		return newString;
	}

	private static HanyuPinyinOutputFormat sformat = new HanyuPinyinOutputFormat();
	private static HanyuPinyinOutputFormat bformat = new HanyuPinyinOutputFormat();
	static {
		sformat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		sformat.setVCharType(HanyuPinyinVCharType.WITH_V);
		sformat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		bformat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		bformat.setVCharType(HanyuPinyinVCharType.WITH_V);
		bformat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	}

	private static void pinyin(String str) {
		StringBuilder word = new StringBuilder();
		StringBuilder bigword = new StringBuilder();
		StringBuilder firstWord = new StringBuilder();
		StringBuilder firstBW = new StringBuilder();
		try {
			for (char c : str.toCharArray()) {
				if (c <= 255) {
					word.append(c);
					firstWord.append(c);
					if (c >= 97 && c < 123) {
						bigword.append((char) (c - 32));
						firstBW.append((char) (c - 32));
					} else {
						bigword.append(c);
						firstBW.append(c);
					}
				} else {
					String[] s = PinyinHelper.toHanyuPinyinStringArray(c,
							sformat);
					String[] bs = PinyinHelper.toHanyuPinyinStringArray(c,
							bformat);
					if (s != null && s.length > 0) {
						word.append(s[0]);
						firstWord.append(s[0].charAt(0));
					}
					if (bs != null && bs.length > 0) {
						bigword.append(bs[0]);
						firstBW.append(bs[0].charAt(0));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(word);
		System.out.println(bigword);
		System.out.println(firstWord);
		System.out.println(firstBW);
	}
	
	private static List<String> asList(String[] array){
		return Arrays.asList(array);
	}
}
