/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtusermony.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.xstream.DayWeek;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.mtusermony.entity.MtUsermony;
import com.thinkgem.jeesite.modules.mtusermony.service.MtUsermonyService;

/**
 * 客户资金列表Controller
 * @author wuhao
 * @version 2017-08-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mtusermony/mtUsermony")
public class MtUsermonyController extends BaseController {

	@Autowired
	private MtUsermonyService mtUsermonyService;
	@Autowired
	private MtUserService mtUserService;
	
	@ModelAttribute
	public MtUsermony get(@RequestParam(required=false) String id) {
		MtUsermony entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtUsermonyService.get(id);
		}
		if (entity == null){
			entity = new MtUsermony();
		}
		return entity;
	}
	
	@RequiresPermissions("mtuser:mtUser:view")
	@RequestMapping(value = {"userlist", ""})
	public String userlist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		String starttime = request.getParameter("starttime");
		String aborttime = request.getParameter("aborttime");
		if(starttime==null || aborttime==null) {
			starttime = "";
			aborttime = "";
		}
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser);
		model.addAttribute("starttime", starttime);
		model.addAttribute("aborttime", aborttime);
		model.addAttribute("page", page);
		return "modules/mtusermony/mtUserList";
	}
	
	@RequiresPermissions("mtuser:mtUser:view")
	@RequestMapping(value = {"fundlist", ""})
	public String fundlist(MtUsermony mtUsermony, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<MtUsermony> dataList = null;
		Page<MtUsermony> page = new Page<MtUsermony>(request, response);
		mtUsermony.setUserid(request.getParameter("userid"));
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		if(!(startdate==null) && !(enddate==null)) {
			mtUsermony.setPagesize(page.getPageSize());
			Date dateEnd = DateUtils.parseDate(enddate);
			Date dateStart = DateUtils.parseDate(startdate);
			int count = DayWeek.makeDaynum(dateEnd, dateStart);
			String pageno = request.getParameter("pageNo");
			mtUsermony.setStartdate(dateStart);
			mtUsermony.setEnddate(dateEnd);
			if(!(pageno==null)) {
				mtUsermony.setPageno(Integer.parseInt(pageno));
			}
			page.setCount(count);
			dataList = mtUsermonyService.findDate(mtUsermony);
		} else {
			mtUsermony.setPagesize(page.getPageSize());
			dataList = mtUsermonyService.makeDate(mtUsermony);
			page.setCount(dataList.size());
		}
		mtUsermony.setPage(page);
		page.setList(dataList);
		model.addAttribute("page", page);
		return "modules/mtusermony/mtUsermonyList";
	}

}