/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stat.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.stat.dao.MtStatDao;
import com.thinkgem.jeesite.modules.stat.entity.MtStat;
import com.thinkgem.jeesite.modules.stat.entity.MtStatDetail;

/**
 * statController
 * @author 张琨鹏
 * @version 2017-09-23
 */
@Controller
@RequestMapping(value = "${adminPath}/stat/mtStat")
public class MtStatController extends BaseController {

	@Autowired
	private MtStatDao dao;
	

	@RequiresPermissions("stat:mtStat:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtStat mtStat, HttpServletRequest request, HttpServletResponse response, Model model) {
		String start =       request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		System.out.println("start :"+start +", end :"+endtime);
		if(start == null && endtime==null){
			start = DateUtils.getDate();
			endtime = DateUtils.getDate();
		}
		Integer pv = 0;
		Integer uv = 0;
		List<MtStatDetail> details = dao.getDetail(start, endtime);
		List<MtStatDetail> title_details = dao.getTitleDetail(start,endtime);
		pv = dao.getpv(start,endtime);
		uv = dao.getuv(start,endtime);
		List<String> hours = new ArrayList<String>();
		List<String> uvs = new ArrayList<String>();
		List<String> pvs = new ArrayList<String>();
		
		for (MtStatDetail string : details) {
			hours.add(string.getHour());
			pvs.add(string.getPv());
			uvs.add(string.getUv());
		}
		model.addAttribute("pv", pv);
		model.addAttribute("uv", uv);
		model.addAttribute("detail",details);
		model.addAttribute("title_details", title_details);
		model.addAttribute("pvs",pvs);
		model.addAttribute("hours", hours);
		model.addAttribute("uvs", uvs);
		model.addAttribute("start", start);
		model.addAttribute("end", endtime);
		return "modules/stat/mtStatList";
	}


}