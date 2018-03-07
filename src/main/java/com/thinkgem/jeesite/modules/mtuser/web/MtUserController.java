/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtuser.web;

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
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

/**
 * 客户管理Controller
 * @author fengtao
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/mtuser/mtUser")
public class MtUserController extends BaseController {

	@Autowired
	private MtUserService mtUserService;
	private static MtProductDao  productDao = SpringContextHolder.getBean(MtProductDao.class);
	
	@ModelAttribute
	public MtUser get(@RequestParam(required=false) String id) {
		MtUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtUserService.get(id);
		}
		if (entity == null){
			entity = new MtUser();
		}
		return entity;
	}
	
	@RequiresPermissions("mtuser:mtUser:view")
	@RequestMapping(value = {"userlist", ""})
	public String userlist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser); 
		model.addAttribute("page", page);
		return "modules/mtuser/mtUserList";
	}
	
	

    //经纪人列表
	@RequiresPermissions("mtuser:mtUser:agent:view")
	@RequestMapping(value = {"agentList", ""})
	public String agentList(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		mtUser.setUserStatus(3);
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser); 
		model.addAttribute("page", page);
		return "modules/agent/agentList";
	}

	@RequiresPermissions("mtuser:mtUser:view")
	@RequestMapping(value = "form")
	public String form(MtUser mtUser, Model model) {
		model.addAttribute("mtUser", mtUser);
		return "modules/mtuser/mtUserForm";
	}
	//经纪人
	@RequiresPermissions("mtuser:mtUser:agent:edit")
	@RequestMapping(value = "agentForm")
	public String agentform(MtUser mtUser, Model model) {
		model.addAttribute("mtUser", mtUser);
		return "modules/agent/agentForm";
	}


	@RequiresPermissions("mtuser:mtUser:edit")
	@RequestMapping(value = "save")
	public String save(MtUser mtUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtUser)){
			return form(mtUser, model);
		}
		mtUserService.save(mtUser);
		addMessage(redirectAttributes, "保存客户成功");
		return "redirect:"+Global.getAdminPath()+"/mtuser/mtUser/userlist";
	}
	
	
	
	@RequiresPermissions("mtuser:mtUser:edit")
	@RequestMapping(value = "delete")
	public String delete(MtUser mtUser, RedirectAttributes redirectAttributes) {
		mtUserService.delete(mtUser);
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.getAdminPath()+"/mtuser/mtUser/userlist";
	}
/*****************注释列表********************/
	//经纪人管理列表
	@RequiresPermissions("mtuser:mtUser:agent:view")
	@RequestMapping(value = {"agentPassList", ""})
	public String agentPassList(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		mtUser.setUserType(2);
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser); 
		
		model.addAttribute("page", page);
		
		return "modules/agent/agentPassList";
	}

/*************注释代买*****************/	
	//经纪人产品列表
	@RequiresPermissions("mtuser:mtUser:view")
	@RequestMapping(value = {"agentprolist", ""})
	public String agentprolist(MtProduct pro, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String useragentid = request.getParameter("useragentid");
		pro.setUseragent(Integer.parseInt(useragentid));
		List<MtProduct> products = productDao.findListByagent(pro);
		System.out.println(products.size());
		model.addAttribute("products", products);
		return "modules/agent/agentProList";
	}
	
	/***************注释代买向上↑**************/
	

}