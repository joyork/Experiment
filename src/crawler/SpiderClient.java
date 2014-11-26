package crawler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderClient {
	
	public static final String WAIMAI_API_URL= 
			"http://waimai.meituan.com/geo/geohash?from=m&addr=''";
	//lat=40.014445&lng=116.321087&
	
	public static final String WAIMAI_URL = "http://waimai.meituan.com";
	
	/**
	 * 根据坐标获取附近餐厅的url
	 * @param lat
	 * @param lng
	 * @return
	 * @throws IOException 
	 */
	public Document getContentByLoc(String lat,String lng) throws IOException{
		String url = WAIMAI_API_URL+"&lat="+lat+"&lng="+lng;
		Document doc = Jsoup.connect(url).get();
		return doc;
	}
	
	/**
	 * 解析
	 * @param doc
	 * @return
	 */
	public List<String> getContent(Document doc){
//		<a class="rest-atag" href="/restaurant/19995" target="_blank">
		Elements links = doc.select(".rest-atag").select("a[href]");
		List<String> result =new ArrayList<String>();
		
		Iterator<Element> iterator = links.iterator();
		while(iterator.hasNext()){
			Element e = iterator.next();
//			System.out.println(e.select("span").text());
//			System.out.println(e.attr("href"));
			result.add(e.attr("href"));
		}
		return result;
	}
	
	
	/**
	 * 解析餐厅菜品的详细信息
	 * @param doc
	 * @return
	 * @throws IOException 
	 */
	public List<Food> getDetail(String context) throws IOException{
		String url = WAIMAI_URL+context;
		Document doc = Jsoup.connect(url).get();
		
		List<Food> result = new ArrayList<Food>();
		String eateryName = doc.select(".shopping-cart").attr("data-poiname");
		
		Elements categorys = doc.select(".category");
		Iterator<Element> catIterator = categorys.iterator();
		
		while(catIterator.hasNext()){
			Element category = catIterator.next();
			String categoryName = category.select("h3").attr("title");
			
			Elements links = category.select(".pic-food*");
			//有图片的那种样式
			if(links != null && links.size()>0){
				Iterator<Element> iterator = links.iterator();
				while(iterator.hasNext()){
					Element e = iterator.next();
	//				System.out.println("url："+e.select("div.avatar img").attr("data-src"));
	//				System.out.println("名称："+e.select(".name").text());
	//				System.out.println("价格："+e.select(".price").text());
	//				System.out.println("月销售量："+e.select(".sold-count").text());
	//				System.out.println("赞："+e.select(".cc-syellow").text().replace("(","").replace(")", ""));
					Food food = new Food();
					int count = 0;
					
					try{
						String name = e.select(".name").text();
						String pic = e.select("div.avatar img").attr("data-src");
						String price = e.select(".price").text().replace("￥", "").replace("/份", "").replace("/斤", "");
						String countMonth = e.select(".sold-count").text().replace("月售", "").replace("份", "");
						String goodCount = e.select(".cc-syellow").text().replace("(","").replace(")", "");
						
						food.setEateryName(eateryName);
						food.setEateryUrl(url);
						food.setCategory(categoryName);
						food.setName(name);
						food.setPic(pic);
						
						food.setPrice(StringUtils.isNotBlank(price)?Double.valueOf(price):0);
						count  = StringUtils.isNotBlank(countMonth)?Integer.parseInt(countMonth):0;
						food.setCountMonth(count);
						food.setGoodCount(StringUtils.isNotBlank(goodCount)?Integer.parseInt(goodCount):0);
					}catch(Exception exp){
						continue;
					}
					
					if(count > 0)
						result.add(food);
				}
			}else if(category.select(".text-food")!=null&&category.select(".text-food").size()>0){
				//没有图片的那种样式
				Elements ll = category.select(".text-food");
				Iterator<Element> iterator = ll.iterator();
				while(iterator.hasNext()){
					Element e = iterator.next();
					
					int count = 0;
					Food food = new Food();
					try{
						String name = e.select(".nodesc").attr("title");
						String pic = "";
						String priceStr = e.select(".unit-price").text();
						String price = e.select(".unit-price").text().replace("¥", "").substring(0,priceStr.indexOf("/")-1);
						String countMonth = e.select(".count").text().replace("月售", "").replace("份", "");
						String goodCount = e.select(".cc-syellow").text().replace("(","").replace(")", "");
						
//						System.out.println(name);
//						System.out.println(price);
//						System.out.println(countMonth);
//						System.out.println(goodCount);
						
						food.setEateryName(eateryName);
						food.setEateryUrl(url);
						food.setCategory(categoryName);
						food.setName(name);
						food.setPic(pic);
						
						food.setPrice(StringUtils.isNotBlank(price)?Double.valueOf(price):0);
						count  = StringUtils.isNotBlank(countMonth)?Integer.parseInt(countMonth):0;
						food.setCountMonth(count);
						food.setGoodCount(StringUtils.isNotBlank(goodCount)?Integer.parseInt(goodCount):0);
					}catch(Exception ex){
						continue;
					}
					if(count > 0)
						result.add(food);
					
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 获取所有的抓取结果，根据坐标
	 * @return
	 * @throws IOException 
	 */
	public List<Food> getAll(String lat,String lng) throws IOException{
		List<Food> result = new ArrayList<Food>();
		List<String> urls = getContent(getContentByLoc(lat,lng));
		
		//如果只要取前10家店
		urls = urls.subList(0, 10);
		
		for(String url : urls){
			result.addAll(getDetail(url));
		}
		
		Collections.sort(result,new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return o2.getCountMonth().compareTo(o1.getCountMonth());
			}
		} );
		
		return result;
	}
	
	
	class Food{
		private String name;
		private String pic;
		private Double price;
		private Integer countMonth;
		private Integer goodCount;
		private String category;
		private String eateryName;
		private String eateryUrl;
		public Food(){}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPic() {
			return pic;
		}
		public void setPic(String pic) {
			this.pic = pic;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getEateryName() {
			return eateryName;
		}
		public void setEateryName(String eateryName) {
			this.eateryName = eateryName;
		}
		public String getEateryUrl() {
			return eateryUrl;
		}
		public void setEateryUrl(String eateryUrl) {
			this.eateryUrl = eateryUrl;
		}
		public Integer getCountMonth() {
			return countMonth;
		}
		public void setCountMonth(Integer countMonth) {
			this.countMonth = countMonth;
		}
		public Integer getGoodCount() {
			return goodCount;
		}
		public void setGoodCount(Integer goodCount) {
			this.goodCount = goodCount;
		}

		@Override
		public String toString(){
			return "name:"+name+",pic:"+pic+",price:"+price+",countMonth:"+countMonth
					+",goodCount:"+goodCount+",category:"+category+",eateryName:"+eateryName+",eateryUrl:"+eateryUrl;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
//		System.out.println(getContentByLoc("40.014445","116.321087"));
//		String lat = "40.014445";
//		String lng = "116.321087";
		
		String lat = "39.979291";
		String lng = "116.43262";
		SpiderClient sc = new SpiderClient();
		List<Food> result = sc.getAll(lat, lng);
		System.out.println(result.size());
		
		for(Food f : result){
			if(f.getCountMonth()>1)
				System.out.println(f);
		}
		
//		List<Food> ll = sc.getDetail("/restaurant/18228");
//		for(Food l : ll ){
//			System.out.println(l);
//		}
	}

}
