package utils;

/////////////////////////////////////////////////////////
//Bare Bones Browser Launch                            //
//Version 1.5 (December 10, 2005)                      //
//By Dem Pilafian                                      //
//Supports: Mac OS X, GNU/Linux, Unix, Windows XP      //
//Example Usage:                                       //
// String url = "http://www.centerkey.com/";           //
// BareBonesBrowserLaunch.openURL(url);                //
//Public Domain Software -- Free to Use as You Like    //
/////////////////////////////////////////////////////////

/**
 * @author Dem Pilafian
 * @author John Kristian
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;

public class BareBonesBrowserLaunch {

    public static void openURL(String url) {
        try {
            browse(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error attempting to launch web browser:\n" + e.getLocalizedMessage());
        }
    }

    private static void browse(String url) throws ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InterruptedException, InvocationTargetException, IOException,
            NoSuchMethodException {
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {
            Class fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
            openURL.invoke(null, new Object[] { url });
        } else if (osName.startsWith("Windows")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else { // assume Unix or Linux
            String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++)
                if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
                    browser = browsers[count];
            if (browser == null)
                throw new NoSuchMethodException("Could not find web browser");
            else
                Runtime.getRuntime().exec(new String[] { browser, url });
        }
    }
   
    public static void main(String[] args) {
    	String[] urls = new String[]{
    		"http://zhushou.360.cn/detail/index/soft_id/2744",
    		"http://zhushou.360.cn/detail/index/soft_id/348370",
    		"http://zhushou.360.cn/detail/index/soft_id/348390",
    		
    		"http://www.nduoa.com/apk/detail/548045",
    		"http://www.nduoa.com/package/detail/128343",
    		
    		"http://anzhi.com/soft_822455.html",
    		
    		"http://apk.hiapk.com/html/2012/09/838443.html",
    		"http://apk.hiapk.com/html/2013/05/1452413.html",
    		"http://apk.hiapk.com/html/2011/09/258092.html",
    		
    		"http://as.baidu.com/a/item?docid=3062491&pre=web_am_software&pos=software_1012_13&f=software_1012_13",
    		"http://as.baidu.com/a/item?docid=2671235&pre=web_am_software&pos=software_1011_6&f=item_3004_5%40software_1011_6",
    		
    		"http://www.appchina.com/market/berry/soft_detail.action?packageName=com.softspb.flashcards.pl",
    		"http://www.appchina.com/market/berry/soft_detail.action?packageName=com.netease.newsreader.activity",
    		
    		"http://apk.91.com/Soft/Android/com.netease.newsreader.activity-256.html",
    		"http://apk.91.com/Soft/Android/net.edegeced.ekebdlegeeed-213-2.13.html"
    		
    	};
    	for(String url : urls){
    		BareBonesBrowserLaunch.openURL(url);
    	}

	}
}
