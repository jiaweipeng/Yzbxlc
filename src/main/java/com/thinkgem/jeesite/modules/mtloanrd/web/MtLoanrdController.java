/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtloanrd.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mtloanrd.entity.MtLoanrd;
import com.thinkgem.jeesite.modules.mtloanrd.service.MtLoanrdService;

/**
 * 转账列表Controller
 * @author wuhao
 * @version 2017-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mtloanrd/mtLoanrd")
public class MtLoanrdController extends BaseController {

	@Autowired
	private MtLoanrdService mtLoanrdService;
	
	@ModelAttribute
	public MtLoanrd get(@RequestParam(required=false) String id) {
		MtLoanrd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtLoanrdService.get(id);
		}
		if (entity == null){
			entity = new MtLoanrd();
		}
		return entity;
	}
	
	@RequiresPermissions("mtloanrd:mtLoanrd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtLoanrd mtLoanrd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtLoanrd> page = new Page<MtLoanrd>(request, response);
		List<MtLoanrd> dataList = mtLoanrdService.findProjectNO(mtLoanrd);
		System.out.println(dataList);
		page.setCount(dataList.size());
		mtLoanrd.setPage(page);
		page.setList(dataList);
		model.addAttribute("page", page);
		return "modules/mtloanrd/mtLoanrdList";
	}
	
	@RequiresPermissions("mtloanrd:mtLoanrd:view")
	@RequestMapping(value = {"details", ""})
	public String details(MtLoanrd mtLoanrd, HttpServletRequest request, HttpServletResponse response, Model model) {
		boolean show = false;
		if(!(mtLoanrd.getProjectNo()==null)) {
			show = true;
		}
		Page<MtLoanrd> page = mtLoanrdService.findPage(new Page<MtLoanrd>(request, response), mtLoanrd); 
		model.addAttribute("page", page);
		model.addAttribute("show", show);
		return "modules/mtloanrd/mtDetailsList";
	}

	@RequiresPermissions("mtloanrd:mtLoanrd:view")
	@RequestMapping(value = "form")
	public String form(MtLoanrd mtLoanrd, HttpServletRequest request, Model model) {		
		model.addAttribute("show", request.getParameter("show"));
		model.addAttribute("mtLoanrd", mtLoanrd);
		return "modules/mtloanrd/mtLoanrdForm";
	}

	@RequiresPermissions("mtloanrd:mtLoanrd:edit")
	@RequestMapping(value = "save")
	public String save(MtLoanrd mtLoanrd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtLoanrd)){
			return form(mtLoanrd, null, model);
		}
		mtLoanrdService.save(mtLoanrd);
		addMessage(redirectAttributes, "保存转账列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtloanrd/mtLoanrd/?repage";
	}
	
	@RequiresPermissions("mtloanrd:mtLoanrd:edit")
	@RequestMapping(value = "delete")
	public String delete(MtLoanrd mtLoanrd, RedirectAttributes redirectAttributes) {
		mtLoanrdService.delete(mtLoanrd);
		addMessage(redirectAttributes, "删除转账列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtloanrd/mtLoanrd/details?repage";
	}

}