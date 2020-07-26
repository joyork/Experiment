package demostration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

public class PropertiesUtil {

	public static Map<String,Object> getPropertiesMap(){

		Properties prop = new Properties();
		try {
//			prop.load(new FileInputStream("market3g-android-nav-columns.properties"));
			prop.load(new InputStreamReader(new FileInputStream("market3g-android-nav-columns.properties"),"utf-8"));
			for ( Enumeration<?> e = prop.propertyNames(); e.hasMoreElements();){
				String key = (String)e.nextElement();
				System.out.println(key+":"+prop.getProperty(key)); 
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static List<Map<String,Object>> getCategoryList(){
		List<Map<String,Object>> cate = new ArrayList<Map<String,Object>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("market3g-android-nav-columns.properties")));
			String line = null;
			while ((line = reader.readLine())!=null) {
				if(StringUtils.isNotEmpty(line) && !line.startsWith("#")){
					String[] parts = line.split("=");
					if(parts.length==2){
						Map<String,Object> map = new HashMap<String,Object>();
//						map.put("name", parts[0]);
//						map.put("id", parts[1]);
						map.put(parts[0], parts[1]);
						cate.add(map);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cate;
	}
	
	
	public static void main(String[] args) {
		getPropertiesMap();
		JSON jsona = JSONArray.fromObject(getCategoryList());
		System.out.println(jsona.toString());
	}
}
