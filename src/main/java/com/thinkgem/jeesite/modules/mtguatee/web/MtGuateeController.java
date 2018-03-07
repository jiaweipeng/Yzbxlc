/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtguatee.web;

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
import com.thinkgem.jeesite.modules.mtguatee.entity.MtGuatee;
import com.thinkgem.jeesite.modules.mtguatee.service.MtGuateeService;

/**
 * 担保机构列表Controller
 * @author wuhao
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/mtguatee/mtGuatee")
public class MtGuateeController extends BaseController {

	@Autowired
	private MtGuateeService mtGuateeService;
	
	@ModelAttribute
	public MtGuatee get(@RequestParam(required=false) String id) {
		MtGuatee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtGuateeService.get(id);
		}
		if (entity == null){
			entity = new MtGuatee();
		}
		return entity;
	}
	
	@RequiresPermissions("mtguatee:mtGuatee:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtGuatee mtGuatee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtGuatee> page = mtGuateeService.findPage(new Page<MtGuatee>(request, response), mtGuatee); 
		model.addAttribute("page", page);
		return "modules/mtguatee/mtGuateeList";
	}

	@RequiresPermissions("mtguatee:mtGuatee:view")
	@RequestMapping(value = "form")
	public String form(MtGuatee mtGuatee, Model model) {
		model.addAttribute("mtGuatee", mtGuatee);
		return "modules/mtguatee/mtGuateeForm";
	}

	@RequiresPermissions("mtguatee:mtGuatee:edit")
	@RequestMapping(value = "save")
	public String save(MtGuatee mtGuatee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtGuatee)){
			return form(mtGuatee, model);
		}
		mtGuateeService.save(mtGuatee);
		addMessage(redirectAttributes, "保存担保机构列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtguatee/mtGuatee/?repage";
	}
	
	@RequiresPermissions("mtguatee:mtGuatee:edit")
	@RequestMapping(value = "delete")
	public String delete(MtGuatee mtGuatee, RedirectAttributes redirectAttributes) {
		mtGuateeService.delete(mtGuatee);
		addMessage(redirectAttributes, "删除担保机构列表成功");
		return "redirect:"+Global.getAdminPath()+"/mtguatee/mtGuatee/?repage";
	}

}