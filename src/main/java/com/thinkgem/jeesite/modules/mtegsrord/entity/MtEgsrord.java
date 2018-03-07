/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtegsrord.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收益列表Entity
 * @author wuhao
 * @version 2017-06-16
 */
public class MtEgsrord extends DataEntity<MtEgsrord> {
	
	private static final long serialVersionUID = 1L;
	private String borrower;		// 借款者
	private String numpds;		// 期数
	private BigDecimal total;		// 首款总额
	private BigDecimal receivable;		// 应收本金
	private BigDecimal interest;		// 应收利息
	private Integer type;		// 收益状态
	private Integer mtUserId;		// 用户id
	private Integer mtInvestId;		// 投资记录id
	private Date recenttime;//收益时间
	private Date actualtime;//实际收益时间
	private String projectNo; // 产品标号
	
	public Date getActualtime() {
		return actualtime;
	}

	public void setActualtime(Date actualtime) {
		this.actualtime = actualtime;
	}
	
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Date getRecenttime() {
		return recenttime;
	}

	public void setRecenttime(Date recenttime) {
		this.recenttime = recenttime;
	}

	public MtEgsrord() {
		super();
	}

	public MtEgsrord(String id){
		super(id);
	}

	@Length(min=0, max=50, message="借款者长度必须介于 0 和 50 之间")
	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	
	@Length(min=0, max=50, message="期数长度必须介于 0 和 50 之间")
	public String getNumpds() {
		return numpds;
	}

	public void setNumpds(String numpds) {
		this.numpds = numpds;
	}
	

	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getReceivable() {
		return receivable;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}
	
	public Integer getMtInvestId() {
		return mtInvestId;
	}

	public void setMtInvestId(Integer mtInvestId) {
		this.mtInvestId = mtInvestId;
	}
	
	
}