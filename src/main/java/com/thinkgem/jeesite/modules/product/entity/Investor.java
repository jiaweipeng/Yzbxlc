package com.thinkgem.jeesite.modules.product.entity;

public class Investor {

	private String realName; // 真实姓名
	private String nickName; // 用户名
	private String idNumber; // 身份证号
	private String invest; // 借款金额

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getInvest() {
		return invest;
	}

	public void setInvest(String invest) {
		this.invest = invest;
	}

}
