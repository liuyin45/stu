package com.jt.test.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	
	/**
	 * 1.定位请求的路径
	 * 2.创建httpClient对象
	 * 3.创建请求的方式类型 get/post
	 * 4.发起请求,获取响应信息.
	 * 5.检测状态码信息是否为200
	 * 6.获取返回值有效数据
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void test01() throws ClientProtocolException, IOException{
		
		String url = "https://item.jd.com/8133837.html";
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
		
		CloseableHttpResponse httpResponse = 
				client.execute(httpGet);
		
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			String result = 
			EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
		}
	}
}
