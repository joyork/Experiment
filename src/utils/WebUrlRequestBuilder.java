package utils;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.List;  
  
public class WebUrlRequestBuilder {  
  
  
    public static URLConnection webUrlRequest(String requestUrl) throws IOException {  
        return new URL(requestUrl).openConnection();  
    }  
  
    public static List<String> readLine(InputStream inputStream) throws IOException {  
        List<String> list = new ArrayList<String>();  
        BufferedReader bufferReader = null;  
        try {  
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));  
              
            String readline;  
            while ((readline = bufferReader.readLine()) != null) {  
                list.add(readline);  
            }  
        } finally {  
            bufferReader.close();  
        }  
        return list;  
          
    }  
  
    public static void main(String[] args) throws IOException {  
        URLConnection webUrlRequest = webUrlRequest("http://www.360buy.com");  
        List<String> readline = readLine(webUrlRequest.getInputStream());  
        System.out.println(readline);  
    }  
}  
