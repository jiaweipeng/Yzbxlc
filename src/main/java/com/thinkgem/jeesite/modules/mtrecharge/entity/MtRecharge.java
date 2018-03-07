/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtrecharge.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 充值记录Entity
 * @author wuhao
 * @version 2017-06-21
 */
public class MtRecharge extends DataEntity<MtRecharge> {
	
	private static final long serialVersionUID = 1L;
	private String merbillno;		// 商户订单号
	private Integer deposittype;		// 充值类型:1、普通充值 2、还款充值
	private Integer channeltype;		// 充值渠道 1、个人网银 2、企业网银
	private String bankcode;		// 充值银行
	private String ipsbillno;		// IPS 订单号
	private Date ipsdotime;		// IPS 处理时间
	private BigDecimal ipstrdamt;		// IPS 充值金额
	private BigDecimal ipsfee;		// IPS 手续费金额
	private BigDecimal merfee;		// 平台手续费
	private Integer trdstatus;		// 充值状态0 失败1 成功2 处理中
	private Integer mtUserId;		// 用户id
	private Date  beginTime;//开始时间
	private Date  endTime;//结束时间
	
	
	
	public BigDecimal getIpstrdamt() {
		return ipstrdamt;
	}

	public void setIpstrdamt(BigDecimal ipstrdamt) {
		this.ipstrdamt = ipstrdamt;
	}

	public BigDecimal getIpsfee() {
		return ipsfee;
	}

	public void setIpsfee(BigDecimal ipsfee) {
		this.ipsfee = ipsfee;
	}

	public BigDecimal getMerfee() {
		return merfee;
	}

	public void setMerfee(BigDecimal merfee) {
		this.merfee = merfee;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public MtRecharge() {
		super();
	}

	public MtRecharge(String id){
		super(id);
	}

	@Length(min=0, max=40, message="商户订单号长度必须介于 0 和 40 之间")
	public String getMerbillno() {
		return merbillno;
	}

	public void setMerbillno(String merbillno) {
		this.merbillno = merbillno;
	}
	
	public Integer getDeposittype() {
		return deposittype;
	}

	public void setDeposittype(Integer deposittype) {
		this.deposittype = deposittype;
	}
	
	public Integer getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(Integer channeltype) {
		this.channeltype = channeltype;
	}
	
	@Length(min=0, max=10, message="充值银行长度必须介于 0 和 10 之间")
	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	
	@Length(min=0, max=25, message="IPS 订单号长度必须介于 0 和 25 之间")
	public String getIpsbillno() {
		return ipsbillno;
	}

	public void setIpsbillno(String ipsbillno) {
		this.ipsbillno = ipsbillno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIpsdotime() {
		return ipsdotime;
	}

	public void setIpsdotime(Date ipsdotime) {
		this.ipsdotime = ipsdotime;
	}
	
	
	
	public Integer getTrdstatus() {
		return trdstatus;
	}

	public void setTrdstatus(Integer trdstatus) {
		this.trdstatus = trdstatus;
	}
	
	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}
	
}