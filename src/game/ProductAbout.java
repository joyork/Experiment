package game;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class ProductAbout {

	public static void main(String[] args) {
//	  String date= "2010-10-11";
//	  System.out.println(date.indexOf('-')); 
//	  System.out.println(date.length()); 
//	  
//	  String datefake = "2010-ÄêÄÚ";
//	  System.out.println(datefake.indexOf('-')); 
//	  System.out.println(datefake.length()); 

	  Date modified = new Date(new File("d:/index.log").lastModified());
	  System.out.println(modified); 
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(modified);
	  cal.add(Calendar.MINUTE, -1);
	  System.out.println(cal.getTime()); 

  }
}
