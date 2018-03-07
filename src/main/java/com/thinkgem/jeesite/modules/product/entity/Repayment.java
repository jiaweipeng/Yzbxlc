package com.thinkgem.jeesite.modules.product.entity;


public class Repayment {
	
	private String duedate; // 应还日期
	private String numperiods; // 还款期数
	private String repayment; // 还款金额
	private String yetcapital; // 应还本金
	private String yetinterest; // 应还利息
	private String forfeit; // 罚息

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getNumperiods() {
		return numperiods;
	}

	public void setNumperiods(String numperiods) {
		this.numperiods = numperiods;
	}

	public String getRepayment() {
		return repayment;
	}

	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	public String getYetcapital() {
		return yetcapital;
	}

	public void setYetcapital(String yetcapital) {
		this.yetcapital = yetcapital;
	}

	public String getYetinterest() {
		return yetinterest;
	}

	public void setYetinterest(String yetinterest) {
		this.yetinterest = yetinterest;
	}

	public String getForfeit() {
		return forfeit;
	}

	public void setForfeit(String forfeit) {
		this.forfeit = forfeit;
	}
	
}
