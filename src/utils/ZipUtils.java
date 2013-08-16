package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class ZipUtils {

	private static final int BUFF_SIZE = 4096;
	
	public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4";
	

	/***************************************************************************
	 * —πÀıGZip
	 * 
	 * @param data
	 * @author taoyi
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***************************************************************************
	 * Ω‚—πGZip
	 * 
	 * @param data
	 * @author taoyi
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	public static String getContent(String url, String encoding) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
		httpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 60000);

		String content = getContent(httpClient, url, encoding);
		httpClient.getConnectionManager().shutdown();
		return content;
	}
	
	public static String getContent(DefaultHttpClient httpClient, String url, String encoding) {
		String content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			response.getAllHeaders();
			content = EntityUtils.toString(entity, encoding);
			entity.consumeContent();
		} catch (Exception e) {
			System.out.println("Crawl url faild, url:" + url);
		}
		return content;
	}
	
	public static void main(String[] args) {
		String str = "abcdefg";
		byte[] zips = ZipUtils.gZip(str.getBytes());
		for (byte b : zips) {
			System.out.print((char)(b)); 
		}
		System.out.println(); 
//		System.out.println(zips.toString()); 
		
		try {
			URL url = new URL("http://m.163.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] wdj = str.getBytes(); 
		byte[] unzips = ZipUtils.unGZip(zips);
		for (byte b : unzips) {
			System.out.print((char)(b)); 
		}
		System.out.println(); 
		
		String content = getContent( "http://m.163.com", "UTF-8");
		System.out.println(content.substring(0, 100)); 
		
	}
}
