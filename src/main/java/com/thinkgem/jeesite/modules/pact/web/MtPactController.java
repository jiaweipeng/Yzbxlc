/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pact.web;

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
import com.thinkgem.jeesite.modules.auth.entity.MtAuth;
import com.thinkgem.jeesite.modules.pact.entity.MtPact;
import com.thinkgem.jeesite.modules.pact.service.MtPactService;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;

/**
 * 合同管理Controller
 * @author shouming
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pact/mtPact")
public class MtPactController extends BaseController {

	@Autowired
	private MtPactService mtPactService;

	@Autowired
	private MtProductService mtProductService;
	
	@ModelAttribute
	public MtPact get(@RequestParam(required=false) String id) {
		MtPact entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtPactService.get(id);
		}
		if (entity == null){
			entity = new MtPact();
		}
		return entity;
	}
	
	@RequiresPermissions("pact:mtPact:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtPact mtPact, HttpServletRequest request, HttpServletResponse response, Model model) {
		String pid=request.getParameter("pid");
		mtPact.setPid(pid);
		Page<MtPact> page =null;
		
		page = mtPactService.findPage(new Page<MtPact>(request, response), mtPact);
		model.addAttribute("page", page);
		
		model.addAttribute("pactpid", pid);
		return "modules/pact/mtPactList";
	}
	
	
	
	

	@RequiresPermissions("pact:mtPact:view")
	@RequestMapping(value = "form")
	public String form(MtPact mtPact, Model model) {
		model.addAttribute("mtPact", mtPact);
		//
		//model.addAttribute("mtPactid", mtPact.getId());
		return "modules/pact/mtPactForm";
	}

	@RequiresPermissions("pact:mtPact:edit")
	@RequestMapping(value = "save")
	public String save(MtPact mtPact, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtPact)){
			return form(mtPact, model);
		}
		mtPactService.save(mtPact);
		addMessage(redirectAttributes, "保存合同成功");
		return "redirect:"+Global.getAdminPath()+"/pact/mtPact/list?pid="+mtPact.getPid()+"&type="+mtPact.getPactType()+"&repage";
	}

	//打开协议列表， 首先显示产品信息， 然后点击查看投资或借款人的协议 
	@RequiresPermissions("product:mtProduct:view")
	@RequestMapping(value = {"productlist", ""})
	public String productlist(MtProduct mtProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtProduct> page = null;
		//只显示还款中的状态()
		mtProduct.setStatus(6);
		page = mtProductService.findPage(new Page<MtProduct>(request, response), mtProduct);
		model.addAttribute("page", page);
		return "modules/pact/mtProductList";
	}
	
	
	@RequiresPermissions("pact:mtPact:edit")
	@RequestMapping(value = "delete")
	public String delete(MtPact mtPact, RedirectAttributes redirectAttributes) {
		mtPactService.delete(mtPact);
		addMessage(redirectAttributes, "删除合同成功");
		return "redirect:"+Global.getAdminPath()+"/pact/mtPact/list?pid="+mtPact.getPid()+"&type="+mtPact.getPactType()+"&repage";
	}

}