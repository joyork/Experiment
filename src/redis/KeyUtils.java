package redis;

import java.util.*;

public class KeyUtils {
	private static Set<String> keys = new HashSet<String>();
	public static String RCMD_MASTER_KEY = "rcmd:mstr";
	
	public static String getUserKey(String userId){
		String key = "u:"+userId;
		keys.add(key);
		return key;
	}
	
	public static String getUserFoloKey(String userId){
		String key = "u:"+userId+":folo";
		keys.add(key);
		return key;
	}
	
	public static String getUserFansKey(String userId){
		String key = "u:"+userId+":fans";
		keys.add(key);
		return key;
	}
	
	public static String getRcmdFoloKey(){
		return "rcm";
	}
	
	public static Set<String> getTotalKeys(){
		return keys;
	}
}
