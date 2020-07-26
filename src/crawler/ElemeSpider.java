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
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElemeSpider {

	public static final String WAIMAI_API_URL= 
			"http://v5.ele.me/poi/from?lng=116.488454&lat=40.006783";
	
	public static final String WAIMAI_URL = "http://ele.me";
	
	public Document getContent(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
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
		List<HashMap<String,String>> poiList = new ArrayList<HashMap<String,String>>();
		FileWriter writer = null;

		try {
			writer = new FileWriter("elm_poi.txt", true);

			Document doc = getContent(waimaiIndexUrl);
			Elements rests = doc.select("div.restaurant-block");

			Iterator<Element> iterator = rests.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				Element e = iterator.next();
				if (e.hasClass("closed")) {
					// System.out.println("closed");
					continue;
				}
				// System.out.println(e);
				HashMap<String, String> poiMap = new HashMap<String, String>();

				String name = e.select("img.restaurant-logo").attr("alt");
				if (StringUtil.isBlank(name)) {
					// System.out.println("no name continue");
					continue;
				}
				String url = e.select("div.logo > a").attr("href");
				String startPrice = e.select("p.restaurant-deliver-desc")
						.text();
				String sendPrice = e.select("span.deliver-fee").attr("title");
				String minus = "NoMinus";
				if (StringUtils.isNotBlank(e.select(
						"div.restaurant-more-info span.extra-discount")
						.toString())) {
					minus = e
							.select("div.restaurant-more-info span.extra-discount ~ span.desc")
							.text();
				}
				String sendTime = "NoSendTime";
				if (StringUtils.isNotBlank(e.select("div.deliver-time-wrapper")
						.toString())) {
					sendTime = e.select("span.send-time").attr(
							"data-original-title");
				}
				String picUrl = e.select("img.restaurant-logo").attr("srcset")
						.split(" ")[0];

				String log = name +"\t"+url +"\t"+startPrice +"\t"+sendPrice +"\t"
						+minus +"\t"+sendTime +"\t"+picUrl ;
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
	
	public List<HashMap<String, String>> getFoodList(String poiUrl) {
		List<HashMap<String, String>> foodList = new ArrayList<HashMap<String, String>>();
		FileWriter writer = null;

		try {
			writer = new FileWriter("elm_foodmenu.txt", true);

			Document doc = getContent(poiUrl);
			Elements atag =	doc.select("div.restaurant-header div.container article.rst-header-main header.rst-header-info"
					+ " div.rst-basic-info a.rst-name");
			String shopName = atag.text();
			String mark = doc.select("div.restaurant-header div.container article.rst-header-main header.rst-header-info"
					+ " div.rst-basic-info div.rst-misc a#rst_rating").text().substring(6) ;
			String log = shopName + " marks " + mark;
			System.out.println(log); 
			writer.write(log+"\n");
			
			Elements foods = doc.select("div#cate_view");
			Iterator<Element> iter = foods.iterator();
			while (iter.hasNext()) {
				Element e = iter.next();

				Elements cates = e.select("section[id^=cate_view_]");
				
				System.out.println(shopName + " has totally cates:"
						+ cates.size());
				writer.write(shopName + " has totally cates:" + cates.size()
						+ "\n");

				for (Element cate : cates) {
					String cateName = cate.select("h2.menu_title")
							.attr("title");
					HashMap<String, String> food = new HashMap<String, String>();
					Elements picfoods = cate.select("li.rst-dish-img-item");

					for (Element picfood : picfoods) {
						String picUrl = picfood.select("img.rst-d-img")
								.attr("srcset").split(" ")[0];
						String name = picfood.select("a.food_name").attr(
								"title");
						String soldCount = picfood.select("span.rst-d-sales")
								.text();
						String price = picfood.select("span.price").text();
						String rating = picfood.select("span.food_rating i")
								.attr("class").split(" ")[1];

						log = name + "\t" + soldCount + "\t" + price
								+ "\t" + rating + "\t" + picUrl + "\t"
								+ cateName;
						System.out.println(log);
						writer.write(log + "\n");

						food.put("picUrl", picUrl);
						food.put("name", name);
						food.put("soldCount", soldCount);
						food.put("price", price);
						food.put("rating", rating);
						food.put("cate", cateName);
						foodList.add(food);
					}

					Elements textfoods = cate.select("li.rst-dish-item");
					for (Element textfood : textfoods) {
						String name = textfood.select("a.food_name").attr(
								"title");
						String soldCount = textfood.select("span.rst-d-sales")
								.text();
						String price = textfood.select("span.price").text();
						String rating = textfood.select("span.food_rating i")
								.attr("class").split(" ")[1];

						log = name + "\t" + soldCount + "\t" + price
								+ "\t" + rating + "\t" + cateName;
						System.out.println(log);
						writer.write(log + "\n");

						food.put("name", name);
						food.put("soldCount", soldCount);
						food.put("price", price);
						food.put("rating", rating);
						food.put("cate", cateName);
						foodList.add(food);
					}

					System.out
							.println("cate " + cateName + ". Up to now total :"
									+ foodList.size() + " food");
					writer.write("cate " + cateName + ". Up to now total :"
							+ foodList.size() + " food"+"\n");
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
		File poifile = new File("elm_poi.txt");
		poifile.delete();
		File menufile = new File("elm_foodmenu.txt");
		menufile.delete();
		
		ElemeSpider ms  = new ElemeSpider();
		List<HashMap<String,String>> poiList = ms.getPoiList(WAIMAI_API_URL);
		System.out.println(poiList.size()); 
		for(HashMap<String,String> poi : poiList){
			ms.getFoodList(poi.get("url"));
		}
	}
}
