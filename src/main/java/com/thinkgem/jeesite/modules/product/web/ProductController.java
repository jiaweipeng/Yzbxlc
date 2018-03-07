package com.thinkgem.jeesite.modules.product.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.xstream.DayWeek;
import com.thinkgem.jeesite.modules.mtegsrord.service.MtEgsrordService;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtinvest.service.MtInvestService;
import com.thinkgem.jeesite.modules.mtloanrd.service.MtLoanrdService;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.notice.entity.MtNotice;
import com.thinkgem.jeesite.modules.notice.service.MtNoticeService;
import com.thinkgem.jeesite.modules.notice.web.MtNoticeController.NoticeType;
import com.thinkgem.jeesite.modules.pact.entity.MtPact;
import com.thinkgem.jeesite.modules.pact.service.MtPactService;
import com.thinkgem.jeesite.modules.pnuelist.service.MtPnuelistService;
import com.thinkgem.jeesite.modules.product.entity.Investor;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.entity.Repayment;
import com.thinkgem.jeesite.modules.product.service.AgreementService;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;
import com.thinkgem.jeesite.modules.repayment.service.MtRepaymentService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

public class ProductController extends BaseController {

	@Autowired
	private MtPactService mtPactService;
	@Autowired
	protected MtInvestService mtInvestService;	
	@Autowired
	protected MtRepaymentService mtRepaymentService;
	@Autowired
	protected MtPnuelistService mtPnuelistService;
	@Autowired
	protected MtEgsrordService mtEgsrordService;
	@Autowired
	protected MtLoanrdService mtLoanrdService;
	@Autowired
	protected MtNoticeService mtNoticeService;
	@Autowired
	protected MtUserService mtUserService;
	
	/**
	 * 协议投资人信息
	 * @param investList
	 * @return
	 */
	private List<Investor> doInvestorList(List<MtInvest> investList) {
		List<Investor> investorList = new ArrayList<>();
		for(int index=0; index<investList.size(); index++) {
			Investor investor = new Investor();
			MtInvest invest = investList.get(index);
			Integer mtUserId = invest.getMtUserId();
			MtUser lendUser = mtUserService.get(mtUserId.toString());
			investor.setRealName(lendUser.getUserRealName());
			investor.setNickName(lendUser.getUserNickName());
			investor.setIdNumber(lendUser.getUserIdNumber());
			investor.setInvest(invest.getMount().toString());
			investorList.add(investor);
		}
		return investorList;
	}
	
	/**
	 * 协议还款列表数据
	 * @param mtRepayment
	 * @return
	 */
	private List<Repayment> doRepaymentList(List<MtRepayment> mtRepayment) {
		List<Repayment> repaymentList = new ArrayList<>();
		for(int i=0; i<mtRepayment.size(); i++) {
			Repayment repayment = new Repayment();
			MtRepayment mtRepay = mtRepayment.get(i);
			BigDecimal yetcapital = mtRepay.getYetcapital();
			BigDecimal yetinterest = mtRepay.getYetinterest();
			repayment.setNumperiods(mtRepay.getNumperiods().toString());
			repayment.setDuedate(DateUtils.formatDate(mtRepay.getDuedate()));
			repayment.setRepayment(yetcapital.add(yetinterest).toString());
			repayment.setYetcapital(mtRepay.getYetcapital().toString());
			repayment.setYetinterest(mtRepay.getYetinterest().toString());
			repayment.setForfeit("");
			repaymentList.add(repayment);
		}
		return repaymentList;
	}
	
	/**
	 * 协议借款总利息
	 * @param mtProduct
	 * @return
	 */
	private BigDecimal doTotalInterest(MtProduct mtProduct) {
		Double totalInterest;
		BigDecimal planmoney = mtProduct.getPlanmoney();
		Float hopepercent = mtProduct.getHopepercent();
		Integer cycle = mtProduct.getCycle();
		Integer unit = mtProduct.getUnit();
		if(unit == 1) {
			totalInterest = doInterest(planmoney, hopepercent, 365);
		} else {
			totalInterest = doInterest(planmoney, hopepercent, 12);
		}
		return toDecimal(totalInterest*cycle);
	}
	
	/**
	 * 协议借款最终到期日
	 * @param mtProduct
	 * @return
	 */
	private String doEndDate(MtProduct mtProduct) {
		String endDate = null;
		Integer cycle = mtProduct.getCycle();
		Integer unit = mtProduct.getUnit();
		if(unit == 1) {
			endDate = DayWeek.plusDay2(cycle);
		} else {
			endDate = DayWeek.plusMonth(cycle);
		}
		return endDate;
	}
	
	/**
	 * 日期转XXXX年XX月XX日
	 * @param date
	 * @return
	 */
	private String toChineseDate(String date) {
		String[] strDate = date.split("-");
		return strDate[0]+"年"+strDate[1]+"月"+strDate[2]+"日";
	}
	
