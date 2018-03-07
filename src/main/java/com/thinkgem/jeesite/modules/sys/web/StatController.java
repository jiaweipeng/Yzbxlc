package com.thinkgem.jeesite.modules.sys.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.xstream.HTTPPost;


@Controller
@RequestMapping(value = "${adminPath}/sys/stat")
public class StatController extends BaseController {

	
	
	public static String getYesToday(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,2);
		Date time=cal.getTime();
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(time));
		return new SimpleDateFormat("yyyyMMdd").format(time);
		
	}
	/**
	 * 实时访客
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"realstat", ""})
	public String area(Model model) {
		
		String resultContent = null;
		HttpPost httpPost = new HttpPost("http://47.92.85.77:81/piwik/index.php?module=API&method=API.get&idSite=1&period=day&date=today&format=JSON&token_auth=574e872eb614be64a429eb76fb9e14e4");
		//List<NameValuePair> listParam = list;
       
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			resultContent = new BasicResponseHandler().handleResponse(response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resultContent); ;
		int uv = 0;
		int actions = 0;//活动量
		int maxactions = 0;//单次访问的最大活动量
		int bounce_count =0; //跳出次数
		int pv = 0;
		int avg_time_generation =0;//生成页面的评价时间
		String bounce_rate =""; //只查看单个页面的百分比，即访客直接从入口页面离开网站。
		int nb_actions_per_visit = 0;//平均活动次数(查看页面)。
		int avg_time_on_site = 0 ;//平均停留时间。
		if(resultContent!=null){
			JSONObject obj = new JSONObject(resultContent);
			System.out.println(obj);
			uv = obj.getInt("nb_uniq_visitors");
			pv = obj.getInt("nb_pageviews");

			bounce_count = obj.getInt("bounce_count");
			
			bounce_rate = obj.getString("bounce_rate");
			nb_actions_per_visit = obj.getInt("nb_actions_per_visit");
			avg_time_on_site = obj.getInt("avg_time_on_site");
			
		}
	
		model.addAttribute("pv", pv);
		model.addAttribute("uv", uv);
	

		model.addAttribute("bounce_count", bounce_count);

		model.addAttribute("bounce_rate", bounce_rate);
		model.addAttribute("nb_actions_per_visit", nb_actions_per_visit);
		model.addAttribute("avg_time_on_site", avg_time_on_site);
		
		return "modules/sys/realstat";
		//return "redirect:/static/stat/stat/area_"+getYesToday()+"-"+getYesToday()+"/report.html";
	}
	/**
	 * 忠诚度
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"zhongcheng", ""})
	public String zhongcheng(Model model) {
		
		return "modules/sys/zhongcheng";
		//return "redirect:/static/stat/stat/area_"+getYesToday()+"-"+getYesToday()+"/report.html";
	}
	/**
	 * 访问时间
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"vtime", ""})
	public String vtime(Model model) {
		
		return "modules/sys/vtime";
		//return "redirect:/static/stat/stat/area_"+getYesToday()+"-"+getYesToday()+"/report.html";
	}

	/**
	 * 用户分析
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"userinf", ""})
	public String userinf(Model model) {
		
		return "modules/sys/userinf";
		//return "redirect:/static/stat/stat/area_"+getYesToday()+"-"+getYesToday()+"/report.html";
	}

	/**
	 * 页面分析
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"page", ""})
	public String page(Model model) {
		
		return "modules/sys/page";
		//return "redirect:/static/stat/stat/area_"+getYesToday()+"-"+getYesToday()+"/report.html";
	}

	
	
	
}
