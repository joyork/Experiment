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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MeituanSpider {

	public static final String WAIMAI_API_URL= 
			"http://waimai.meituan.com/home/wx4gdujukfxv";
	
	public static final String WAIMAI_URL = "http://waimai.meituan.com";
	
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
	
	/**
	 * 解析
	 * @param doc
	 * @return
	 */
	public List<HashMap<String,String>> getPoiList(String waimaiIndexUrl){
		
		List<HashMap<String, String>> poiList = new ArrayList<HashMap<String, String>>();
		
		FileWriter writer = null;

		try {
			writer = new FileWriter("mt_poi.txt", true);

			Document doc = getContent(waimaiIndexUrl);

			Elements rests = doc.select("div.restaurant");

			Iterator<Element> iterator = rests.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				Element e = iterator.next();

				if (e.select("div.rest-mask div.others span").hasClass(
						"outof-sale")) {
					continue;
				}

				HashMap<String, String> poiMap = new HashMap<String, String>();

				String name = e.select("span[title]").attr("title");
				String url = WAIMAI_URL + e.select("a.rest-atag").attr("href");
				String startPrice = e.select("span.start-price").text();
				String sendPrice = e.select("span.send-price").text();
				String minus = "NoMinus";
				if (StringUtils.isNotBlank(e
						.select("script[data-icon=i-minus]").toString())) {
					minus = e.select("script[data-icon=i-minus]").html();
				}
				String sendTime = "NoSendTime";
				if (e.select("span.send-time").hasText()) {
					sendTime = e.select("span.send-time").text();
				}
				String picUrl = e.select("img.scroll-loading").attr("data-src");

				String log = name + "\t" + url + "\t" + startPrice + "\t"
						+ sendPrice + "\t" + minus + "\t"
						+ sendTime + "\t" + picUrl;

				System.out.println(log);
				writer.write(log+"\n");

				poiMap.put("name", name);
				poiMap.put("url", url);
				poiMap.put("startPrice", startPrice);
				poiMap.put("sendPrice", sendPrice);
				poiMap.put("minus", minus);
				poiMap.put("sendTime", sendTime);
				poiMap.put("picUrl", picUrl);

				// if(count>10){
				// break;
				// }
				count++;

				poiList.add(poiMap);
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
			writer = new FileWriter("mt_foodmenu.txt", true);

			Document doc = getContent(poiUrl);

			String mark = doc.select("div.stars span.mark").text();
			String shopName = doc.select("div.na > a > span").text();

			String log = shopName + " marks " + mark;
			writer.write(log+"\n");
			
			Elements foods = doc.select("div.food-nav");
			Iterator<Element> iter = foods.iterator();
			while (iter.hasNext()) {
				Element e = iter.next();

				Elements cates = e.select("div.category");

				System.out.println(shopName + " has totally cates:"
						+ cates.size());
				writer.write(shopName + " has totally cates:" + cates.size()
						+ "\n");
				
				for (Element cate : cates) {
					String cateName = cate.select("h3.title").attr("title");
					HashMap<String, String> food = new HashMap<String, String>();
					Elements picfoods = cate.select("div.pic-food");

					for (Element picfood : picfoods) {
						String picUrl = picfood.select("div.avatar > img")
								.attr("data-src");
						String name = picfood.select("span.name").attr("title");
						String soldCount = picfood.select(
								"div.sold-count > span").text();
						String price = picfood.select("div.only").text();
						
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

					Elements textfoods = cate.select("div.text-food");
					for (Element textfood : textfoods) {
						String name = textfood.select(
								"div.description > div.na").attr("title");
						String soldCount = textfood.select("div.count").text();
						String price = textfood.select("div.only").text();

						log = name + "\t" + soldCount + "\t" + price +"\t"+cateName;
						writer.write(log+"\n"); 
						System.out.println(log); 

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
		File poifile = new File("mt_poi.txt");
		poifile.delete();
		File menufile = new File("mt_foodmenu.txt");
		menufile.delete();
		
		MeituanSpider ms  = new MeituanSpider();
		List<HashMap<String,String>> poiList = ms.getPoiList(WAIMAI_API_URL);
		for(HashMap<String,String> poi : poiList){
			ms.getFoodList(poi.get("url"));
		}
	}
}
