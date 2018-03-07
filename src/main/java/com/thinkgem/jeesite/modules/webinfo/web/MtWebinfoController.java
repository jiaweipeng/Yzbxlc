/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webinfo.web;

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
import com.thinkgem.jeesite.modules.webinfo.entity.MtWebinfo;
import com.thinkgem.jeesite.modules.webinfo.service.MtWebinfoService;

/**
 * 网站信息Controller
 * @author wuhao
 * @version 2017-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/webinfo/mtWebinfo")
public class MtWebinfoController extends BaseController {

	@Autowired
	private MtWebinfoService mtWebinfoService;
	
	@ModelAttribute
	public MtWebinfo get(@RequestParam(required=false) String id) {
		MtWebinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtWebinfoService.get(id);
		}
		if (entity == null){
			entity = new MtWebinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("webinfo:mtWebinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtWebinfo mtWebinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtWebinfo> page = mtWebinfoService.findPage(new Page<MtWebinfo>(request, response), mtWebinfo); 
		model.addAttribute("page", page);
		return "modules/webinfo/mtWebinfoList";
	}

	@RequiresPermissions("webinfo:mtWebinfo:view")
	@RequestMapping(value = "form")
	public String form(MtWebinfo mtWebinfo, Model model) {
		model.addAttribute("mtWebinfo", mtWebinfo);
		return "modules/webinfo/mtWebinfoForm";
	}

	@RequiresPermissions("webinfo:mtWebinfo:edit")
	@RequestMapping(value = "save")
	public String save(MtWebinfo mtWebinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtWebinfo)){
			return form(mtWebinfo, model);
		}
		mtWebinfoService.save(mtWebinfo);
		addMessage(redirectAttributes, "保存网站信息成功");
		return "redirect:"+Global.getAdminPath()+"/webinfo/mtWebinfo/?repage";
	}
	
	@RequiresPermissions("webinfo:mtWebinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MtWebinfo mtWebinfo, RedirectAttributes redirectAttributes) {
		mtWebinfoService.delete(mtWebinfo);
		addMessage(redirectAttributes, "删除网站信息成功");
		return "redirect:"+Global.getAdminPath()+"/webinfo/mtWebinfo/?repage";
	}

}