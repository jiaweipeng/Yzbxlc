/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auth.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 认证管理Entity
 * @author shouming
 * @version 2017-06-14
 */
public class MtAuth extends DataEntity<MtAuth> {
	
	private static final long serialVersionUID = 1L;
	private Integer userid;		// 用户ID
	private String name;		// 真实姓名
	private String idcard;		// 身份证号
	private String picture;		// 身份证正反面图片
	private String phone;		// 手机号
	private String email;		// 邮箱号
	private String videoFile;		// 视频文件
	private String depositBank;		// 开户银行
	private String branchBankName;		// 开户行支行名称
	private String bankCard;		// 银行卡号
	private Integer authState;		// 认证状态
	private Integer authType;		// 认证类型
	
	//用户名
	private String userNickName;
	
	private Date starttime; // 开始时间
	private Date updatetime; // 更新时间
	
	


	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public MtAuth() {
		super();
	}

	public MtAuth(String id){
		super(id);
	}
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=50, message="真实姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="身份证号长度必须介于 0 和 50 之间")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Length(min=0, max=200, message="身份证正反面图片长度必须介于 0 和 200 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Length(min=0, max=50, message="手机号长度必须介于 0 和 50 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="邮箱号长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="视频文件长度必须介于 0 和 200 之间")
	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	
	@Length(min=0, max=50, message="开户银行长度必须介于 0 和 50 之间")
	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	
	@Length(min=0, max=50, message="开户行支行名称长度必须介于 0 和 50 之间")
	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}
	
	@Length(min=0, max=50, message="银行卡号长度必须介于 0 和 50 之间")
	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	public Integer getAuthState() {
		return authState;
	}

	public void setAuthState(Integer authState) {
		this.authState = authState;
	}
	
	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	
}