	private Map<String, Object> toData(MtProduct mtProduct) {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		// 产品信息
		dataModel.put("product_id", mtProduct.getPid());
		dataModel.put("product_name", mtProduct.getTitle());
		dataModel.put("contract_num", doOrderNum());
		// 投资人信息
		MtInvest mtInvest = new MtInvest();
		mtInvest.setProductid(mtProduct.getId());
		List<MtInvest> investList = mtInvestService.findList(mtInvest);
		List<Investor> investorList = doInvestorList(investList);
		dataModel.put("investors", investorList);
		// 借款人信息
		Integer userId = mtProduct.getUserid();
		MtUser loanUser = mtUserService.get(userId.toString());
		dataModel.put("loan_name", loanUser.getUserRealName());
		dataModel.put("loan_username", loanUser.getUserNickName());
		dataModel.put("loan_idnumber", loanUser.getUserIdNumber());
		dataModel.put("loan_planmoney", mtProduct.getPlanmoney());
		// 借款信息
		dataModel.put("loan_userfor", mtProduct.getUserfor());
		dataModel.put("hopepercent", mtProduct.getHopepercent());
		BigDecimal totalInterest = doTotalInterest(mtProduct);
		dataModel.put("total_interests", totalInterest.toString());
		dataModel.put("start_date", toChineseDate(DateUtils.getDate()));
		dataModel.put("end_date", toChineseDate(doEndDate(mtProduct)));
		dataModel.put("interest_accrual", toChineseDate(DateUtils.getDate()));
		String type = mtProduct.getBalancetype().toString();
		String balancetype = DictUtils.getDictLabel(type, "balancetype", "");
		dataModel.put("balancetype", balancetype);
		// 还款信息
		List<MtRepayment> mtRepayment = mtRepaymentService.findPidList(mtProduct.getPid());
		List<Repayment> repaymentList = doRepaymentList(mtRepayment);
		dataModel.put("mtRepayment", repaymentList);
		return dataModel;
	}
	
	/**
	 * 生成借款合同
	 * @throws URISyntaxException 
	 */
	protected void makeAgreement(MtProduct mtProduct) {
		try {
			// 初始化FreeMarker
			AgreementService.initConfig();
			Map<String, Object> dataModel = toData(mtProduct);
			String fileName = (String) dataModel.get("contract_num");
			String filePath = "tmp";
			// 生成的index.html文件
			Writer out = new OutputStreamWriter(new FileOutputStream(filePath+fileName+".html"), "UTF-8");
			AgreementService.processTemplate("agreement.ftl", dataModel, out); // 导出index.html文件
			AgreementService ts = new AgreementService();			
			ts.htmlCodeComeFromFile(filePath+fileName+".html", filePath+fileName+".pdf");
			String path =  filePath+fileName+".pdf";
			String returnMsg = ts.uploadAgreeMent(path);
			if(returnMsg!="") {
				MtPact mtPact = new MtPact();
				mtPact.setPactId(fileName);
				mtPact.setPid(mtProduct.getPid());
				mtPact.setPactSite(returnMsg);
				mtPactService.save(mtPact);
				mtInvestService.updateInvment(returnMsg, mtProduct.getPid());
				File file1 = new File(filePath+fileName+".html");
				File file2 = new File(filePath+fileName+".pdf");
			    if (file1.isFile() && file1.exists()) {
			        file1.delete();
			    }  
			    if (file2.isFile() && file2.exists()) {
			        file2.delete();
			    }  
			    mtPact = null;
			    file1 = null;
			    file2 = null;
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成还款日期
	 * @param strDate
	 * @return
	 */
	protected Date doRepayDate(String strDate) {
		String datetime = strDate+" 23:59:59";
		return DateUtils.parseDate(datetime);
	}

	/**
	 * 计算利息
	 * @param corpus
	 * @param periods
	 * @param num
	 * @return
	 */
	protected Double doInterest(BigDecimal corpus, Float periods, Integer num) {
		Double twelve = num.doubleValue();
		Double hopeper = periods.doubleValue() / 100;
		Double interest = corpus.doubleValue() * hopeper / twelve;
		return interest;
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
		if(!(email==null) && !(email=="")) {
			SendMailUtil.sendCommonMail(email, title, text);
		}
	}
	
	/**
	 * 站内信发送
	 * @param userid
	 * @param title
	 * @param content
	 */
	protected void siteNotice(Integer userid, String title, String content) {
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
	 * 生成订单号
	 * 
	 * @return
	 */
	protected String doOrderNum() {
		SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSS");
		return allTime.format(new Date()) + new Random().nextInt(9);
	}
	
	/**
	 * 读取 config.properties 配置文件
	 * @param key 键值
	 * @return
	 */
	protected String getProper(String key) {
		Properties properties = new Properties();
		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		String path = cLoader.getResource("common.properties").getPath();
		FileInputStream fileInputStream = null;
		String defaultValue = null;
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
			defaultValue = properties.getProperty(key);
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultValue;
	}
	
	/**
	 * 响应报文head数据读取
	 * @param result
	 * @return
	 */
	protected JSONObject getHead(String result) {
		JSONObject object = new JSONObject(result);
		return object.getJSONObject("head");
	}
	
	/**
	 * 响应报文body数据读取
	 * @param result
	 * @return
	 */
	protected JSONObject getBody(String result) {
		JSONObject object = new JSONObject(result);
		return object.getJSONObject("body");
	}
	
	public static String getOrderIdByUUId() {
        int machineId = 2;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }
    public static void main(String[] args) {
    	System.out.println(getOrderIdByUUId());
    }

	protected static class ProStatus {
		public static final int ZERO = 0; // 待初审
		public static final int ONE = 1; // 初审通过，等待发标
		public static final int TWO = 2; // 初审不通过
		public static final int THREE = 3; // 产品已发布，资金募集中
		public static final int FOUR = 4; // 满标待复审
		public static final int FIVE = 5; // 复审不通过流标
		public static final int SIX = 6; // 正常还款
		public static final int SEVEN = 7; // 逾期还款
		public static final int EIGHT = 8; // 失联
		public static final int NINE = 9; // 借款周期结束
		public static final int TEN = 10; // 未满标，资金募集结束
		public static final int ELEVEN = 11; // 未满标，流标
	}

}
