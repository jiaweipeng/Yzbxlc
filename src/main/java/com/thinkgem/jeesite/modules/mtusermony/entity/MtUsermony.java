/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtusermony.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户资金列表Entity
 * @author wuhao
 * @version 2017-08-19
 */
public class MtUsermony extends DataEntity<MtUsermony> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户ID
	private BigDecimal recharge;		// 充值金额
	private BigDecimal wals;		// 提现金额
	private BigDecimal dsfunds;		// 可取资金
	private BigDecimal dlfunds;		// 冻结资金
	private BigDecimal lnmony;		// 投资金额
	private BigDecimal prinmony;	// 结算本金
	private BigDecimal settmony;	// 结算收益
	private BigDecimal pumony;		// 罚息收入
	private BigDecimal remony;		// 剩余还款
	private BigDecimal alsmony;		// 已还本金
	private BigDecimal eamony;		// 已换收益
	private BigDecimal penmony;		// 罚息支出
	private BigDecimal damamony;	// 违约金
	private BigDecimal feemony;		// 账户管理费
	private BigDecimal reward;		// 奖励金额
	private String other;		// 其他
	private Integer pagesize;   //页面大小
	private Integer pageno;    // 页码
	private Date startdate;    //搜索开始日期
	private Date enddate;    //搜索结束日期
	private String date;		// 日期
	
	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public MtUsermony() {
		super();
	}

	public MtUsermony(String id){
		super(id);
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public BigDecimal getRecharge() {
		return recharge;
	}

	public void setRecharge(BigDecimal recharge) {
		this.recharge = recharge;
	}

	public BigDecimal getWals() {
		return wals;
	}

	public void setWals(BigDecimal wals) {
		this.wals = wals;
	}

	public BigDecimal getDsfunds() {
		return dsfunds;
	}

	public void setDsfunds(BigDecimal dsfunds) {
		this.dsfunds = dsfunds;
	}

	public BigDecimal getDlfunds() {
		return dlfunds;
	}

	public void setDlfunds(BigDecimal dlfunds) {
		this.dlfunds = dlfunds;
	}

	public BigDecimal getLnmony() {
		return lnmony;
	}

	public void setLnmony(BigDecimal lnmony) {
		this.lnmony = lnmony;
	}

	public BigDecimal getPrinmony() {
		return prinmony;
	}

	public void setPrinmony(BigDecimal prinmony) {
		this.prinmony = prinmony;
	}

	public BigDecimal getSettmony() {
		return settmony;
	}

	public void setSettmony(BigDecimal settmony) {
		this.settmony = settmony;
	}

	public BigDecimal getPumony() {
		return pumony;
	}

	public void setPumony(BigDecimal pumony) {
		this.pumony = pumony;
	}

	public BigDecimal getRemony() {
		return remony;
	}

	public void setRemony(BigDecimal remony) {
		this.remony = remony;
	}

	public BigDecimal getAlsmony() {
		return alsmony;
	}

	public void setAlsmony(BigDecimal alsmony) {
		this.alsmony = alsmony;
	}

	public BigDecimal getEamony() {
		return eamony;
	}

	public void setEamony(BigDecimal eamony) {
		this.eamony = eamony;
	}

	public BigDecimal getPenmony() {
		return penmony;
	}

	public void setPenmony(BigDecimal penmony) {
		this.penmony = penmony;
	}

	public BigDecimal getDamamony() {
		return damamony;
	}

	public void setDamamony(BigDecimal damamony) {
		this.damamony = damamony;
	}

	public BigDecimal getFeemony() {
		return feemony;
	}

	public void setFeemony(BigDecimal feemony) {
		this.feemony = feemony;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}