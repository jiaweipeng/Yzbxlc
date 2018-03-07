/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.repayment.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.xstream.DayWeek;
import com.thinkgem.jeesite.common.xstream.SMS;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.notice.entity.MtNotice;
import com.thinkgem.jeesite.modules.notice.service.MtNoticeService;
import com.thinkgem.jeesite.modules.notice.web.MtNoticeController.NoticeType;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;
import com.thinkgem.jeesite.modules.repayment.service.MtRepaymentService;

/**
 * 还款列表Controller
 * @author jiaweipeng
 * @version 2017-06-16
 */
@Controller
@EnableScheduling
@RequestMapping(value = "${adminPath}/repayment/mtRepayment")
public class MtRepaymentController extends BaseController {

	@Autowired
	protected MtNoticeService mtNoticeService;
	@Autowired
	private MtProductService mtProductService;
	@Autowired
	private MtRepaymentService mtRepaymentService;
	@Autowired
	protected MtUserService mtUserService;
	private String menuType = null;
	
	@ModelAttribute
	public MtRepayment get(@RequestParam(required=false) String id) {
		MtRepayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtRepaymentService.get(id);
		}
		if (entity == null){
			entity = new MtRepayment();
		}
		return entity;
	}
	
	@RequiresPermissions("repayment:mtRepayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtRepayment mtRepayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		menuType = request.getParameter("type");
		switch(Integer.parseInt(menuType)) {
		case 0:break;
		case 1:mtRepayment.setOverdue(1);break;		
		}
		Page<MtRepayment> page = new Page<MtRepayment>();
		page = mtRepaymentService.findPage(new Page<MtRepayment>(request, response), mtRepayment);
		model.addAttribute("type", menuType);
		model.addAttribute("page", page);
		return "modules/repayment/mtRepaymentList";
	}

	@RequiresPermissions("repayment:mtRepayment:view")
	@RequestMapping(value = "form")
	public String form(MtRepayment mtRepayment, Model model) {		
		model.addAttribute("mtRepayment", mtRepayment);
		return "modules/repayment/mtRepaymentForm";
	}

	@RequiresPermissions("repayment:mtRepayment:edit")
	@RequestMapping(value = "save")
	public String save(MtRepayment mtRepayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtRepayment)){
			return form(mtRepayment, model);
		}
		mtRepaymentService.save(mtRepayment);
		addMessage(redirectAttributes, "保存还款列表成功");
		return "redirect:"+Global.getAdminPath()+"/repayment/mtRepayment/?type="+menuType;
	}
	
	@RequiresPermissions("repayment:mtRepayment:edit")
	@RequestMapping(value = "delete")
	public String delete(MtRepayment mtRepayment, RedirectAttributes redirectAttributes) {
		mtRepaymentService.delete(mtRepayment);
		addMessage(redirectAttributes, "删除还款列表成功");
		return "redirect:"+Global.getAdminPath()+"/repayment/mtRepayment/?type="+menuType;
	}
	
	/**
	 * 转BigDecimal类型
	 * @param value
	 * @return
	 */
	protected BigDecimal toDecimal(Double value) {
		return new BigDecimal(value).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 邮件发送
	 * @param id
	 * @param title
	 * @param text
	 */
	protected void mailSend(String email, String title, String text) {
		if(!(email==null) && !(email==null)) {
			SendMailUtil.sendCommonMail(email, title, text);
		}
	}
	
	/**
	 * 站内信发送
	 * @param userid
	 * @param title
	 * @param content
	 */
	private void websiteNotice(Integer userid, String title, String content) {
		MtNotice mtNotice = new MtNotice();
		mtNotice.setTitle(title);
		mtNotice.setContent(content);
		mtNotice.setSender("燕赵百姓理财网");
		mtNotice.setType(NoticeType.SEVEN);
		mtNotice.setIsread(NoticeType.ONE);
		mtNotice.setUserid(userid);
		mtNoticeService.save(mtNotice);
	}
	
	/**
	 * 定时改变逾期状态
	 * @param repayment
	 * @param nowaTime
	 */
	private void changeStatus(MtRepayment repayment, Date nowaTime) {
		try {
			repayment.setState(0); // 为未还款
			String strPlusDate = DayWeek.plusDay(2, DateUtils.formatDate(nowaTime));
			List<MtRepayment> list=mtRepaymentService.findList(repayment);
			for (MtRepayment mtRepayment : list) {
				Date datetime = mtRepayment.getDuedate();
				Date addDate = DateUtils.parseDate(strPlusDate);
				String strDate = DateUtils.formatDate(datetime);
				Date repayDate = DateUtils.parseDate(strDate);
				// 改为逾期状态
				if(datetime.getTime() < nowaTime.getTime()){
					mtRepayment.setState(2);
					mtRepayment.setOverdue(1);
					mtRepaymentService.save(mtRepayment);
				}
				// 还款通知
				Integer iswarn = mtRepayment.getIswarn();
				if(addDate.getTime()==repayDate.getTime() && (iswarn==null || !(iswarn==1))) {
					String title = "还款通知";
					String content = "您在燕赵百姓理财网申请的借款，在"+strDate+"日需要还款，请及时还款";
					
					mtRepayment.setIswarn(1);
					Integer userId = repayment.getUserid();
					MtUser mtUser = mtUserService.get(userId.toString());
					mailSend(mtUser.getUserMail(), title, content);
					SMS.sendout(mtUser.getUserTel(), content);
					websiteNotice(userId, title, content);
					mtRepaymentService.save(mtRepayment);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 计算违约金
	 * @param borrow
	 * @param interest
	 * @param daynum
	 * @return
	 */
	private Double makePenalty(BigDecimal borrow, Double interest, Integer daynum) {
		interest = interest / 100;
		Double penalty = borrow.doubleValue() * interest * daynum;
		return penalty;
	}
	
	/**
	 * 生成违约金:
	 * 逾期(30天内)违约金=借款金额*逾期违约金费率（0.05%）*逾期天数;
	 * 逾期(30天以上)违约金=借款金额*逾期违约金费率（0.05%）*30+借款金额*逾期违
	 * 约金费率(0.1%)*(逾期天数-30);
	 * @param repayment
	 * @param daynum
	 */
	private BigDecimal doPenalty(MtRepayment repayment, Integer daynum) {
		Integer deadline = 30;
		String grade = repayment.getGrade();
		MtProduct mtProduct = mtProductService.findProduct(grade);
		BigDecimal borrow = mtProduct.getPlanmoney();
		Double penalty = null;
		if(daynum<deadline) {
			penalty = makePenalty(borrow, 0.05, daynum);
		} else {
			Double value1 = makePenalty(borrow, 0.05, deadline);
			Double value2 = makePenalty(borrow, 0.1, daynum-deadline);
			penalty = value1 + value2;
		}
		return toDecimal(penalty);
	}
	
	/**
	 * 生成罚息:
	 * 罚息=(逾期本金+逾期利息)*借款利息*50%/365*逾期时间
	 * @param repayment
	 * @param daynum
	 * @return
	 */
	private BigDecimal doDefaultInterest(MtRepayment repayment, Integer daynum) {
		Double finerate = 0.5 / 365;
		String grade = repayment.getGrade();
		BigDecimal yetcapital = repayment.getYetcapital();
		BigDecimal yetinterest = repayment.getYetinterest();
		MtProduct mtProduct = mtProductService.findProduct(grade);
		Double annualrate = mtProduct.getHopepercent().doubleValue()/100;
		Double yetoverdue = yetcapital.doubleValue()+yetinterest.doubleValue();
		Double forfeit = yetoverdue*annualrate*finerate*daynum;
		return toDecimal(forfeit);
	}
	
	/**
	 * 生成逾期金额
	 * @param repayment
	 * @param nowaTime
	 */
	private void makeWithanakin(MtRepayment repayment, Date nowatime) {
		repayment.setState(2); // 2为逾期
		List<MtRepayment> list = mtRepaymentService.findList(repayment);
		for (MtRepayment mtRepayment : list) {
			Date repaytime = mtRepayment.getDuedate();
			Date nowadate = DateUtils.parseDate(DateUtils.formatDate(nowatime));
			Date repaydate = DateUtils.parseDate(DateUtils.formatDate(repaytime));
			Integer overdueDays = DayWeek.makeDaynum(repaydate, nowadate);
			Long current = nowatime.getTime();
			// 逾期操作更新时间
			Long overdue = mtRepayment.getOverduedate().getTime();
			if(repaytime.getTime()<current && overdue<nowadate.getTime()) {
				mtRepayment.setOverduedate(nowadate);
				mtRepayment.setWithanakin(doPenalty(mtRepayment, overdueDays));// 违约金
				mtRepayment.setPenalty(doDefaultInterest(mtRepayment, overdueDays));// 罚息
				mtRepayment.setLatefee(doPenalty(mtRepayment, overdueDays));// 滞纳金
				mtRepayment.setOverduedays(overdueDays.toString());
				mtRepaymentService.save(mtRepayment);
			}
		}
	}
	
	/**
	 * 定时处理逾期操作
	 */
	//@Scheduled(cron="0 0 0,4,8,12,16,20 * * ?")
	@Scheduled(cron="0 0/10 * * * ?")
	public void overdueHandle() {
		logger.info("定时处理逾期操作");
		Date nowaTime = DateUtils.parseDate(DateUtils.getDateTime());
		MtRepayment repayment = new MtRepayment();
		this.changeStatus(repayment, nowaTime);
		makeWithanakin(repayment, nowaTime);
	}

}