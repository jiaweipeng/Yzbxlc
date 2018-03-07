/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reflect.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 体现成功Entity
 * @author wuhao
 * @version 2017-06-14
 */
public class MtReflect extends DataEntity<MtReflect> {
	
	private static final long serialVersionUID = 1L;
	private String cashinbank;		// 提现账号
	private Integer cashinaccount;		// 提现银行
	private Double cashwithdrawal;		// 体现总额
	private Double arrivalamount;		// 到帐金额
	private Double counterfee;		// 手续费
	private Date cashtime;		// 提现时间
	private Integer state;		// 状态
	private Integer mtUserId;		// 用户id
	private Date paymentdate;		// 到账时间
	
	public MtReflect() {
		super();
	}

	public MtReflect(String id){
		super(id);
	}

	@Length(min=0, max=20, message="提现账号长度必须介于 0 和 20 之间")
	public String getCashinbank() {
		return cashinbank;
	}

	public void setCashinbank(String cashinbank) {
		this.cashinbank = cashinbank;
	}
	
	public Integer getCashinaccount() {
		return cashinaccount;
	}

	public void setCashinaccount(Integer cashinaccount) {
		this.cashinaccount = cashinaccount;
	}
	
	public Double getCashwithdrawal() {
		return cashwithdrawal;
	}

	public void setCashwithdrawal(Double cashwithdrawal) {
		this.cashwithdrawal = cashwithdrawal;
	}
	
	public Double getArrivalamount() {
		return arrivalamount;
	}

	public void setArrivalamount(Double arrivalamount) {
		this.arrivalamount = arrivalamount;
	}
	
	public Double getCounterfee() {
		return counterfee;
	}

	public void setCounterfee(Double counterfee) {
		this.counterfee = counterfee;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCashtime() {
		return cashtime;
	}

	public void setCashtime(Date cashtime) {
		this.cashtime = cashtime;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	
}