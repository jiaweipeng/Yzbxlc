/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtrecharge.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mtrecharge.entity.MtRecharge;
import com.thinkgem.jeesite.modules.mtrecharge.service.MtRechargeService;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;

/**
 * 充值记录Controller
 * @author wuhao
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/mtrecharge/mtRecharge")
public class MtRechargeController extends BaseController {

	@Autowired
	private MtRechargeService mtRechargeService;
	@Autowired
	private MtUserService mtuserservice;
	
	
	@ModelAttribute
	public MtRecharge get(@RequestParam(required=false) String id) {
		MtRecharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtRechargeService.get(id);
		}
		if (entity == null){
			entity = new MtRecharge();
		}
		return entity;
	}
	
	
	
	//用户列表
	@RequiresPermissions("mtrecharge:mtRecharge:view")
	@RequestMapping(value = {"userlist", ""})
	public String mtuserlist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<MtUser> page = mtuserservice.findPage(new Page<MtUser>(request, response), mtUser); 
			model.addAttribute("page", page);
		    return "modules/mtrecharge/Userlist";
	}
		
	//充值列表
	@RequiresPermissions("mtrecharge:mtRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtRecharge mtRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		String userid = request.getParameter("id");
		System.out.println("充值==============================="+userid);
		if(!(userid==null)&&userid!=""){
			mtRecharge.setMtUserId(Integer.parseInt(userid));
		}
		Page<MtRecharge> page = mtRechargeService.findPage(new Page<MtRecharge>(request, response), mtRecharge); 
		model.addAttribute("page", page);
		model.addAttribute("userid", userid);
		return "modules/mtrecharge/mtRechargeList";
	}

	@RequiresPermissions("mtrecharge:mtRecharge:view")
	@RequestMapping(value = "form")
	public String form(MtRecharge mtRecharge, Model model) {
		model.addAttribute("mtRecharge", mtRecharge);
		return "modules/mtrecharge/mtRechargeForm";
	}

	@RequiresPermissions("mtrecharge:mtRecharge:edit")
	@RequestMapping(value = "save")
	public String save(MtRecharge mtRecharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtRecharge)){
			return form(mtRecharge, model);
		}
		mtRechargeService.save(mtRecharge);
		addMessage(redirectAttributes, "保存充值成功成功");
		return "redirect:"+Global.getAdminPath()+"/mtrecharge/mtRecharge/?repage";
	}
	
	@RequiresPermissions("mtrecharge:mtRecharge:edit")
	@RequestMapping(value = "delete")
	public String delete(MtRecharge mtRecharge, RedirectAttributes redirectAttributes) {
		mtRechargeService.delete(mtRecharge);
		addMessage(redirectAttributes, "删除充值成功成功");
		return "redirect:"+Global.getAdminPath()+"/mtrecharge/mtRecharge/?repage";
	}

}