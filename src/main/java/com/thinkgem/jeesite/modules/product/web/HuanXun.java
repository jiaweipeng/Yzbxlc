package com.thinkgem.jeesite.modules.product.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.xstream.DES;
import com.thinkgem.jeesite.common.xstream.HTTPPost;
import com.thinkgem.jeesite.common.xstream.MD5;
import com.thinkgem.jeesite.common.xstream.SMS;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.pnuelist.entity.MtPnuelist;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

public class HuanXun extends ProductController {
	
	protected String yzbxlc = null;
	protected String merchantID = null;
	protected String md5Key = null;
	protected String desKey = null;
	protected String desIv = null;
	
	public HuanXun() {
		// 域名
		this.yzbxlc = getProper("yzbxlc.com");
		// 请求报文信息
		this.merchantID = getProper("hx.merchantID");
		// 密钥信息
		this.md5Key = getProper("hx.md5Key");
		this.desKey = getProper("hx.desKey");
		this.desIv = getProper("hx.desIv");
	}
	
	public String getDesKey() {
		return desKey;
	}

	public String getDesIv() {
		return desIv;
	}

	/**
	 * 环讯项目登记接口
	 * @param product
	 */
	protected String regProject(MtProduct product, MtUser user) {
		
		product.setPid(doOrderNum());
		product.setTitle("测试项目开发");
		product.setPlanmoney(new BigDecimal(1000.00));
		product.setHopepercent(12f);
		product.setUserfor("测试开发");
		product.setManagecost(0.1f);
		product.setStarttime(new Date());
		product.setEndtime(DateUtils.parseDate("2018-03-20"));
		
		user.setIpsAcctNo("1050000920701");
		user.setUserIdNumber("420101198101015161");
		
        List<NameValuePair> listParam = new ArrayList<NameValuePair>();
        // 商户标的编号
        listParam.add(new BasicNameValuePair("merSubjectNo", product.getPid())); 
        // 商户新建的标的名称
        listParam.add(new BasicNameValuePair("subjectName", product.getTitle()));
        // 满标金额
        String subjectAmt = String.valueOf(product.getPlanmoney());
        listParam.add(new BasicNameValuePair("subjectAmt", subjectAmt));
        // 标的利率最多四位小数
        String subjectRate = product.getHopepercent().toString();
        listParam.add(new BasicNameValuePair("subjectRate", subjectRate));
        // 标的用途
        listParam.add(new BasicNameValuePair("subjectPurpose", product.getUserfor()));
        // 融资人账户号码
        listParam.add(new BasicNameValuePair("payeeAcctNo", user.getIpsAcctNo()));
        // 00个人标的 01企业标的
        listParam.add(new BasicNameValuePair("subjectType", "00"));
        // 身份证或三码合一
        listParam.add(new BasicNameValuePair("identificationNo", user.getUserIdNumber()));
        // 标的服务费最多四位小数
        String serviceRate = product.getManagecost().toString();
        listParam.add(new BasicNameValuePair("serviceRate", serviceRate));
        // 担保人的账户号码
        //listParam.add(new BasicNameValuePair("guarantorAcctNo", ""));
        // 担保方名称
        //listParam.add(new BasicNameValuePair("guarantor", ""));
        // 标的开始日期格式yyyyMMdd
        String startDate = DateUtils.formatDate(product.getStarttime(), "yyyyMMdd");
        listParam.add(new BasicNameValuePair("subjectStartDate", startDate));
        // 标的结束日期格式yyyyMMdd
        String endDate = DateUtils.formatDate(product.getEndtime(), "yyyyMMdd");
        listParam.add(new BasicNameValuePair("SubjectEndDate", endDate)); 
        String result = HTTPPost.request(listParam, "productreg.do");
        logger.info("============================项目登记接口==============================");
        System.out.println(result);
        logger.info("============================项目登记接口==============================");
        return result;
	}
	
