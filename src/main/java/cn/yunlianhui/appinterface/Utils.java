package cn.yunlianhui.appinterface;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  

import org.junit.Assert;

public class Utils {		
	private static final String URL = "http://115.29.201.135/mobile/mobileapi.php";
	private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";       
    private CloseableHttpResponse response;     
    private long startTime = 0L;  
    private long endTime = 0L;   
    private String resString = "";
    
	public String getResponse(String parameters) throws IOException {    
	    CloseableHttpClient httpclient = HttpClients.createDefault();  	  
	    HttpPost httppost = new HttpPost(URL);  	    
		startTime = System.currentTimeMillis();  	
		if (parameters != null && !"".equals(parameters.trim())) {
			 // 建立一个NameValuePair数组，用于存储欲传送的参数  
	        //httppost.addHeader("Content-type","application/json; charset=utf-8");  
	        //httppost.setHeader("Accept", "application/json");  
	        httppost.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));  
			try{  		
			    response = httpclient.execute(httppost);  
			    endTime = System.currentTimeMillis();  		    
			    int statusCode = response.getStatusLine().getStatusCode();                                                
		        
			    System.out.println("--------------------------------------------");  
		        System.out.println("statusCode:" + statusCode);  
		        System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));  
		        if (statusCode != HttpStatus.SC_OK) {
		        	Assert.fail("Method failed:" + response.getStatusLine());    
		        }	  
		        
		        HttpEntity myEntity = response.getEntity();  
		        System.out.println(myEntity.getContentType());  
		        System.out.println("Content-Length:" + myEntity.getContentLength());  	          
		        resString = EntityUtils.toString(myEntity);  
		    }catch(ClientProtocolException e){
		        e.printStackTrace();	
		    }finally{  
		        response.close();  
		    }  
		}
	    return resString;		
    }
}  