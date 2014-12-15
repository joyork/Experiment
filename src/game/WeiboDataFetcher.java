package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WeiboDataFetcher {

	// �鿴ͨ��֤�û��Ƿ�ͨ΢��
	// �ӿڣ�http://extapi.t.163.com/service/isTinyBlogUser?email=baiyawen@126.com
	public static void checkOpen() {

		try {
			String url = "http://extapi.t.163.com/service/isTinyBlogUser?email=baiyawen@126.com";

			URL verify = new URL(url);
			URLConnection conn = verify.openConnection();
			BufferedReader is = new BufferedReader(new InputStreamReader(conn
			    .getInputStream()));
			String status = is.readLine();
			is.close();
			if ("&ResultCode=1".equals(status)) {
				System.out.println("�ѿ�ͨ");
			} else {
				System.out.println("��δ��ͨ");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ�û�ͨ��֤��΢���ĸ�����Ϣ
	// �ӿڣ�http://extapi.t.163.com/api/userInfoInternal?email=baiyawen@126.com&format=json
	public static void personInfo(){
		try {
			String url = "http://extapi.t.163.com/api/userInfoInternal?email=baiyawen@126.com&format=json";

			URL verify = new URL(url);
			URLConnection conn = verify.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"utf-8");

			BufferedReader is = new BufferedReader(isr);
			
			String status = is.readLine();
			is.close();

			if (status!=null) {
				System.out.println(status);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ��ͨ��֤��΢��
	// http://extapi.t.163.com/service/newTweetInternal
	public static void tweet(){
		String url = "http://extapi.t.163.com/service/newTweetInternal?";
		String email = "baiyawen@126.com";
		String inReplyToStatusId = "";
		String content = "test weibo";
		String source = "wan.163.com";
		String tweeter=null;
    try {
	    tweeter = url+"email="+email+"&content="+URLEncoder.encode(content,"utf-8")+"&source="+URLEncoder.encode(source, "utf-8");
    } catch (UnsupportedEncodingException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
    }
		
		URL result;
		try {
			result = new URL(tweeter);

			URLConnection conn = result.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(),
			    "utf-8");

			BufferedReader is = new BufferedReader(isr);

			String status = is.readLine();
			is.close();

			if (status != null) {
				System.out.println(status);
			}

		} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }
	}
	
	public static void main(String[] args) {

		checkOpen();
		personInfo();
		tweet();
	}
}
