package com.jt.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {

	@Autowired(required = false)
	private CloseableHttpClient httpClient;

	@Autowired(required = false)
	private RequestConfig requestConfig;

	/**
	 * 1.代码通用, 2.满足用户任意的需求 3.传递参数时方便
	 */

	public String doGet(String url, Map<String, String> params, String charset) {

		String result = null;

		// 1.判断字符集是否为空,如果为null添加默认值
		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";

		}

		// 2.封装参数 www.kt.com/add?name=tom&age=18
		try {
			if (params != null) {

				URIBuilder builder = new URIBuilder(url);
				
				for (Map.Entry<String, String> entry : params.entrySet()) {

					builder.addParameter(entry.getKey(), entry.getValue());
				}

				// 参数拼接成功www.kt.com/add?name=tom&age=18
				url = builder.build().toString();
			}
			// 3.定义请求对象
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);

			// 4.发起请求
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			//5.判断返回是否正确
	    	if(httpResponse.getStatusLine().getStatusCode() == 200){
	    		
	    		result = EntityUtils.toString(httpResponse.getEntity(),charset);
	    	}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public String doGet(String url) {

		return doGet(url, null, null);

	}

	public String doGet(String url, Map<String, String> params) {

		return doGet(url, params, null);

	}

	/**
	 * 实现post提交
	 * 
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public String doPost(String url, Map<String, String> params, String charset) {

		String result = null;

		// 1.判断字符集是否为空,如果为null添加默认值
		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";

		}

		// 2.post参数的提交
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);

		try {

			if (params != null) {
				// 3.构建表单封装提交实体
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> p : params.entrySet()) {

					parameters.add(new BasicNameValuePair(p.getKey(), p.getValue()));
				}
                //必须写定charset
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, charset);
				httpPost.setEntity(entity);
			}

			// 4.发起请求,www.baidu.com
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

			// 5.判断返回是否正确
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //解析时家charset
				result = EntityUtils.toString(httpResponse.getEntity(),charset);
				System.out.println(result);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return result;
	}
	
	
	public String doPost(String url, Map<String, String> params){
		
		return doPost(url,params,null);		
	}
	
	public String doPost(String url){
		
		return doPost(url, null, null);
	
	}
	
	

}
