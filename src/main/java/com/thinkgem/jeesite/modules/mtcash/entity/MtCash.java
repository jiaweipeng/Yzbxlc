/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtcash.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提现列表Entity
 * @author wuhao
 * @version 2017-06-22
 */
public class MtCash extends DataEntity<MtCash> {
	
	private static final long serialVersionUID = 1L;
	private String merchantid;		// 商户存管交易账 号
	private Integer mtUserId;		// 用户id
	private String merbillno;		// 商户订单号
	private String ipsbillno;		// IPS 订单号
	private Date ipsdotime;		// IPS 处理时间
	private Double merfee;		// 平台手续费
	private Double ipsfee;		// IPS 手续费
	private String ipsacctno;		// IPS 存管账户号
	private Double ipstrdamt;		// IPS 提现金额
	private Integer trdstatus;		// 提现状态0 失败1 成功2 处理中
	private Date  beginTime;//开始时间
	private Date  endTime;//结束时间
	
	public MtCash() {
		super();
	}

	public MtCash(String id){
		super(id);
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

	@Length(min=0, max=20, message="商户存管交易账 号长度必须介于 0 和 20 之间")
	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	
	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}
	
	@Length(min=0, max=50, message="商户订单号长度必须介于 0 和 50 之间")
	public String getMerbillno() {
		return merbillno;
	}

	public void setMerbillno(String merbillno) {
		this.merbillno = merbillno;
	}
	
	@Length(min=0, max=40, message="IPS 订单号长度必须介于 0 和 40 之间")
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
	
	public Double getMerfee() {
		return merfee;
	}

	public void setMerfee(Double merfee) {
		this.merfee = merfee;
	}
	
	public Double getIpsfee() {
		return ipsfee;
	}

	public void setIpsfee(Double ipsfee) {
		this.ipsfee = ipsfee;
	}
	
	@Length(min=0, max=40, message="IPS 存管账户号长度必须介于 0 和 40 之间")
	public String getIpsacctno() {
		return ipsacctno;
	}

	public void setIpsacctno(String ipsacctno) {
		this.ipsacctno = ipsacctno;
	}
	
	public Double getIpstrdamt() {
		return ipstrdamt;
	}

	public void setIpstrdamt(Double ipstrdamt) {
		this.ipstrdamt = ipstrdamt;
	}
	
	public Integer getTrdstatus() {
		return trdstatus;
	}

	public void setTrdstatus(Integer trdstatus) {
		this.trdstatus = trdstatus;
	}
	
}