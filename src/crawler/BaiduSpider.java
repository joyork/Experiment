package crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import regex.RegexTester;


public class BaiduSpider {

	public static final String WAIMAI_API_URL= 
			"http://waimai.baidu.com/waimai/shoplist/d19e126fc5bf0e39";
	
	public static final String WAIMAI_URL = "http://waimai.baidu.com/waimai/shop/";
	public static final String PIC_URL = "http://webmap1.map.bdimg.com/maps/services/thumbnails?width=228&height=140&align=center,center&quality=100&src=";
	public Document getContent(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	/**更新json数据
	 * @param addressJSON
	 * @throws Exception 
	 */
	private void parseJSON(String addressJSON) throws Exception {

		System.out.println(addressJSON);
		
		JSONArray jsonArray = new JSONArray(addressJSON);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject regionObj = jsonArray.getJSONObject(i);
			String regionName = regionObj.getString("name");

			JSONArray buildingJSONArray = regionObj
					.getJSONArray("buildingList");
			ArrayList<HashMap<String, String>> building_list = new ArrayList<HashMap<String, String>>();
			for (int j = 0; j < buildingJSONArray.length(); j++) {
				HashMap<String, String> building_map = new HashMap<String, String>();
				building_map.put("id", buildingJSONArray.getJSONObject(j)
						.getString("id"));
				building_map.put("name", buildingJSONArray.getJSONObject(j)
						.getString("name"));

				building_list.add(building_map);
			}
		}

	}
	
	
	public List<HashMap<String,String>> getPoiListFromScript(String waimaiIndexUrl) throws JSONException{
		List<HashMap<String,String>> poiList = new ArrayList<HashMap<String,String>>();
		FileWriter writer = null;

		try {
			writer = new FileWriter("bd_poi.txt", true);

			Document doc = getContent(waimaiIndexUrl);
			String scripts = doc.select("script:eq(12)").html();
			String pattern = "list.js\"\\).init\\((.*)\\{\"lat\":";
			String json = RegexTester.parseByPattern(pattern, scripts);
			json = json.substring(0, json.length() - 1);

			String log = null;
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject poi = jsonArray.getJSONObject(i);
				String name = poi.getString("shop_name");
				String url = WAIMAI_URL + poi.getString("shop_id");
				String startPrice = poi.getString("takeout_price");
				String sendPrice = poi.getString("takeout_cost");
				String sendTime = poi.getString("delivery_time");
				String picUrl = PIC_URL + poi.getString("logo_url");
				String minus = "NoMinus";
				String mian = "NoMian";
				JSONArray welfareList = poi.getJSONArray("welfare_info");
				for (int j = 0; j < welfareList.length(); j++) {
					JSONObject wel = welfareList.getJSONObject(j);
					String type = wel.getString("type");
					if (type.equals("jian")) {
						minus = wel.getString("msg");
					} else if (type.equals("mian")) {
						mian = wel.getString("msg");
					}
				}

				HashMap<String,String> poiMap = new HashMap<String,String>();
				poiMap.put("name", name);
				poiMap.put("url", url);
				poiMap.put("startPrice", startPrice);
				poiMap.put("sendPrice", sendPrice);
				poiMap.put("minus", minus);
				poiMap.put("sendTime", sendTime);
				poiMap.put("picUrl", picUrl);
				poiList.add(poiMap);
				
				log = name + "\t" + url + "\t" + startPrice + "\t" + sendPrice
						+ "\t" + sendTime + "\t" + minus + "\t" + mian + "\t"
						+ picUrl;
				System.out.println(log);
				writer.write(log+"\n");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (writer != null)  
                try {  
                	writer.close();  
                } catch (IOException e) {  
                    throw new RuntimeException("关闭失败！");  
                }  
		}
		return poiList;
	}
	
	public List<HashMap<String,String>> getFoodList(String poiUrl){
		List<HashMap<String,String>> foodList = new ArrayList<HashMap<String,String>>();
		
		FileWriter writer = null;

		try {
			writer = new FileWriter("bd_foodmenu.txt", true);

			Document doc = getContent(poiUrl);
			Elements dl = doc.select("div.b-info > dl");
			String mark = doc.select("div.b-info > dl > dd.rate-con").text();
			String shopName = doc.select("div.b-info > h2").text();

			String log = shopName + " marks " + mark;
			writer.write(log+"\n");
			
			Elements foods = doc.select("section.menu-list");
			Iterator<Element> iter = foods.iterator();
			while (iter.hasNext()) {
				Element e = iter.next();

				Elements cates = e.select("div[id^=menu_]");

				System.out.println(shopName + " has totally cates:"
						+ cates.size());
				writer.write(shopName + " has totally cates:" + cates.size()
						+ "\n");
				
				for (Element cate : cates) {
					String cateName = cate.select("div.list-status span.title").text();
					HashMap<String, String> food = new HashMap<String, String>();
					Elements items = cate.select("div.list li.list-item");

					for (Element item : items) {
						String picUrl = "NoPic";
						Elements figure = item.select("figure.headimg");
						if(StringUtils.isNotBlank(figure.toString())){
							picUrl = figure.select("img").attr("src");
						}
						String name = item.select("div.info h3").attr("data-title");
						String soldCount = item.select(
								"div.info-desc span.sales-count").text();
						String price = item.select("div.m-price").text();
						
						log = name + "\t" + soldCount + "\t" + price +"\t"+cateName+ "\t" + picUrl;
						writer.write(log+"\n"); 
						System.out.println(log); 
						
						food.put("picUrl", picUrl);
						food.put("name", name);
						food.put("soldCount", soldCount);
						food.put("price", price);
						food.put("cate", cateName);
						foodList.add(food);
					}

					log = "cate " + cateName + ". Up to now total :"
									+ foodList.size() + " food";
					writer.write(log+"\n"); 
				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (writer != null)  
                try {  
                	writer.close();  
                } catch (IOException e) {  
                    throw new RuntimeException("关闭失败！");  
                }  
		}
		return foodList;
	}
	
	
	public static void main(String[] args) throws IOException {
		File poifile = new File("bd_poi.txt");
		poifile.delete();
		File menufile = new File("bd_foodmenu.txt");
		menufile.delete();
		
		BaiduSpider ms  = new BaiduSpider();
		try {
			List<HashMap<String,String>> poiList = ms.getPoiListFromScript(WAIMAI_API_URL);
			for(HashMap<String,String> poi : poiList){
				ms.getFoodList(poi.get("url"));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
