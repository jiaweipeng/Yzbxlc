/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pact.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同管理Entity
 * @author shouming
 * @version 2017-06-20
 */
public class MtPact extends DataEntity<MtPact> {
	
	private static final long serialVersionUID = 1L;
	private String pactId;		// 协议ID
	private String pactType;		// 协议类型
	private String pactState;		// 协议状态
	private Date pactSignTime;		// 协议签订时间
	private String pid;		// 产品ID
	private Integer investId;		// 投资人ID
	private Integer borrowId;		// 借款人ID
	
	private String pactSite; //协议连接地址


	public String getPactSite() {
		return pactSite;
	}

	public void setPactSite(String pactSite) {
		this.pactSite = pactSite;
	}

	//真实姓名
	private String userRealName;
	
	
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public MtPact() {
		super();
	}

	public MtPact(String id){
		super(id);
	}

	@Length(min=0, max=50, message="协议ID长度必须介于 0 和 50 之间")
	public String getPactId() {
		return pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	
	@Length(min=0, max=50, message="协议类型长度必须介于 0 和 50 之间")
	public String getPactType() {
		return pactType;
	}

	public void setPactType(String pactType) {
		this.pactType = pactType;
	}
	
	@Length(min=0, max=50, message="协议状态长度必须介于 0 和 50 之间")
	public String getPactState() {
		return pactState;
	}

	public void setPactState(String pactState) {
		this.pactState = pactState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPactSignTime() {
		return pactSignTime;
	}

	public void setPactSignTime(Date pactSignTime) {
		this.pactSignTime = pactSignTime;
	}
	
	@Length(min=0, max=50, message="产品ID长度必须介于 0 和 50 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public Integer getInvestId() {
		return investId;
	}

	public void setInvestId(Integer investId) {
		this.investId = investId;
	}
	
	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	
}