/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.product.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品管理Entity
 * 
 * @author jiaweipeng
 * @version 2017-06-13
 */
public class MtProduct extends DataEntity<MtProduct> {

	private static final long serialVersionUID = 1L;
	private String pid; // 标号
	private String title; // 产品标题
	private Float hopepercent; // 预期收益百分比
	private Date starttime; // 募集期开始时间
	private Date endtime; // 募集期结束
	private String userfor; // 借款用途
	private Integer balancetype; // 结算方式
	private Integer cycle; // 借款周期
	private Integer unit; // 借款类型
	private Integer risk; // 风险程度
	private String riskcontrol; // 风控措施
	private Integer displaydays; // 展示天数
	private BigDecimal lowmoney; // 最低投资金额
	private BigDecimal highmoney; // 最高投资金额
	private BigDecimal planmoney; // 计划金额
	private Integer duetime; // 投资期限类型
	private String controldemo; // 合同范本
	private String planinfo; // 计划介绍
	private Integer recommend; // 是否推荐
	private Integer recommendidx; // 推荐位顺序
	private BigDecimal currentpercent; // 当前募集进度
	private Integer status; // 状态
	private Float managecost; // 借款收费费率
	private Float investcost; // 投资收费费率
	private Integer type; // 是否为质押标
	private Integer userid; // 用户id
	private Integer rewardtype; // 投标奖励类型
	private BigDecimal rewardamout; // 奖励金额
	private Integer useragent; // 经纪人ID
	private String agreementid; // 合同id
	private BigDecimal cashdeposit; // 保证金
	private Integer guarantee; // 担保机构
	private String contact; // 联系人
	private String contactTel; // 联系人
	private String pawnimg; // 抵押物图片
	private BigDecimal ivable; // 可投金额
	private BigDecimal ivalready; // 已投金额
	private String merBillNo; // 商户订单号
	private String ipsBillNo; // IPS 订单号
	private Date ipsDoTime; // IPS 登记日期
	private String carBrand; // 汽车品牌
	private String carModel; // 汽车型号
	private String carColor; // 汽车颜色
	private String carFuel; // 汽车燃料
	private String carDischarge; // 汽车排量
	private String carKilometer; // 汽车行驶公里数
	private Date carRegtime; // 汽车登记时间
	private Date carBuytime; // 汽车购买时间
	private String carBuyPrice; // 汽车购买价格
	private String carEstimate; // 汽车估计价格
	private String bizFlow; // 业务流水号-陕坝
	private String tradeDate; // 项目注册日期-陕坝
	private String tradeTime; // 项目注册时间-陕坝
	private String subjectNo; // 项目标号-陕坝
	
	public String getBizFlow() {
		return bizFlow;
	}

