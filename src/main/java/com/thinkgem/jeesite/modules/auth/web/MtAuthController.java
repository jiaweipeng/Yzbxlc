/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auth.web;

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
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.auth.dao.MtAuthDao;
import com.thinkgem.jeesite.modules.auth.entity.MtAuth;
import com.thinkgem.jeesite.modules.auth.service.MtAuthService;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.repayment.dao.MtRepaymentDao;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;
import com.thinkgem.jeesite.modules.repayment.service.MtRepaymentService;

/**
 * 认证管理Controller
 * @author shouming
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/auth/mtAuth")
public class MtAuthController extends BaseController {
	
	@Autowired
	private MtAuthService mtAuthService;
	@Autowired
	private MtUserService mtUserService;
	
	private static MtAuthDao authDao = SpringContextHolder.getBean(MtAuthDao.class);
	
	MtUserService mtusers=new MtUserService();
	//WebUserService userservice = new WebUserServiceImpl();
	
	@ModelAttribute
	public MtAuth get(@RequestParam(required=false) String id) {
		MtAuth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtAuthService.get(id);
		}
		if (entity == null){
			entity = new MtAuth();
		}
		return entity;
	}
	
	@RequiresPermissions("auth:mtAuth:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtAuth mtAuth, HttpServletRequest request, HttpServletResponse response, Model model) {
		String type=request.getParameter("type");
		Page<MtAuth> page =null;
		//实名认证
		if(type.equals("1")){		
			mtAuth.setAuthType(1);
			//8.15修改  只显示未认证的信息
			
			mtAuth.setAuthState(2);
			
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth); 
		}else if(type.equals("2")){		
			//银行卡认证
			mtAuth.setAuthType(2);
			mtAuth.setAuthState(2);
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth); 
		}else if(type.equals("3")){
			//手机认证
			mtAuth.setAuthType(3);
			mtAuth.setAuthState(2);
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth);
		}else if(type.equals("4")){
			//现场认证
			mtAuth.setAuthType(4);
			mtAuth.setAuthState(2);
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth);
		}else if(type.equals("5")){
			//视频认证
			mtAuth.setAuthType(5);
			mtAuth.setAuthState(2);
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth);
		}else if(type.equals("6")){
			//邮箱认证
			mtAuth.setAuthType(6);
			mtAuth.setAuthState(2);
			page=mtAuthService.findPage(new Page<MtAuth>(request, response), mtAuth);
		}
		model.addAttribute("page", page);
		model.addAttribute("authtype",type);
		return "modules/auth/mtAuthList";
	}
	
	//认证列表读取user表
	@RequiresPermissions("auth:mtAuth:view")
	@RequestMapping(value = {"ulist", ""})
	public String ulist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser); 
		model.addAttribute("page", page);
		return "modules/auth/mtUserList";
	}

	@RequiresPermissions("auth:mtAuth:view")
	@RequestMapping(value = "form")
	public String form(MtAuth mtAuth, Model model,HttpServletRequest request, HttpServletResponse response) {
		String type =request.getParameter("type");
		model.addAttribute("mtAuth", mtAuth);
		model.addAttribute("type",type);
		return "modules/auth/mtAuthForm";
	}
	
	
	@RequiresPermissions("auth:mtAuth:edit")
	@RequestMapping(value = "save")
	public String save(MtAuth mtAuth, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtAuth)){
			return form(mtAuth, model, null, null);
		}
		
		//当进入实名认证
		if(mtAuth.getAuthType().equals(1)){
			Integer mtusers= mtAuth.getUserid();
			MtUser user = mtUserService.get(String.valueOf(mtusers));
			mtAuthService.save(mtAuth);
			if( mtAuth.getAuthState()==1&&mtAuth.getAuthState()!=null){
				user.setIsAuth(1);
				mtUserService.save(user);
			}else{
				user.setIsAuth(2);
				mtUserService.save(user);
			}
		}else if(mtAuth.getAuthType().equals(2)){//当进入银行卡认证
				Integer mtusers= mtAuth.getUserid();
				MtUser user= mtUserService.get(String.valueOf(mtusers));
				mtAuthService.save(mtAuth);
				if(mtAuth.getAuthState()==1&&mtAuth.getAuthState()!=null){
					user.setIsBindBank(1);
					mtUserService.save(user);
				}else{
					user.setIsBindBank(2);
					mtUserService.save(user);
				}
		}else if(mtAuth.getAuthType().equals(5)){//当进入视频认证
			Integer mtusers=mtAuth.getUserid();
			MtUser user= mtUserService.get(String.valueOf(mtusers));
			mtAuthService.save(mtAuth);
			if(mtAuth.getAuthState()==1&&mtAuth.getAuthState()!=null){
				user.setIsVideoAuth(1);
				mtUserService.save(user);
			}else{
				user.setIsVideoAuth(2);
				mtUserService.save(user);
			}
		}
		mtAuthService.save(mtAuth);
		addMessage(redirectAttributes, "保存认证成功");
		return "redirect:"+Global.getAdminPath()+"/auth/mtAuth/list?type="+mtAuth.getAuthType()+"&repage";
	}
	
	@RequiresPermissions("auth:mtAuth:edit")
	@RequestMapping(value = "delete")
	public String delete(MtAuth mtAuth, RedirectAttributes redirectAttributes) {
		mtAuthService.delete(mtAuth);
		addMessage(redirectAttributes, "删除认证成功");
		return "redirect:"+Global.getAdminPath()+"/auth/mtAuth/list?type="+mtAuth.getAuthType()+"&repage";
	}

}