	/**
	 * 放款操作
	 * @param product
	 * @return
	 */
	protected String makeLoan(MtProduct product) {
        //密钥信息
	    String md5Key = this.md5Key;
	    String desKey = this.desKey;
	    String desIv = this.desIv;
        //请求报文信息
	    String operationType="trade.transfer";
        String s2SUrl = this.yzbxlc+"jeesite/a/product/mtProduct/makeLoanCallback";
        MtUser mtUser = mtUserService.get(String.valueOf(product.getUserid()));
        BigDecimal loanCost = new BigDecimal(0);
        // 商户号
        String merchantID = this.merchantID;
        
        JSONObject sonParam = null;
        JSONArray detail = new JSONArray();
        MtInvest mtInvest = new MtInvest();
        mtInvest.setProductid(product.getId());
        List<MtInvest> invest = mtInvestService.findList(mtInvest);
        for(int index=0; index<invest.size(); index++) {
        	sonParam = new JSONObject();
            sonParam.put("merBillNo", merchantID+doOrderNum()+index);
            sonParam.put("freezeId", invest.get(index).getIpsBillNo());
            sonParam.put("outIpsAcctNo", invest.get(index).getIpsAcctNo());
            sonParam.put("outMerFee", "0.00");
            sonParam.put("inIpsAcctNo", mtUser.getIpsAcctNo());
            BigDecimal inMerFee = loanCost(invest.get(index),product.getManagecost());
            sonParam.put("inMerFee", inMerFee);
            sonParam.put("trdAmt", invest.get(index).getMount());
            loanCost = loanCost.add(inMerFee);
            detail.put(sonParam);
        }
        JSONObject requestParam = new JSONObject();
        requestParam.put("batchNo", doOrderNum());
        requestParam.put("merDate", DateUtils.getDate());
        requestParam.put("projectNo", product.getPid());
        requestParam.put("transferType", "1");
        requestParam.put("isAutoRepayment", "2");
        requestParam.put("transferMode", "2");
        requestParam.put("s2SUrl", s2SUrl);
        requestParam.put("transferAccDetail", detail);
        System.out.println(requestParam.toString(1));
        
        String request=DES.encrypt3DES(requestParam.toString(),desKey,desIv);
        String sign=MD5.sign(operationType+merchantID+request+md5Key,"UTF-8");
        
        List<NameValuePair> listParam = new ArrayList<NameValuePair>();
        
        listParam.add(new BasicNameValuePair("subjectNo", product.getPid())); // 标的编号
        listParam.add(new BasicNameValuePair("payeeAcctNo", "")); // 融资人账户号码
        listParam.add(new BasicNameValuePair("subjectFlag", "")); // 标的状态
        listParam.add(new BasicNameValuePair("subjectTerm", "")); // 最后还款日
        listParam.add(new BasicNameValuePair("incomeAmt", "")); // 商户服务费
        listParam.add(new BasicNameValuePair("list", "")); // 投资人信息列表
        
        
        String result = HTTPPost.request(listParam, "");
        JSONObject head = this.getHead(result);
		if(isLoanSuccess(head)) {
			// 平台收益记录
			MtPnuelist mtPnuelist = new MtPnuelist();
			mtPnuelist.setInmount(loanCost);
			String nowtime = DateUtils.getDateTime();
			mtPnuelist.setCreationtime(DateUtils.parseDate(nowtime));
			mtPnuelist.setMtUserId(product.getUserid());
			mtPnuelist.setPid(product.getPid());
			mtPnuelist.setType(0);
			mtPnuelistService.save(mtPnuelist);
		} 
        return result;
	}
	
