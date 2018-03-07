/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.product.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.xstream.DES;
import com.thinkgem.jeesite.common.xstream.DayWeek;
import com.thinkgem.jeesite.common.xstream.SMS;
import com.thinkgem.jeesite.modules.mtegsrord.entity.MtEgsrord;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtloanrd.entity.MtLoanrd;
import com.thinkgem.jeesite.modules.mtloanrd.web.Loanrd;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.notice.entity.MtNotice;
import com.thinkgem.jeesite.modules.notice.web.MtNoticeController.NoticeType;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;

/**
 * 产品管理Controller
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Controller
@EnableScheduling
@RequestMapping(value = "${adminPath}/product/mtProduct")
public class MtProductController extends HuanXun {
	
	@Autowired
	private MtProductService mtProductService;
	private String menuType = null;
	
	@ModelAttribute
	public MtProduct get(@RequestParam(required=false) String id) {
		MtProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtProductService.get(id);
		}
		if (entity == null){
			entity = new MtProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("product:mtProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtProduct mtProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MtProduct> page = null;
		menuType = request.getParameter("type");
		switch(Integer.parseInt(menuType)) {
		case ProStatus.FIVE:break;
		case ProStatus.ONE:mtProduct.setStatus(ProStatus.ONE);break;
		case ProStatus.NINE:mtProduct.setStatus(ProStatus.NINE);break;
		case ProStatus.ZERO:mtProduct.setStatus(ProStatus.ZERO);break;
		case ProStatus.FOUR:mtProduct.setStatus(ProStatus.FOUR);break;
		case ProStatus.THREE:mtProduct.setStatus(ProStatus.THREE);break;
		case ProStatus.TEN:mtProduct.setStatus(ProStatus.TEN);break;
		case ProStatus.SIX:mtProduct.setStatus(ProStatus.SIX);break;
		}
		
		this.regProject(new MtProduct(), new MtUser());
		
		model.addAttribute("type", Integer.parseInt(menuType));
		page = mtProductService.findPage(new Page<MtProduct>(request, response), mtProduct);
		model.addAttribute("page", page);
		return "modules/product/mtProductList";
	}

	@RequiresPermissions("product:mtProduct:view")
	@RequestMapping(value = "form")
	public String form(MtProduct mtProduct, Model model, HttpServletRequest request) {
		String pid = mtProduct.getPid();
		Integer status = mtProduct.getStatus();
		if(!(status==null) && (status==0)) {
			if(pid==null || pid.equals("")) {
				mtProduct.setPid(doOrderNum());
			}
		}
		String keyt = request.getParameter("keyt");
		MtUser mtUser = mtUserService.get(String.valueOf(mtProduct.getUserid()));
		model.addAttribute("mtProduct", mtProduct);
		model.addAttribute("type", menuType);
		model.addAttribute("mtUser", mtUser);
		if(!(keyt==null) && Integer.parseInt(keyt)==ProStatus.FOUR) {
			return "modules/product/mtLoanForm";
		} else if(!(keyt==null) && Integer.parseInt(keyt)==ProStatus.ZERO) {
			return "modules/product/mtIssueForm";
		} else if(!(keyt==null) && Integer.parseInt(keyt)==ProStatus.TEN) {
			return "modules/product/mtLiubiaoForm";
		}
		return "modules/product/mtProductForm";
	}

	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "save")
	public String save(MtProduct mtProduct, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtProduct)){
			return form(mtProduct, model, null);
		}
		this.usersave(request);
		mtProductService.save(mtProduct);
		addMessage(redirectAttributes, "保存产品成功");
		return "redirect:"+Global.getAdminPath()+"/product/mtProduct/?type="+menuType;
	}
	
	/**
	 * 发标操作
	 * @param mtProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "issue")
	public String issue(MtProduct mtProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtProduct)){
			return form(mtProduct, model, null);
		}
		mtProduct.setIvable(mtProduct.getPlanmoney());
		String userid = String.valueOf(mtProduct.getUserid());
		MtUser mtUser = mtUserService.get(userid);
		String result = this.regProject(mtProduct, mtUser);
		JSONObject head = this.getHead(result);
		if(isLoanSuccess(head)) {
			mtProduct.setStatus(ProStatus.THREE);
			this.regProjectCallback(result, mtProduct);
			addMessage(redirectAttributes, "发标成功");
		} else {
			addMessage(redirectAttributes, "发标失败");
		}
		return "redirect:"+Global.getAdminPath()+"/product/mtProduct/?type="+menuType;
	}
	
	/**
	 * 放款操作
	 * @param mtProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "loan")
	public String loan(MtProduct mtProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtProduct)){
			return form(mtProduct, model, null);
		}
		String result = this.makeLoan(mtProduct);
		JSONObject head = this.getHead(result);
		if(isLoanSuccess(head)) {
			this.doLucreRepay(mtProduct);
			this.makeAgreement(mtProduct);
			this.loanNotice(mtProduct);
			mtProduct.setStatus(ProStatus.SIX);
			mtProductService.save(mtProduct);
			addMessage(redirectAttributes, "放款成功");
		} else {
			addMessage(redirectAttributes, "放款失败");
		}
		return "redirect:"+Global.getAdminPath()+"/product/mtProduct/?type="+menuType;
	}
	
	/**
	 * 流标操作
	 * @param mtProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "liubiao")
	public String liubiao(MtProduct mtProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mtProduct)){
			return form(mtProduct, model, null);
		}
		Integer failNum = this.makeLiubiao(mtProduct);
		if(failNum == 0) {
			if(mtProduct.getStatus() == ProStatus.TEN) {
				mtProduct.setStatus(ProStatus.ELEVEN);
			} else {
				mtProduct.setStatus(ProStatus.FIVE);
			}
			mtProductService.save(mtProduct);
			addMessage(redirectAttributes, "操作完成，流标成功");
		} else {
			addMessage(redirectAttributes, "操作完成，有"+failNum+"个用户投资金额解冻失败");
		}
		return "redirect:"+Global.getAdminPath()+"/product/mtProduct/?type="+menuType;
	}
	
	/**
	 * 放款通知
	 * @param mtProduct
	 */
	private void loanNotice(MtProduct mtProduct) {
		String title = "放款通知";
		String content = "您在燕赵百姓理财网申请的借款已成功放款，请及时查收";
		
		Integer userId = mtProduct.getUserid();
		MtUser mtUser = mtUserService.get(userId.toString());
		mailSend(mtUser.getUserMail(), title, content);
		SMS.sendout(mtUser.getUserTel(), content);
		siteNotice(userId, title, content);
	}
	
	/**
	 * 生成收益列表和还款列表
	 * @param mtProduct
	 */
	private void doLucreRepay(MtProduct mtProduct) {
		// 借款类型条件判断
		if(mtProduct.getUnit() == 1) {
			Double interest = makeDayEgsrord(mtProduct);
			makeDayRepayment(mtProduct, interest);
		} else {
			makeMonthEgsrord(mtProduct);
		}
	}
	
	/**
	 * 按天计算收益列表	 
	 * @param mtProduct
	 * @return
	 */
	private Double makeDayEgsrord(MtProduct mtProduct) {
		// 标号
		String Pid = mtProduct.getPid();
		// 借款周期
		Integer cycle = mtProduct.getCycle();
		// 年利率
		Float hopepercent = mtProduct.getHopepercent();
		String userid = String.valueOf(mtProduct.getUserid());
		MtUser mtUser = mtUserService.get(userid);
		MtInvest invest = new MtInvest();
		invest.setProductid(mtProduct.getId());
		Double totalIint = new Double(0);
		List<MtInvest> mtInvest = mtInvestService.findList(invest);
		for(int index=0; index<mtInvest.size(); index++) {
			String investId = mtInvest.get(index).getId();
			BigDecimal mount = mtInvest.get(index).getMount();
			MtEgsrord mtEgsrord = new MtEgsrord();
			// 按天计算，到期全额还款
			mtEgsrord.setType(0);
			mtEgsrord.setProjectNo(Pid);
			mtEgsrord.setReceivable(mount);
			mtEgsrord.setNumpds(String.valueOf(1));
			String recenttime = DayWeek.plusDay2(cycle);
			mtEgsrord.setRecenttime(doRepayDate(recenttime));
			mtEgsrord.setMtInvestId(Integer.parseInt(investId));
			mtEgsrord.setMtUserId(Integer.parseInt(mtUser.getId()));
			Double interest = doInterest(mount, hopepercent, 365);
			BigDecimal addInterest = toDecimal(interest*cycle);
			totalIint += addInterest.doubleValue();
			mtEgsrord.setInterest(addInterest);
			mtEgsrordService.save(mtEgsrord);
		}
		return totalIint;
	}
	
	/**
	 * 按天计算还款列表
	 * @param mtProduct
	 * @param interest
	 */
	private void makeDayRepayment(MtProduct mtProduct, Double interest) {
		// 标号
		String pid = mtProduct.getPid();
		// 借款金额
		BigDecimal planmoney = mtProduct.getPlanmoney();
		// 借款周期
		Integer cycle = mtProduct.getCycle();		
		String userid = String.valueOf(mtProduct.getUserid());
		MtRepayment mtRepayment = new MtRepayment();
		MtUser mtUser = mtUserService.get(userid);
		// 按天计算，到期全额还款
		mtRepayment.setGrade(pid);
		mtRepayment.setNumperiods(1);
		String duedate = DayWeek.plusDay2(cycle);
		mtRepayment.setDuedate(doRepayDate(duedate));
		mtRepayment.setUserid(Integer.parseInt(mtUser.getId()));
		mtRepayment.setOverduedate(DateUtils.parseDate(DateUtils.getDate()));
		mtRepayment.setYetinterest(toDecimal(interest));
		mtRepayment.setYetcapital(planmoney);
		mtRepayment.setOverdue(0);
		mtRepayment.setState(0);
		mtRepaymentService.save(mtRepayment);
	}
	
	/**
	 * 按月计算收益列表
	 * @param mtProduct
	 * @return
	 */
	private void makeMonthEgsrord(MtProduct mtProduct) {
		// 标号
		String Pid = mtProduct.getPid();
		// 借款周期
		Integer cycle = mtProduct.getCycle();
		// 结算方式
		Integer balancetype = mtProduct.getBalancetype();
		// 年利率
		Float hopepercent = mtProduct.getHopepercent();
		String userid = String.valueOf(mtProduct.getUserid());
		MtUser mtUser = mtUserService.get(userid);
		MtInvest invest = new MtInvest();
		invest.setProductid(mtProduct.getId());
		List<MtInvest> mtInvest = mtInvestService.findList(invest);
		for(int i=0,periods=1; i<cycle; i++,periods++) {
			Double totalIint = new Double(0);
			for(int index=0; index<mtInvest.size(); index++) {
				MtEgsrord mtEgsrord = new MtEgsrord();
				String investId = mtInvest.get(index).getId();
				BigDecimal mount = mtInvest.get(index).getMount();
				mtEgsrord.setMtInvestId(Integer.parseInt(investId));
				mtEgsrord.setRecenttime(doRepayDate(DayWeek.plusMonth(periods)));
				BigDecimal interest = toDecimal(doInterest(mount, hopepercent, 12));
				mtEgsrord.setMtUserId(Integer.parseInt(mtUser.getId()));
				mtEgsrord.setNumpds(String.valueOf(periods));
				totalIint += interest.doubleValue();
				mtEgsrord.setInterest(interest);
				mtEgsrord.setProjectNo(Pid);
				mtEgsrord.setType(0);
				if(balancetype.intValue() == 1) {
					// 等额本息
					BigDecimal bdCycle = new BigDecimal(cycle);
					mtEgsrord.setReceivable(mount.divide(bdCycle, 2, BigDecimal.ROUND_HALF_UP));
				} else if(balancetype.intValue() == 0) {
					// 月还息到期本
					if(periods == cycle) {
						mtEgsrord.setReceivable(mount);
					} else {
						mtEgsrord.setReceivable(new BigDecimal(0));
					}
				}
				mtEgsrordService.save(mtEgsrord);
			}
			makeMonthRepayment(mtProduct, totalIint, periods);
		}		
	}
	
	/**
	 * 按月计算还款列表
	 * @param mtProduct
	 * @param interest
	 * @param periods
	 */
	private void makeMonthRepayment(MtProduct mtProduct, Double interest, int periods) {
		// 标号
		String pid = mtProduct.getPid();
		// 借款金额
		BigDecimal planmoney = mtProduct.getPlanmoney();
		// 借款周期
		Integer cycle = mtProduct.getCycle();
		// 结算方式
		Integer balancetype = mtProduct.getBalancetype();
		String userid = String.valueOf(mtProduct.getUserid());
		MtUser mtUser = mtUserService.get(userid);
		MtRepayment mtRepayment = new MtRepayment();
		// 按月为单位计算
		mtRepayment.setState(0);
		mtRepayment.setGrade(pid);
		mtRepayment.setOverdue(0);
		if(balancetype.intValue() == 1) {
			// 等额本息
			BigDecimal bdCycle = new BigDecimal(cycle);
			mtRepayment.setYetcapital(planmoney.divide(bdCycle, 2, BigDecimal.ROUND_HALF_UP));
		} else if(balancetype.intValue() == 0) {
			// 月还息到期本
			if(periods == cycle) {
				mtRepayment.setYetcapital(planmoney);
			} else {
				mtRepayment.setYetcapital(new BigDecimal(0));
			}
		}
		mtRepayment.setNumperiods(periods);
		mtRepayment.setYetinterest(toDecimal(interest));
		mtRepayment.setOverduedate(DateUtils.parseDate(DateUtils.getDate()));
		mtRepayment.setDuedate(doRepayDate(DayWeek.plusMonth(periods)));
		mtRepayment.setUserid(Integer.parseInt(mtUser.getId()));
		mtRepaymentService.save(mtRepayment);
	}
	
	// 完善部分用户数据
	private void usersave(HttpServletRequest request) {
		String userid = request.getParameter("uid");
		MtUser mtUser = mtUserService.get(String.valueOf(userid));
		mtUser.setCurrentAddress(request.getParameter("currentAddress"));
		mtUser.setJobDescription(request.getParameter("jobDescription"));
		mtUser.setUserBrith(DateUtils.parseDate(request.getParameter("userBrith")));
		mtUser.setMaritalStatus(Integer.parseInt(request.getParameter("maritalStatus")));
		mtUser.setCensusRegisterAddr(request.getParameter("censusRegisterAddr"));
		mtUser.setUserSex(Integer.parseInt(request.getParameter("userSex")));
		mtUser.setEduBackground(request.getParameter("eduBackground"));
		mtUser.setUserSosName(request.getParameter("userSosName"));
		mtUser.setUserSosTel(request.getParameter("userSosTel"));
		this.mtUserService.save(mtUser);
	}
	
	/**
	 * 标的注册响应报文处理
	 * @param result
	 */
	public void regProjectCallback(String result, MtProduct mtProduct) {
		JSONObject head = this.getHead(result);
		JSONObject body = this.getBody(result);
		// 商户请求的唯一标识(项目ID号)
    	String projectNo = head.getString("merOrderNo");
    	if(projectNo.equals(mtProduct.getPid())) {
    		// 业务流水号-陕坝
    		mtProduct.setBizFlow(head.getString("bizFlow"));
    		// 项目注册日期-陕坝
        	mtProduct.setTradeDate(head.getString("tradeDate"));
        	// 项目注册时间-陕坝
        	mtProduct.setTradeTime(head.getString("tradeTime"));
        	// 项目标号-陕坝
        	mtProduct.setSubjectNo(body.getString("subjectNo"));
        	mtProductService.save(mtProduct);
    	}
		logger.info("=====================标的注册响应报文处理完成=====================");
	}
    
    @RequestMapping(value = "makeLoanCallback")
    public void makeLoanCallback(HttpServletRequest request) {
    	String desIv = this.desIv;
    	String desKey = this.desKey;
    	String response = request.getParameter("response");
    	response = DES.decrypt3DES(response, desKey, desIv);
    	JSONObject jsonobject = new JSONObject(response);
    	String prono =jsonobject.getString("projectNo");
    	MtLoanrd loanrd = new MtLoanrd();
    	loanrd.setProjectNo(prono);
    	System.out.println(jsonobject);
    	List<MtLoanrd> list = mtLoanrdService.findList(loanrd);
    	if(list.size()==0) {
    		this.loanRecord(jsonobject);
    	}
    	logger.info("======================放款回调完成======================");
    }
    
    @RequestMapping(value = "makeLiubiaoCallback")
    public void makeLiubiaoCallback(HttpServletRequest request) {
    	String desIv = this.desIv;
    	String desKey = this.desKey;
    	String response = request.getParameter("response");
    	response = DES.decrypt3DES(response, desKey, desIv);
    	JSONObject jsonobject = new JSONObject(response);
    	String ipsbillon = jsonobject.getString("ipsBillNo");
    	String freezeId = jsonobject.getString("freezeId");
    	MtInvest mtInvest = mtInvestService.findInvest(freezeId);
    	Integer countNum = mtLoanrdService.findCount(ipsbillon);
    	MtLoanrd mtLoanrd = new MtLoanrd();
    	if(countNum == 0) {
    		mtLoanrd.setIpsbillno(ipsbillon);
	    	String acctNo = jsonobject.getString("ipsAcctNo");
	    	String userid = mtUserService.findUser(acctNo).getId();
	    	mtLoanrd.setMerBillNo(jsonobject.getString("merBillNo"));
	    	mtLoanrd.setProjectNo(jsonobject.getString("projectNo"));
	    	mtLoanrd.setIpsdotime(DateUtils.parseDate(jsonobject.get("ipsDoTime")));
	    	mtLoanrd.setTrdstatus(Integer.valueOf(jsonobject.getString("trdStatus")));
	    	mtLoanrd.setOutipsacctno(jsonobject.getString("ipsAcctNo"));
	    	mtLoanrd.setInipsacctno(jsonobject.getString("ipsAcctNo"));
	    	mtLoanrd.setTransferType(String.valueOf(9));
	    	mtLoanrd.setUserid(Integer.valueOf(userid));
	    	mtLoanrd.setIpstrdamt(mtInvest.getMount());
	    	mtLoanrd.setRemarks("流标:投资资金解冻操作");
	    	mtLoanrdService.save(mtLoanrd);
    	}
    	logger.info("======================流标回调完成======================");
    }
    
    private void loanRecord(JSONObject jsonobject) {
    	String projectNo = jsonobject.getString("projectNo"); // 项目 ID号
    	String batchNo = jsonobject.getString("batchNo"); // 商户转账批次号
    	//String transferType = jsonobject.getString("transferType"); // 转账类型
    	JSONArray jsonarray = jsonobject.getJSONArray("transferAccDetail"); // 转账明细集合
    	for(int i=0; i<jsonarray.length(); i++) {
    		MtLoanrd mtLoanrd = new MtLoanrd();
    		JSONObject json = jsonarray.getJSONObject(i);
    		String ipsTrdAmt = json.getString("ipsTrdAmt");
    		String outAcctNo = json.getString("outIpsAcctNo");
    		MtUser mtUser = mtUserService.findUser(outAcctNo);
    		mtLoanrd.setIpstrdamt(new BigDecimal(ipsTrdAmt));
    		mtLoanrd.setUserid(Integer.parseInt(mtUser.getId()));
    		mtLoanrd.setInipsacctno(json.getString("inIpsAcctNo"));
    		mtLoanrd.setRemarks("放款:"+new BigDecimal(ipsTrdAmt).toString());
    		mtLoanrd.setIpsdotime(DateUtils.parseDate(json.getString("ipsDoTime")));
    		mtLoanrd.setTrdstatus(Integer.parseInt(json.getString("trdStatus")));
    		mtLoanrd.setIpsbillno(json.getString("ipsBillNo"));
    		mtLoanrd.setMerBillNo(json.getString("merBillNo"));
    		mtLoanrd.setTransferType(String.valueOf(8));
    		mtLoanrd.setAmoutable(new BigDecimal(0));
    		mtLoanrd.setOutipsacctno(outAcctNo);
    		mtLoanrd.setProjectNo(projectNo);
    		mtLoanrd.setBatchNo(batchNo);
    		mtLoanrdService.save(mtLoanrd);
    	}
    }
	
	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(MtProduct mtProduct, RedirectAttributes redirectAttributes) {
		mtProductService.delete(mtProduct);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/product/mtProduct/?type="+menuType;
	}
	
	/**
	 * 自动修改产品状态
	 */
	private void autoChangeStatus() {
		Date nowaTime = DateUtils.parseDate(DateUtils.getDateTime());
		List<MtProduct> prolist = mtProductService.findStatusList(ProStatus.ONE, ProStatus.THREE);
		for (MtProduct mtProduct : prolist) {
			if(mtProduct.getStatus() == ProStatus.ONE) {
				// 定时发布产品
				Date startTime = mtProduct.getStarttime();
				if(nowaTime.getTime() >= startTime.getTime()) {
					logger.info("********************************定时发标************************************");
					System.out.println("------当前标：" + mtProduct.getTitle()+ ",发布时间为:"+ mtProduct.getStarttime() +",状态为:"+mtProduct.getStatus()+"------");
					String userid = String.valueOf(mtProduct.getUserid());
					MtUser mtUser = mtUserService.get(userid);
					String result = this.regProject(mtProduct, mtUser);
					JSONObject head = this.getHead(result);
					if(isLoanSuccess(head)) {
						mtProduct.setStatus(ProStatus.THREE);
						mtProduct.setIvable(mtProduct.getPlanmoney());
						this.regProjectCallback(result, mtProduct);
					}
				}
			} else {
				// 未满标，资金募集结束
				Date endTime = mtProduct.getEndtime();
				if(nowaTime.getTime() >= endTime.getTime()) {
					logger.info("********************************资金募集结束************************************");
					System.out.println("------当前标：" + mtProduct.getTitle()+ ",结束时间为:"+ mtProduct.getStarttime() +",状态为:"+mtProduct.getStatus()+"------");
					mtProduct.setStatus(ProStatus.TEN);
					mtProductService.save(mtProduct);
				}
			}
		}
		System.gc();
	}
	
	/**
	 * 定时执行器
	 */
	@Scheduled(cron="0/30 * * * * ?")
	public void cronTimingExecutor() {
		this.autoChangeStatus();
	}
	
}