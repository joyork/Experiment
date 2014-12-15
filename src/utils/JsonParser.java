package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;
    
    public void init() {
        
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public void readJson2Map() {
	    String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
	                "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
	    try {
	        Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
	        System.out.println(maps.size());
	        Set<String> key = maps.keySet();
	        Iterator<String> iter = key.iterator();
	        while (iter.hasNext()) {
	            String field = iter.next();
	            System.out.println(field + ":" + maps.get(field));
	        }
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		
	    JsonGenerator jsonGenerator = null;
	    ObjectMapper objectMapper = null;
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File file = new File("subscribe.json");
        File columnfile = new File("column.txt");
        File dingyuefile = new File("dingyue.txt");
        try {
			Map<String, Map<String, Object>> maps = objectMapper.readValue(file, Map.class);
//			System.out.println(maps.size());

			StringBuffer sb = new StringBuffer();
			Map<String,Object> subread = ((List<Map<String,Object>>)maps.get("subscribe_read")).get(0);
			Map<String,Object> subnews = ((List<Map<String,Object>>)maps.get("subscribe_news")).get(0);
			int line = 0;
			List<Map<String,Object>> clistsr = (List<Map<String,Object>>)subread.get("cList");
			List<Map<String,Object>> clistsn = (List<Map<String,Object>>)subnews.get("cList");
			for(Map<String,Object> csr : clistsr){
				List<Map<String,Object>> tlist = (List<Map<String,Object>>)csr.get("tList");
				for (Map<String, Object> ct : tlist) {
					sb.append(csr.get("cName")+","+ct.get("tname")+"\n");
					line++;
				}
			}
			System.out.println(line); 
			
			FileWriter writer = new FileWriter(columnfile);
			writer.append(sb);
			writer.flush();
			writer.close();
			sb = null;
			////////////////////
			sb = new StringBuffer();
			 
			for(Map<String,Object> csr : clistsn){
				List<Map<String,Object>> tlist = (List<Map<String,Object>>)csr.get("tList");
				for (Map<String, Object> ct : tlist) {
					sb.append(csr.get("cName")+","+ct.get("tname")+"\n");
					line++;
				}
			}
			System.out.println(line); 
			
			writer = new FileWriter(dingyuefile);
			writer.append(sb);
			writer.flush();
			writer.close();

        } catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