	/**
	 * 环讯查询接口
	 * @param ipsAcctNo
	 * @param queryType
	 * @param merBillNo
	 * @return
	 */
	public String findInterface(String ipsAcctNo, String queryType, String merBillNo) {
		//密钥信息
	    String md5Key = this.md5Key;
	    String desKey = this.desKey;
	    String desIv = this.desIv;
        //请求报文信息
	    String operationType="query.commonQuery";        
        // 商户号
        String merchantID = this.merchantID;
        // 请求参数
        JSONObject requestParam = new JSONObject();
        requestParam.put("ipsAcctNo", ipsAcctNo);
        requestParam.put("queryType", queryType);
        requestParam.put("merBillNo", merBillNo);
        // 请求参数加密
        String request=DES.encrypt3DES(requestParam.toString(),desKey,desIv);
        String sign=MD5.sign(operationType+merchantID+request+md5Key,"UTF-8");
        // 请求参数拼接
        List<NameValuePair> listParam = new ArrayList<NameValuePair>();
        listParam.add(new BasicNameValuePair("operationType",operationType));
        listParam.add(new BasicNameValuePair("merchantID", merchantID));
        listParam.add(new BasicNameValuePair("sign", sign));
        listParam.add(new BasicNameValuePair("request", request));
		return HTTPPost.request(listParam, "");
	}
	
	/**
	 * 流标操作
	 * @param product
	 * @return
	 */
	protected Integer makeLiubiao(MtProduct product) {
        //密钥信息
	    String md5Key = this.md5Key;
	    String desKey = this.desKey;
	    String desIv = this.desIv;
        //请求报文信息
	    String operationType="trade.unFreeze";
        String s2SUrl = this.yzbxlc+"jeesite/a/product/mtProduct/makeLiubiaoCallback";
        // 商户号
        String merchantID = this.merchantID;
        
        Integer failNum = 0;
        String id = product.getId();
        List<MtInvest> mtInvest = mtInvestService.findIdList(id);
        for(int index=0; index<mtInvest.size(); index++) {
        	JSONObject requestParam = new JSONObject();
        	requestParam.put("merBillNo", merchantID+doOrderNum()+index);
        	requestParam.put("merDate", DateUtils.getDate());
        	requestParam.put("projectNo", mtInvest.get(index).getProjectNo());
        	requestParam.put("freezeId", mtInvest.get(index).getIpsBillNo());
        	requestParam.put("bizType", String.valueOf(1));
        	requestParam.put("merFee", "0.00");
        	requestParam.put("ipsAcctNo", mtInvest.get(index).getIpsAcctNo());
        	requestParam.put("trdAmt", mtInvest.get(index).getMount());
        	requestParam.put("s2SUrl", s2SUrl);
        	// 3DES加密
        	String request=DES.encrypt3DES(requestParam.toString(),desKey,desIv);
            String sign=MD5.sign(operationType+merchantID+request+md5Key,"UTF-8");
            // 添加访问参数
            List<NameValuePair> listParam = new ArrayList<NameValuePair>();
            listParam.add(new BasicNameValuePair("operationType",operationType));
            listParam.add(new BasicNameValuePair("merchantID", merchantID));
            listParam.add(new BasicNameValuePair("sign", sign));
            listParam.add(new BasicNameValuePair("request", request));
            String result = HTTPPost.request(listParam, "");
            JSONObject head = this.getHead(result);
            logger.info(result);
            // 解冻失败统计
            if(isLoanSuccess(head)==false) {
            	failNum = failNum + 1;
            } else {
            	String title = "流标通知";
        		String content = "您在燕赵百姓理财网投资的理财产品流标，投资金额已返还你的账户";
        		
        		Integer userId = mtInvest.get(index).getMtUserId();
        		MtUser mtUser = mtUserService.get(userId.toString());
        		mailSend(mtUser.getUserMail(), title, content);
        		SMS.sendout(mtUser.getUserTel(), content);
        		siteNotice(userId, title, content);
            }
        }
		return failNum;
	}
	
	protected BigDecimal loanCost(MtInvest invest, Float loanrate) {
		Float managecost = loanrate / 100;
		Float income = invest.getMount().floatValue() * managecost;
		return toDecimal(income.doubleValue());
	}
	
	/**
	 * 是否放款成功
	 * @param result
	 * @return
	 */
	protected boolean isLoanSuccess(JSONObject object) {
		String respCode = object.getString("respCode");
		String respDesc = object.getString("respDesc");
		if(respCode.equals("000000") && respDesc.equals("成功")) {
			return true;
		}
		return false;
	}
	
}
