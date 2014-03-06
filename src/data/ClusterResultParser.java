package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class ClusterResultParser {
	private static Calendar calendar = Calendar.getInstance();

	private static final Pattern SPACE = Pattern.compile("\t");

	
	public static void parseCluster() {

		File clusterData = new File("resources/cluster_8_20140304134006_40_30_5_77");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(clusterData));
			String line = null;
			int  count=0;

			while ((line = reader.readLine())!=null) {
			    String[] cols = SPACE.split(line);

			    // sometimes there are multiple separator spaces
			    ArrayList<Double> points = new ArrayList<Double>(24);

			    if(cols.length==27){
			    	String cidStr = cols[0];
			    	int cid = Integer.parseInt(cidStr.split(":")[1]);
			    	
			    	String nameStr = cols[1];
			    	String macDate = nameStr.split(":")[1];
			    	String mac = macDate.split("-")[0];
			    	String date = macDate.split("-")[1];
			    	
			    	// cols[2] == "Values:";
			    	for (int i=3;i<27;i++) {
			    		String value = cols[i];
			    		if (!value.isEmpty()) {
			    			points.add(i-3, Double.valueOf(value));
			    		}
			    	}
			    	
			   
			    	System.out.println(cid + " " + mac+" "+date + " "+points.size()+ " __" +points.get(23)  ); 
			    }
			    count++;

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static  void parseCenters() {

		File centerData = new File("resources/centers_8_20140304134006_40_30_5");
		double max = 0.0;
		int maxc = 0; 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(centerData));
			String line = null;
			int  count=0;

			while ((line = reader.readLine())!=null) {
			    String[] cols = SPACE.split(line);
			    String name = null;
			    // sometimes there are multiple separator spaces
			    ArrayList<Double> points = new ArrayList<Double>(24);
			    ArrayList<Double> radiuses = new ArrayList<Double>(24);
			    if(cols.length==52){
			    	String cidStr = cols[0];
			    	int cid = Integer.parseInt(cidStr.split(":")[1]);
			    	
			    	String totalStr = cols[1];
			    	int total = Integer.parseInt(totalStr.split(":")[1]);
			    	
			    	// cols[2] == "Centers:";
			    	for (int i=3;i<27;i++) {
			    		String value = cols[i];
			    		if (!value.isEmpty()) {
			    			points.add(i-3, Double.valueOf(value));
			    		}
			    	}
			    	
			    	// cols[27] == "Radius:";
			    	for (int i=28;i<52;i++) {
			    		String value = cols[i];
			    		if (!value.isEmpty()) {
			    			radiuses.add(i-28, Double.valueOf(value));
			    		}
			    	}
			    	System.out.println(cid + " " + total + " "+points.size()+ " __" +points.get(23) +"__ " +radiuses.get(23) ); 
			    }
			    count++;

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		parseCenters(); 
		parseCluster();
	}
}
