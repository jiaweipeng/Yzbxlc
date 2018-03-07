/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lunbo.web;

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
import com.thinkgem.jeesite.modules.lunbo.entity.MtLunbo;
import com.thinkgem.jeesite.modules.lunbo.service.MtLunboService;

/**
 * 轮播图Controller
 * @author wuhao
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/lunbo/mtLunbo")
public class MtLunboController extends BaseController {

	@Autowired
	private MtLunboService mtLunboService;
	
	@ModelAttribute
	public MtLunbo get(@RequestParam(required=false) String id) {
		MtLunbo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtLunboService.get(id);
		}
		if (entity == null){
			entity = new MtLunbo();
		}
		return entity;
	}
	
	@RequiresPermissions("lunbo:mtLunbo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtLunbo mtLunbo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtLunbo> page = mtLunboService.findPage(new Page<MtLunbo>(request, response), mtLunbo); 
		model.addAttribute("page", page);
		return "modules/lunbo/mtLunboList";
	}

	@RequiresPermissions("lunbo:mtLunbo:view")
	@RequestMapping(value = "form")
	public String form(MtLunbo mtLunbo, Model model) {
		model.addAttribute("mtLunbo", mtLunbo);
		return "modules/lunbo/mtLunboForm";
	}

	@RequiresPermissions("lunbo:mtLunbo:edit")
	@RequestMapping(value = "save")
	public String save(MtLunbo mtLunbo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtLunbo)){
			return form(mtLunbo, model);
		}
		mtLunboService.save(mtLunbo);
		addMessage(redirectAttributes, "保存轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/lunbo/mtLunbo/?repage";
	}
	
	@RequiresPermissions("lunbo:mtLunbo:edit")
	@RequestMapping(value = "delete")
	public String delete(MtLunbo mtLunbo, RedirectAttributes redirectAttributes) {
		mtLunboService.delete(mtLunbo);
		addMessage(redirectAttributes, "删除轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/lunbo/mtLunbo/?repage";
	}

}