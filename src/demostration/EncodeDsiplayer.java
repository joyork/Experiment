package demostration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodeDsiplayer {
	public static void main(String[] args) {
		try {
			String url= "http://xyx.game.153.com/game/12434.html";
			String str = URLEncoder.encode(url,"utf-8");
			System.out.println(str); 
			String decode = "http%3A%2F%2Fxyx.game.153.com%2Fgame%2F12434.html";
			System.out.println(URLDecoder.decode(decode,"utf-8")); 
			
			Pattern pattern = Pattern.compile("(\\d+).html");
			Matcher matcher = pattern.matcher(url);
			if(matcher.find()){
				System.out.println(matcher.group(1));
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
