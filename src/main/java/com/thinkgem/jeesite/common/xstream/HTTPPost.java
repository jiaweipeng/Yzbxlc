package com.thinkgem.jeesite.common.xstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HTTPPost {
	
	private final static String CONTEXT = "cgapi/product/";
	
	public static String request(List<NameValuePair> list, String interFace) {
		String resultContent = null;
		String rulPath = getProper("yzbxlc.com")+CONTEXT;
		HttpPost httpPost = new HttpPost(rulPath+interFace);
		//List<NameValuePair> listParam = list;
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			resultContent = new BasicResponseHandler().handleResponse(response);
			System.out.println(resultContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultContent;
	}
	
	/**
	 * 读取 config.properties 配置文件
	 * @param key 键值
	 * @return
	 */
	private static String getProper(String key) {
		Properties properties = new Properties();
		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		String path = cLoader.getResource("common.properties").getPath();
		FileInputStream fileInputStream = null;
		String defaultValue = null;
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
			defaultValue = properties.getProperty(key);
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultValue;
	}
	
}
