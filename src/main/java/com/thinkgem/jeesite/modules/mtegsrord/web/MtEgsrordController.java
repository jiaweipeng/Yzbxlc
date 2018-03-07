/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtegsrord.web;

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
import com.thinkgem.jeesite.modules.mtegsrord.entity.MtEgsrord;
import com.thinkgem.jeesite.modules.mtegsrord.service.MtEgsrordService;

/**
 * 收益列表Controller
 * @author wuhao
 * @version 2017-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/mtegsrord/mtEgsrord")
public class MtEgsrordController extends BaseController {

	@Autowired
	private MtEgsrordService mtEgsrordService;
	
	@ModelAttribute
	public MtEgsrord get(@RequestParam(required=false) String id) {
		MtEgsrord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtEgsrordService.get(id);
		}
		if (entity == null){
			entity = new MtEgsrord();
		}
		return entity;
	}
	
	@RequiresPermissions("mtegsrord:mtEgsrord:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtEgsrord mtEgsrord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtEgsrord> page = mtEgsrordService.findPage(new Page<MtEgsrord>(request, response), mtEgsrord); 
		model.addAttribute("page", page);
		return "modules/mtegsrord/mtEgsrordList";
	}

	@RequiresPermissions("mtegsrord:mtEgsrord:view")
	@RequestMapping(value = "form")
	public String form(MtEgsrord mtEgsrord, Model model) {
		model.addAttribute("mtEgsrord", mtEgsrord);
		return "modules/mtegsrord/mtEgsrordForm";
	}

	@RequiresPermissions("mtegsrord:mtEgsrord:edit")
	@RequestMapping(value = "save")
	public String save(MtEgsrord mtEgsrord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtEgsrord)){
			return form(mtEgsrord, model);
		}
		mtEgsrordService.save(mtEgsrord);
		addMessage(redirectAttributes, "保存收益列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtegsrord/mtEgsrord/?repage";
	}
	
	@RequiresPermissions("mtegsrord:mtEgsrord:edit")
	@RequestMapping(value = "delete")
	public String delete(MtEgsrord mtEgsrord, RedirectAttributes redirectAttributes) {
		mtEgsrordService.delete(mtEgsrord);
		addMessage(redirectAttributes, "删除收益列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtegsrord/mtEgsrord/?repage";
	}

}