/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtinvest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mtegsrord.entity.MtEgsrord;
import com.thinkgem.jeesite.modules.mtegsrord.service.MtEgsrordService;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtinvest.service.MtInvestService;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;

/**
 * 投资记录Controller
 * @author wuhao
 * @version 2017-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/mtinvest/mtInvest")
public class MtInvestController extends BaseController {

	@Autowired
	private MtInvestService mtInvestService;
	@Autowired
	private MtProductService mtproductservice;
	@Autowired
	private MtEgsrordService mtEgsrordService;//收益详细


	
	
	//投资收益详情记录
	
	@RequiresPermissions("mtinvest:mtInvest:view")
	@RequestMapping(value = {"mtinvestlist", ""})
	public String mtinvestlist(MtEgsrord mtEgsrord, HttpServletRequest request, HttpServletResponse response, Model model) {
		String  id=request.getParameter("id");
		mtEgsrord.setMtInvestId(Integer.parseInt(id));
		System.out.println("--------------------"+id);
		Page<MtEgsrord> page = mtEgsrordService.findPage(new Page<MtEgsrord>(request, response), mtEgsrord); 
		model.addAttribute("page", page);
		return "modules/mtinvest/mtegsrorList";
	}
	

	//产品列表
	@RequiresPermissions("mtinvest:mtInvest:view")
	@RequestMapping(value = {"Pidlist", ""})
	public String Pidlist(MtProduct mtProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		
		Page<MtProduct> page  = mtproductservice.findPage(new Page<MtProduct>(request, response), mtProduct); 
		model.addAttribute("page", page);	
		return "modules/mtinvest/mtInvetPidList";
	}
	
	//投资记录
	@RequiresPermissions("mtinvest:mtInvest:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtInvest mtInvest, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		mtInvest.setProductid(id);
		Page<MtInvest> page = mtInvestService.findPage(new Page<MtInvest>(request, response), mtInvest); 
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "modules/mtinvest/mtInvestList";
	}

	@RequiresPermissions("mtinvest:mtInvest:view")
	@RequestMapping(value = "form")
	public String form(MtInvest mtInvest, Model model) {
		model.addAttribute("mtInvest", mtInvest);
		return "modules/mtinvest/mtInvestForm";
	}

	@RequiresPermissions("mtinvest:mtInvest:edit")
	@RequestMapping(value = "save")
	public String save(MtInvest mtInvest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtInvest)){
			return form(mtInvest, model);
		}
		mtInvestService.save(mtInvest);
		addMessage(redirectAttributes, "保存投资记录成功");
		return "redirect:"+Global.getAdminPath()+"/mtinvest/mtInvest/?repage";
	}
	
	@RequiresPermissions("mtinvest:mtInvest:edit")
	@RequestMapping(value = "delete")
	public String delete(MtInvest mtInvest, RedirectAttributes redirectAttributes) {
		mtInvestService.delete(mtInvest);
		addMessage(redirectAttributes, "删除投资记录成功");
		return "redirect:"+Global.getAdminPath()+"/mtinvest/mtInvest/?repage";
	}

}