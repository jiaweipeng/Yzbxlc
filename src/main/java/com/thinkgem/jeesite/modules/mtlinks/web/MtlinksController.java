/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtlinks.web;

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
import com.thinkgem.jeesite.modules.mtlinks.entity.Mtlinks;
import com.thinkgem.jeesite.modules.mtlinks.service.MtlinksService;

/**
 * 友情连接Controller
 * @author wuhao
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mtlinks/mtlinks")
public class MtlinksController extends BaseController {

	@Autowired
	private MtlinksService mtlinksService;
	
	@ModelAttribute
	public Mtlinks get(@RequestParam(required=false) String id) {
		Mtlinks entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtlinksService.get(id);
		}
		if (entity == null){
			entity = new Mtlinks();
		}
		return entity;
	}
	
	@RequiresPermissions("mtlinks:mtlinks:view")
	@RequestMapping(value = {"list", ""})
	public String list(Mtlinks mtlinks, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Mtlinks> page = mtlinksService.findPage(new Page<Mtlinks>(request, response), mtlinks); 
		model.addAttribute("page", page);
		return "modules/mtlinks/mtlinksList";
	}

	@RequiresPermissions("mtlinks:mtlinks:view")
	@RequestMapping(value = "form")
	public String form(Mtlinks mtlinks, Model model) {
		model.addAttribute("mtlinks", mtlinks);
		return "modules/mtlinks/mtlinksForm";
	}

	@RequiresPermissions("mtlinks:mtlinks:edit")
	@RequestMapping(value = "save")
	public String save(Mtlinks mtlinks, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtlinks)){
			return form(mtlinks, model);
		}
		mtlinksService.save(mtlinks);
		addMessage(redirectAttributes, "保存友情连接成功");
		return "redirect:"+Global.getAdminPath()+"/mtlinks/mtlinks/?repage";
	}
	
	@RequiresPermissions("mtlinks:mtlinks:edit")
	@RequestMapping(value = "delete")
	public String delete(Mtlinks mtlinks, RedirectAttributes redirectAttributes) {
		mtlinksService.delete(mtlinks);
		addMessage(redirectAttributes, "删除友情连接成功");
		return "redirect:"+Global.getAdminPath()+"/mtlinks/mtlinks/?repage";
	}

}