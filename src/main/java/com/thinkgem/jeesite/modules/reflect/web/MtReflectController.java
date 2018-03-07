/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reflect.web;

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
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.reflect.entity.MtReflect;
import com.thinkgem.jeesite.modules.reflect.service.MtReflectService;

/**
 * 体现成功Controller
 * @author wuhao
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/reflect/mtReflect")
public class MtReflectController extends BaseController {

	@Autowired
	private MtReflectService mtReflectService;
	
	@Autowired
	private MtUserService mtuserservice;
	
	@ModelAttribute
	public MtReflect get(@RequestParam(required=false) String id) {
		MtReflect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtReflectService.get(id);
		}
		if (entity == null){
			entity = new MtReflect();
		}
		return entity;
	}
	
	
		//用户列表
		@RequiresPermissions("reflect:mtReflect:view")
		@RequestMapping(value = {"userlist", ""})
		public String mtuserlist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<MtUser> page = mtuserservice.findPage(new Page<MtUser>(request, response), mtUser); 
			
			model.addAttribute("page", page);
			return "modules/reflect/ReflectUserlist";
		}
	
	
	
	
	
	//提现列表
	@RequiresPermissions("reflect:mtReflect:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtReflect mtReflect, HttpServletRequest request, HttpServletResponse response, Model model) {
		String userid = request.getParameter("id");
		System.out.println("提现==============================="+userid);
		mtReflect.setMtUserId(Integer.parseInt(userid));
		Page<MtReflect> page = mtReflectService.findPage(new Page<MtReflect>(request, response), mtReflect); 
		model.addAttribute("page", page);
		return "modules/reflect/mtReflectList";
	}

	
	
	
	@RequiresPermissions("reflect:mtReflect:view")
	@RequestMapping(value = "form")
	public String form(MtReflect mtReflect, Model model) {
		model.addAttribute("mtReflect", mtReflect);
		return "modules/reflect/mtReflectForm";
	}

	@RequiresPermissions("reflect:mtReflect:edit")
	@RequestMapping(value = "save")
	public String save(MtReflect mtReflect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtReflect)){
			return form(mtReflect, model);
		}
		mtReflectService.save(mtReflect);
		addMessage(redirectAttributes, "保存体现成功成功");
		return "redirect:"+Global.getAdminPath()+"/reflect/mtReflect/?repage";
	}
	
	@RequiresPermissions("reflect:mtReflect:edit")
	@RequestMapping(value = "delete")
	public String delete(MtReflect mtReflect, RedirectAttributes redirectAttributes) {
		mtReflectService.delete(mtReflect);
		addMessage(redirectAttributes, "删除体现成功成功");
		return "redirect:"+Global.getAdminPath()+"/reflect/mtReflect/?repage";
	}

}