package demostration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppendFile {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat hoursdf = new SimpleDateFormat("yyyy-MM-dd-HH");
	
	public static void main(String[] args) {
		String time = "2014-05-12 21:43:18";
		try {
			Date date = sdf.parse(time);
			int poi = 103;
			long mac = 273384865667101l;
			int dbm = -52;
			String datehour = hoursdf.format(date);
			String content = ""+poi+"\t"+mac+"\t"+time+"\t"+dbm+"\n";
			
			String fileName="/Users/york/Desktop/tdir/test.txt";
			checkExists(fileName); 
//			appendFile(datehour,content);
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

    public static void checkExists(String fileName){
    	File file = new File(fileName);

    	//path表示你所创建文件的路径
    	String path = file.getParent();
    	File dir = new File(path);
		if(!dir.exists()){
		  dir.mkdirs();
		} 
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	public static void appendFile(String fileName, String content) {

		try {
	        //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	        FileWriter writer = new FileWriter(fileName, true);
	        writer.write(content);
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