	public void setBizFlow(String bizFlow) {
		this.bizFlow = bizFlow;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarFuel() {
		return carFuel;
	}

	public void setCarFuel(String carFuel) {
		this.carFuel = carFuel;
	}

	public String getCarDischarge() {
		return carDischarge;
	}

	public void setCarDischarge(String carDischarge) {
		this.carDischarge = carDischarge;
	}

	public String getCarKilometer() {
		return carKilometer;
	}

	public void setCarKilometer(String carKilometer) {
		this.carKilometer = carKilometer;
	}

	public Date getCarRegtime() {
		return carRegtime;
	}

	public void setCarRegtime(Date carRegtime) {
		this.carRegtime = carRegtime;
	}

	public Date getCarBuytime() {
		return carBuytime;
	}

	public void setCarBuytime(Date carBuytime) {
		this.carBuytime = carBuytime;
	}

	public String getCarBuyPrice() {
		return carBuyPrice;
	}

	public void setCarBuyPrice(String carBuyPrice) {
		this.carBuyPrice = carBuyPrice;
	}

	public String getCarEstimate() {
		return carEstimate;
	}

	public void setCarEstimate(String carEstimate) {
		this.carEstimate = carEstimate;
	}

	public Float getInvestcost() {		
		return investcost;
	}

	public void setInvestcost(Float investcost) {
		this.investcost = investcost;
	}

	public String getMerBillNo() {
		return merBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		this.merBillNo = merBillNo;
	}

	public String getIpsBillNo() {
		return ipsBillNo;
	}

	public void setIpsBillNo(String ipsBillNo) {
		this.ipsBillNo = ipsBillNo;
	}

	public Date getIpsDoTime() {
		return ipsDoTime;
	}

	public void setIpsDoTime(Date ipsDoTime) {
		this.ipsDoTime = ipsDoTime;
	}

	public BigDecimal getIvable() {
		return ivable;
	}

	public void setIvable(BigDecimal ivable) {
		this.ivable = ivable;
	}

	public BigDecimal getIvalready() {
		return ivalready;
	}

	public void setIvalready(BigDecimal ivalready) {
		this.ivalready = ivalready;
	}

	public String getPawnimg() {
		return pawnimg;
	}

	public void setPawnimg(String pawnimg) {
		this.pawnimg = pawnimg;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public MtProduct() {
		super();
	}

	public MtProduct(String id) {
		super(id);
	}

	@Length(min = 0, max = 100, message = "标号长度必须介于 0 和 100 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Length(min = 0, max = 80, message = "产品标题长度必须介于 0 和 80 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getHopepercent() {
		return hopepercent;
	}

	public void setHopepercent(Float hopepercent) {
		this.hopepercent = hopepercent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getUserfor() {
		return userfor;
	}

	public void setUserfor(String userfor) {
		this.userfor = userfor;
	}

	public Integer getBalancetype() {
		return balancetype;
	}

	public void setBalancetype(Integer balancetype) {
		this.balancetype = balancetype;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}

	public String getRiskcontrol() {
		return riskcontrol;
	}

	public void setRiskcontrol(String riskcontrol) {
		this.riskcontrol = riskcontrol;
	}

	public Integer getDisplaydays() {
		return displaydays;
	}

	public void setDisplaydays(Integer displaydays) {
		this.displaydays = displaydays;
	}

	public BigDecimal getLowmoney() {
		return lowmoney;
	}

	public void setLowmoney(BigDecimal lowmoney) {
		this.lowmoney = lowmoney;
	}

	public BigDecimal getHighmoney() {
		return highmoney;
	}

	public void setHighmoney(BigDecimal highmoney) {
		this.highmoney = highmoney;
	}

	public BigDecimal getPlanmoney() {
		return planmoney;
	}

	public void setPlanmoney(BigDecimal planmoney) {
		this.planmoney = planmoney;
	}

	public Integer getDuetime() {
		return duetime;
	}

	public void setDuetime(Integer duetime) {
		this.duetime = duetime;
	}

	public String getControldemo() {
		return controldemo;
	}

	public void setControldemo(String controldemo) {
		this.controldemo = controldemo;
	}

	public String getPlaninfo() {
		return planinfo;
	}

	public void setPlaninfo(String planinfo) {
		this.planinfo = planinfo;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getRecommendidx() {
		return recommendidx;
	}

	public void setRecommendidx(Integer recommendidx) {
		this.recommendidx = recommendidx;
	}

	public BigDecimal getCurrentpercent() {
		return currentpercent;
	}

	public void setCurrentpercent(BigDecimal currentpercent) {
		this.currentpercent = currentpercent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getManagecost() {
		return managecost;
	}

	public void setManagecost(Float managecost) {
		this.managecost = managecost;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRewardtype() {
		return rewardtype;
	}

	public void setRewardtype(Integer rewardtype) {
		this.rewardtype = rewardtype;
	}

	public BigDecimal getRewardamout() {
		return rewardamout;
	}

	public void setRewardamout(BigDecimal rewardamout) {
		this.rewardamout = rewardamout;
	}

	public Integer getUseragent() {
		return useragent;
	}

	public void setUseragent(Integer useragent) {
		this.useragent = useragent;
	}

	@Length(min = 0, max = 11, message = "合同id长度必须介于 0 和 11 之间")
	public String getAgreementid() {
		return agreementid;
	}

	public void setAgreementid(String agreementid) {
		this.agreementid = agreementid;
	}

	public BigDecimal getCashdeposit() {
		return cashdeposit;
	}

	public void setCashdeposit(BigDecimal cashdeposit) {
		this.cashdeposit = cashdeposit;
	}

	public Integer getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}

}