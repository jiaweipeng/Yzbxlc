/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.notice.web;

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
import com.thinkgem.jeesite.common.xstream.SMS;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.notice.entity.MtNotice;
import com.thinkgem.jeesite.modules.notice.service.MtNoticeService;

/**
 * 文章管理Controller
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/mtNotice")
public class MtNoticeController extends BaseController {

	@Autowired
	private MtNoticeService mtNoticeService;
	@Autowired
	private MtUserService mtUserService;
	private StringBuffer mobiles = null;
	
	@ModelAttribute
	public MtNotice get(@RequestParam(required=false) String id) {
		MtNotice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtNoticeService.get(id);
		}
		if (entity == null){
			entity = new MtNotice();
		}
		return entity;
	}
	
	/**
	 * 通知列表
	 * @param mtNotice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("notice:mtNotice:view")
	@RequestMapping(value = {"nlist", ""})
	public String nlist(MtNotice mtNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtNotice> page = mtNoticeService.findPage(new Page<MtNotice>(request, response), mtNotice); 
		model.addAttribute("page", page);
		return "modules/notice/mtNoticeList";
	}
	
	/**
	 * 用户列表
	 * @param mtUser
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("notice:mtNotice:view")
	@RequestMapping(value = {"ulist", ""})
	public String ulist(MtUser mtUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtUser> page = mtUserService.findPage(new Page<MtUser>(request, response), mtUser); 
		model.addAttribute("page", page);
		return "modules/notice/mtUserList";
	}
	
	/**
	 * 查看通知内容
	 * @param mtNotice
	 * @param model
	 * @return
	 */
	@RequiresPermissions("notice:mtNotice:view")
	@RequestMapping(value = "form")
	public String form(MtNotice mtNotice, Model model) {
		model.addAttribute("mtNotice", mtNotice);
		return "modules/notice/mtNoticeForm";
	}
	
	/**
	 * 发送通知
	 * @param mtUser
	 * @param model
	 * @return
	 */
	@RequiresPermissions("notice:mtNotice:view")
	@RequestMapping(value = "uform")
	public String uform(MtNotice mtNotice, Model model, HttpServletRequest request) {
		model.addAttribute("mtNotice", mtNotice);
		model.addAttribute("uid", request.getParameter("uid"));
		return "modules/notice/mtUserForm";
	}

	@RequiresPermissions("notice:mtNotice:edit")
	@RequestMapping(value = "save")
	public String save(MtNotice mtNotice, Model model, 
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String email = request.getParameter("email");
		int type = getType(request.getParameter("site"), request.getParameter("phone"), email);
		System.out.println(type);
		if(type == NoticeType.ZERO) {
			addMessage(redirectAttributes, "请选择发送方式");
			return null;
		}
		if (!beanValidator(model, mtNotice)){
			return uform(mtNotice, model, null);
		}
		mobiles = new StringBuffer();
		mtNotice.setSender("燕赵百姓理财网");
		mtNotice.setIsread(NoticeType.ONE);
		String[] uid = request.getParameter("uid").split(",");
		for(int i=0; i<uid.length; i++) {
			Integer id = Integer.parseInt(uid[i]);
			String content = mtNotice.getContent();
			String title = mtNotice.getTitle();
			switch(type) {
			case NoticeType.ONE:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.ONE);
				break;
			case NoticeType.TWO:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.TWO);
				this.addMobiles(id);
				break;
			case NoticeType.THREE:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.THREE);
				this.mailSend(id, title, content);
				break;
			case NoticeType.FOUR:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.FOUR);
				this.addMobiles(id);
				break;
			case NoticeType.FIVE:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.FIVE);
				this.mailSend(id, title, content);
				this.addMobiles(id);
				break;
			case NoticeType.SIX:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.SIX);
				this.mailSend(id, title, content);
				break;
			case NoticeType.SEVEN:
				mtNotice.setUserid(id);
				mtNotice.setType(NoticeType.SEVEN);
				this.mailSend(id, title, content);
				this.addMobiles(id);
				break;
			default : break;
			}
			mtNotice.setId(null);
			mtNoticeService.save(mtNotice);
		}
		if(this.mobiles.length() > 0) {			
			SMS.sendout(this.mobiles.toString(), mtNotice.getContent());
		}
		addMessage(redirectAttributes, "发送通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/mtNotice/ulist?repage";
	}
	
	@RequiresPermissions("notice:mtNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(MtNotice mtNotice, RedirectAttributes redirectAttributes) {
		mtNoticeService.delete(mtNotice);
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/mtNotice/nlist?repage";
	}
	
	/**
	 * 邮件发送
	 * @param id
	 * @param title
	 * @param text
	 */
	private void mailSend(Integer id, String title, String text) {
		MtUser user = mtUserService.get(String.valueOf(id));
		SendMailUtil.sendCommonMail(user.getUserMail(), title, text);
	}
	
	/**
	 * 累加手机号
	 * @param id 用户ID
	 */
	private void addMobiles(Integer id) {
		MtUser user = mtUserService.get(String.valueOf(id));
		System.out.println(user.getUserTel());
		if(user.getUserTel()!=null && user.getUserTel()!="") {
			if(mobiles.length() == 0) {
				mobiles.append(user.getUserTel());
			} else {
				mobiles.append(","+user.getUserTel());
			}
		}
	}
	
	private String strChange(String zifu) {
		if(zifu == null || zifu.length() == 0) {
			zifu = NoticeType.STR_ZERO;
		} else {
			zifu = NoticeType.STR_ONE;
		}
		return zifu;
	}
	
	private int getType(String site, String phone, String email) {
		int index = 0;		
		StringBuilder type = new StringBuilder();
		String[] strType = {"000","100","010","001","110","011","101","111"};
		type.append(strChange(site)).append(strChange(phone)).append(strChange(email));
		for(int i=0; i<strType.length; i++) {
			if(strType[i].equals(type.toString())) {
				index = i; break;
			}
		}
		return index;
	}
	
	public static class NoticeType {
		public static final int ZERO = 0;
		public static final int ONE = 1; 
		public static final int TWO = 2;
		public static final int THREE = 3;
		public static final int FOUR = 4;
		public static final int FIVE = 5;
		public static final int SIX = 6;
		public static final int SEVEN = 7;
		
		public static final String STR_ZERO = "0";
		public static final String STR_ONE = "1";
	}

}