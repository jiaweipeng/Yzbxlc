/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtinvest.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 投资记录Entity
 * @author wuhao
 * @version 2017-06-15
 */
public class MtInvest extends DataEntity<MtInvest> {
	private static final long serialVersionUID = 1L;
	private Date tendtime;// 投标时间
	private BigDecimal mount;// 投资金额
	private BigDecimal down;// 应收本息
	private String productid;	// 标号
	private Integer mtUserId;// 用户id
	private String invment;//投资协议
	private BigDecimal plfment;//平台管理费
	private String merBillNo;//商户订单号	
	private Integer trdStatus;//冻结状态
	private String projectNo;//项目ID号
	private String ipsBillNo;//IPS订单号
	private String ipsAcctNo;//冻结账号
	private Date  ipsDoTime;//IPS处理时间
	private BigDecimal ipsTrdAmt;//IPS冻结金额
	private Date rvnueTime;//收益时间
	
	private Date beginTime;//投标开始时间
	private Date endTime;//投标结束时间
	


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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRvnueTime() {
		return rvnueTime;
	}

	public void setRvnueTime(Date rvnueTime) {
		this.rvnueTime = rvnueTime;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getMerBillNo() {
		return merBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		this.merBillNo = merBillNo;
	}

	public Integer getTrdStatus() {
		return trdStatus;
	}

	public void setTrdStatus(Integer trdStatus) {
		this.trdStatus = trdStatus;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getIpsBillNo() {
		return ipsBillNo;
	}

	public void setIpsBillNo(String ipsBillNo) {
		this.ipsBillNo = ipsBillNo;
	}

	public String getIpsAcctNo() {
		return ipsAcctNo;
	}

	public void setIpsAcctNo(String ipsAcctNo) {
		this.ipsAcctNo = ipsAcctNo;
	}

	public Date getIpsDoTime() {
		return ipsDoTime;
	}

	public void setIpsDoTime(Date ipsDoTime) {
		this.ipsDoTime = ipsDoTime;
	}

	public BigDecimal getIpsTrdAmt() {
		return ipsTrdAmt;
	}

	public void setIpsTrdAmt(BigDecimal ipsTrdAmt) {
		this.ipsTrdAmt = ipsTrdAmt;
	}

	public BigDecimal getPlfment() {
		return plfment;
	}

	public void setPlfment(BigDecimal plfment) {
		this.plfment = plfment;
	}

	public String getInvment() {
		return invment;
	}

	public void setInvment(String invment) {
		this.invment = invment;
	}

	public MtInvest() {
		super();
	}

	public MtInvest(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTendtime() {
		return tendtime;
	}

	public void setTendtime(Date tendtime) {
		this.tendtime = tendtime;
	}
	
	
	
	public BigDecimal getMount() {
		return mount;
	}

	public void setMount(BigDecimal mount) {
		this.mount = mount;
	}

	public BigDecimal getDown() {
		return down;
	}

	public void setDown(BigDecimal down) {
		this.down = down;
	}


	
	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}
	
}