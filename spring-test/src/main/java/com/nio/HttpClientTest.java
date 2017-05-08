package com.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import sun.net.www.MessageHeader;
import sun.net.www.http.HttpClient;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.test.util.JsonUtil;

public class HttpClientTest {
	public static void main(String[] args) {
		URL url;
		try {
//			url = new URL("http","10.253.21.54","123123123");
//			HttpClient httpClient = new HttpClient(url, "10.253.21.54", 8080);
//			
//			byte[] buf = {'t','e','s','t'};
//			InputStream is = new ByteInputStream(buf, buf.length);
//			MessageHeader msg = new MessageHeader(is);
//			httpClient.writeRequests(msg);
			
			String urlStr = "http://10.253.21.54:8080";
			HttpClientBuilder httpClient1 = HttpClientBuilder.create();
			 //HttpClient  
	        CloseableHttpClient closeableHttpClient = httpClient1.build();  
	        
//	        HttpGet httpGet = new HttpGet(urlStr+"?test=123123");  
//	        System.out.println(httpGet.getRequestLine());  
//	        //执行get请求  
//            HttpResponse httpResponse = closeableHttpClient.execute(httpGet); 
	        
	        HttpPost httpPost = new HttpPost(urlStr);
	        StringEntity se = new StringEntity("{\"test\":\"12312\"}");
	        se.setContentType("text/json");
//	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
	        httpPost.setEntity(se);
	        
	        //执行post请求  
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost); 
            StringEntity strE = (StringEntity) httpResponse.getEntity();
            System.out.println(JsonUtil.object2Json(httpResponse));
            System.out.println(JsonUtil.object2Json(strE));
            closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
