package game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XyxGameParser {
	public static void main(String[] args) {
		String desc = "<p>ÂÃ£¬Ò»£¡<br /><br /><!--{$Snapshots-visual}--></p><div><div class=\"t common_c\"><h2></h2></div></div>";
		Pattern p = Pattern.compile("<[^>]+>");
		Matcher m = p.matcher(desc);
		String gameDesc = m.replaceAll("");
		System.out.println(gameDesc); 
	}
}
