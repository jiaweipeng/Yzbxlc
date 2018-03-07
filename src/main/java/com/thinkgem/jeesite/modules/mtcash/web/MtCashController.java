/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtcash.web;

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
import com.thinkgem.jeesite.modules.mtcash.entity.MtCash;
import com.thinkgem.jeesite.modules.mtcash.service.MtCashService;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;

/**
 * 提现列表Controller
 * @author wuhao
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/mtcash/mtCash")
public class MtCashController extends BaseController {

	@Autowired
	private MtCashService mtCashService;
	@Autowired
	private MtUserService mtuserservice;
	
	@ModelAttribute
	public MtCash get(@RequestParam(required=false) String id) {
		MtCash entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtCashService.get(id);
		}
		if (entity == null){
			entity = new MtCash();
		}
		return entity;
	}
	
	
	//用户列表
	@RequiresPermissions("mtcash:mtCash:view")
	@RequestMapping(value = {"userlist", ""})
	public String mtuserlist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtUser> page = mtuserservice.findPage(new Page<MtUser>(request, response), mtUser); 		
		model.addAttribute("page", page);
		  return "modules/mtcash/Userlist";
	}
		
	
	//提现列表
	@RequiresPermissions("mtcash:mtCash:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtCash mtCash, HttpServletRequest request, HttpServletResponse response, Model model) {
		String userid = request.getParameter("id");
		System.out.println("提现==============================="+userid);
		if(!(userid==null)&&userid!=""){
			System.out.println("进去"+userid);
			mtCash.setMtUserId(Integer.parseInt(userid));
		}
		Page<MtCash> page = mtCashService.findPage(new Page<MtCash>(request, response), mtCash); 
		model.addAttribute("page", page);
		model.addAttribute("userid", userid);
		
		return "modules/mtcash/mtCashList";
	}

	@RequiresPermissions("mtcash:mtCash:view")
	@RequestMapping(value = "form")
	public String form(MtCash mtCash, Model model) {
		model.addAttribute("mtCash", mtCash);
		return "modules/mtcash/mtCashForm";
	}

	@RequiresPermissions("mtcash:mtCash:edit")
	@RequestMapping(value = "save")
	public String save(MtCash mtCash, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtCash)){
			return form(mtCash, model);
		}
		mtCashService.save(mtCash);
		addMessage(redirectAttributes, "保存提现成功成功");
		return "redirect:"+Global.getAdminPath()+"/mtcash/mtCash/?repage";
	}
	
	@RequiresPermissions("mtcash:mtCash:edit")
	@RequestMapping(value = "delete")
	public String delete(MtCash mtCash, RedirectAttributes redirectAttributes) {
		mtCashService.delete(mtCash);
		addMessage(redirectAttributes, "删除提现成功成功");
		return "redirect:"+Global.getAdminPath()+"/mtcash/mtCash/?repage";
	}

}