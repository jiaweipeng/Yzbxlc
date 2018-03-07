/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.article.web;

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
import com.thinkgem.jeesite.modules.article.entity.MtArticle;
import com.thinkgem.jeesite.modules.article.service.MtArticleService;

/**
 * 文章管理Controller
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/article/mtArticle")
public class MtArticleController extends BaseController {

	@Autowired
	private MtArticleService mtArticleService;
	
	@ModelAttribute
	public MtArticle get(@RequestParam(required=false) String id) {
		MtArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtArticleService.get(id);
		}
		if (entity == null){
			entity = new MtArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("article:mtArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtArticle mtArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtArticle> page = mtArticleService.findPage(new Page<MtArticle>(request, response), mtArticle); 
		model.addAttribute("page", page);
		return "modules/article/mtArticleList";
	}

	@RequiresPermissions("article:mtArticle:view")
	@RequestMapping(value = "form")
	public String form(MtArticle mtArticle, Model model) {
		model.addAttribute("mtArticle", mtArticle);
		return "modules/article/mtArticleForm";
	}

	@RequiresPermissions("article:mtArticle:edit")
	@RequestMapping(value = "save")
	public String save(MtArticle mtArticle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtArticle)){
			return form(mtArticle, model);
		}
		mtArticleService.save(mtArticle);
		addMessage(redirectAttributes, "保存文章管理成功");
		return "redirect:"+Global.getAdminPath()+"/article/mtArticle/?repage";
	}
	
	@RequiresPermissions("article:mtArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(MtArticle mtArticle, RedirectAttributes redirectAttributes) {
		mtArticleService.delete(mtArticle);
		addMessage(redirectAttributes, "删除文章管理成功");
		return "redirect:"+Global.getAdminPath()+"/article/mtArticle/?repage";
	}

}