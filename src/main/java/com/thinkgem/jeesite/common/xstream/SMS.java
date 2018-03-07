package com.thinkgem.jeesite.common.xstream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.webinfo.dao.MtWebinfoDao;
import com.thinkgem.jeesite.modules.webinfo.entity.MtWebinfo;
import com.thinkgem.jeesite.modules.webinfo.service.MtWebinfoService;

public class SMS {
	
	/**
	 * 发送短信
	 * @param mobiles
	 * @param context
	 * @return
	 */
	public static String sendout(String mobiles, String context) {		
		return sendout(mobiles, context, "");
	}
	
	/**
	 * 定时发送短信
	 * @param mobiles
	 * @param context
	 * @param time
	 * @return
	 */
	public static String sendout(String mobiles, String context, String time) {
		if(mobiles==null || mobiles=="") {
			return null;
		}
		String result = null;		
		if(SMS.getSMSMold().equals("1")) {
			// 用户名
			String account=getProper("sms.account");
			// 密码
			String password=getProper("sms.password");
			// 电话号码字符串，中间用英文逗号间隔
			StringBuffer mobileString=new StringBuffer(mobiles);
			// 内容字符串
			StringBuffer contextString=new StringBuffer(context);
			// 追加发送时间，可为空，为空为及时发送
			String sendtime=time;
			
			String phone = mobileString.toString();
			String text = contextString.toString();
			result = SMS.postHttp(account, password, phone, text, sendtime);
		} else if(SMS.getSMSMold().equals("2")) {
			// 用户名
			String name=getProper("sms.name");
			// 密码
			String pwd=getProper("sms.pwd");
			// 电话号码字符串，中间用英文逗号间隔
			StringBuffer mobileString=new StringBuffer(mobiles);
			// 内容字符串
			StringBuffer contextString=new StringBuffer(context);
			// 签名
			String sign=getProper("sms.sign");
			// 追加发送时间，可为空，为空为及时发送
			String stime=time;
			// 扩展码，必须为数字 可为空
			StringBuffer extno=new StringBuffer();
			// 发送
			try {
				result = SMS.doPost(name, pwd, mobileString, contextString, sign, stime, extno);
				System.out.println(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// https://luosimao.com/
		}
		return result;
	}
	
	/**
	 * 获取使用SMS服务商
	 * @return
	 */
	private static String getSMSMold() {
		MtWebinfoDao mtWebinfoDao = SpringContextHolder.getBean(MtWebinfoDao.class);
		MtWebinfo mtWebinfo = mtWebinfoDao.findVarname("SMS_POST");
		return mtWebinfo.getPvalues();
	}
	
	/**
	 * 【企业通】发送短信
	 * @param account
	 * @param password
	 * @param mobile
	 * @param content
	 * @param sendtime
	 * @return
	 */
	private static String postHttp(String account, String password, String mobile, String content, String sendtime) {
		try {
			StringBuffer sendParam = new StringBuffer();
			sendParam.append("action=send");
			sendParam.append("&userid=").append("64");
			sendParam.append("&account=").append(URLEncoder.encode(account, "UTF-8"));
			sendParam.append("&password=").append(URLEncoder.encode(password, "UTF-8"));			
			sendParam.append("&content=").append(URLEncoder.encode(content, "UTF-8"));
			sendParam.append("&sendTime=").append(sendtime);
			sendParam.append("&mobile=").append(mobile);
			sendParam.append("&extno=");
			String url = "http://sms1.ronglaids.com/sms.aspx";
			return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,sendParam.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "未发送，异常-->" + e.getMessage();
		}
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

	/**
	 * 【短信网】发送短信
	 * 
	 * @param name			用户名
	 * @param pwd			密码
	 * @param mobileString	电话号码字符串，中间用英文逗号间隔
	 * @param contextString	内容字符串
	 * @param sign			签名
	 * @param stime			追加发送时间，可为空，为空为及时发送
	 * @param extno			扩展码，必须为数字 可为空
	 * @return				
	 * @throws Exception
	 */
    public static String doPost(String name, String pwd, 
    		StringBuffer mobileString, StringBuffer contextString,
    		String sign, String stime, StringBuffer extno) throws Exception {
    	StringBuffer param = new StringBuffer();
    	param.append("name="+name);
    	param.append("&pwd="+pwd);
    	param.append("&mobile=").append(mobileString);
    	param.append("&content=").append(URLEncoder.encode(contextString.toString(),"UTF-8"));
    	param.append("&stime="+stime);
    	param.append("&sign=").append(URLEncoder.encode(sign,"UTF-8"));
    	param.append("&type=pt");
    	param.append("&extno=").append(extno);
        
        URL localURL = new URL("http://web.duanxinwang.cc/asmx/smsservice.aspx?");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer;
    }
	
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	private static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	
}
