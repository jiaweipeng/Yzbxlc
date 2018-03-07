/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtloanrd.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转账列表Entity
 * @author wuhao
 * @version 2017-06-28
 */
public class MtLoanrd extends DataEntity<MtLoanrd> {
	
	private static final long serialVersionUID = 1L;
	private String batchNo;		  // 商户转账批次号
	private String projectNo;	  // 项目 ID 号
	private String transferType;  // 转账类型
	private String merBillNo;	  // 商户订单号
	private String outipsacctno;  // 转出方IPS存管账户号
	private String inipsacctno;	  // 转入方IPS存管账户号
	private String ipsbillno;	  // IPS 订单号
	private Date ipsdotime;		  // IPS 处理时间
	private BigDecimal ipstrdamt; // IPS转账金额(IPS实际转
	private Integer trdstatus;	  // 转账状态0:失败1成功
	private Integer userid;		  // 用户id
	private Integer pid;		  // 产品标号
	private BigDecimal amoutable; // 账户余额
	// 产品名称，搜索用
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getAmoutable() {
		return amoutable;
	}

	public void setAmoutable(BigDecimal amoutable) {
		this.amoutable = amoutable;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getMerBillNo() {
		return merBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		this.merBillNo = merBillNo;
	}

	public MtLoanrd() {
		super();
	}

	public MtLoanrd(String id){
		super(id);
	}

	@Length(min=0, max=40, message="转出方IPS存管账户号长度必须介于 0 和 40 之间")
	public String getOutipsacctno() {
		return outipsacctno;
	}

	public void setOutipsacctno(String outipsacctno) {
		this.outipsacctno = outipsacctno;
	}
	
	@Length(min=0, max=40, message="转入方IPS存管账户号长度必须介于 0 和 40 之间")
	public String getInipsacctno() {
		return inipsacctno;
	}

	public void setInipsacctno(String inipsacctno) {
		this.inipsacctno = inipsacctno;
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
	
	public BigDecimal getIpstrdamt() {
		return ipstrdamt;
	}

	public void setIpstrdamt(BigDecimal ipstrdamt) {
		this.ipstrdamt = ipstrdamt;
	}
	
	public Integer getTrdstatus() {
		return trdstatus;
	}

	public void setTrdstatus(Integer trdstatus) {
		this.trdstatus = trdstatus;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}