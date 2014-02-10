package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

public class StopwordsCollector {

	private HashSet<String> loadFile(String filename){
		File file = new File(filename);
		HashSet<String> words = new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			String[] tokens = new String[]{};
			while((line = reader.readLine())!=null){
//				System.out.println(line);
				line = line.trim();
				if(StringUtils.isNotEmpty(line)){
					if(line.indexOf(",")>0){
						tokens = line.split(",");
						for(String token : tokens){
							if(token.indexOf("\"")>0){
								token = token.replaceAll("\"", "");
							}
							token = token.trim();
							if(token.length()>0){
								words.add(token);
							}
						}
					}else{
						line = line.trim();
						if(line.length()>0){
							words.add(line);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}
	
	public static void main(String[] args) {
		StopwordsCollector collector = new StopwordsCollector();
		HashSet<String> stopwords = new HashSet<String>(); 
		stopwords.addAll(collector.loadFile("resources/extStop.txt"));
		stopwords.addAll(collector.loadFile("resources/cnenstop.txt"));
		stopwords.addAll(collector.loadFile("resources/ext_stopword.dic"));
		TreeSet<String> sorted = new TreeSet<String>(stopwords);
		
		for(String word: sorted	){
			System.out.println(word); 
		}
		System.out.println("done! " + sorted.size() );
	}
}
