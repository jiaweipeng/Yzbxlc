/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.repayment.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 还款列表Entity
 * @author jiaweipeng
 * @version 2017-06-16
 */
public class MtRepayment extends DataEntity<MtRepayment> {
	
	private static final long serialVersionUID = 1L;
	private String grade;		// 标号
	private Integer numperiods;		// 还款期数
	private Date duedate;		// 应还日期
	private BigDecimal yetcapital;		// 应还本金
	private BigDecimal yetinterest;		// 应还利息
	private BigDecimal withanakin;		// 违约金
	private BigDecimal penalty;		// 罚息
	private BigDecimal latefee;		// 滞纳金
	private String overduedays;		// 逾期天数
	private Integer state;		// 还款状态
	private Integer userid;		// 用户id
	private Integer overdue;		// 是否逾期，1是0否
	private Date actualDuedate;		// 实际还款日期
	private Integer productId;		// 产品Id
	private Date overduedate;  // 逾期操作时间
	private Integer iswarn; // 是否还款通知
	private BigDecimal actualAnakin; // 实际交纳违约金金额
	private BigDecimal actualLatefee; // 实际交纳滞纳金金额
	private Date actualPayTime; // 实际交纳违约金和滞纳金时间
	// 用户表里的字段
	private String userNickName;		// 用户名
	private String userRealName;		// 真实姓名
	// 产品表里的字段
	private String title;		// 产品标题
	
	public BigDecimal getActualAnakin() {
		return actualAnakin;
	}

	public void setActualAnakin(BigDecimal actualAnakin) {
		this.actualAnakin = actualAnakin;
	}

	public BigDecimal getActualLatefee() {
		return actualLatefee;
	}

	public void setActualLatefee(BigDecimal actualLatefee) {
		this.actualLatefee = actualLatefee;
	}

	public Date getActualPayTime() {
		return actualPayTime;
	}

	public void setActualPayTime(Date actualPayTime) {
		this.actualPayTime = actualPayTime;
	}

	public Integer getIswarn() {
		return iswarn;
	}

	public void setIswarn(Integer iswarn) {
		this.iswarn = iswarn;
	}

	public Date getOverduedate() {
		return overduedate;
	}

	public void setOverduedate(Date overduedate) {
		this.overduedate = overduedate;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getLatefee() {
		return latefee;
	}

	public void setLatefee(BigDecimal latefee) {
		this.latefee = latefee;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public MtRepayment() {
		super();
	}

	public MtRepayment(String id){
		super(id);
	}

	@Length(min=0, max=20, message="标号长度必须介于 0 和 20 之间")
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Integer getNumperiods() {
		return numperiods;
	}

	public void setNumperiods(Integer numperiods) {
		this.numperiods = numperiods;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	
	public BigDecimal getYetcapital() {
		return yetcapital;
	}

	public void setYetcapital(BigDecimal yetcapital) {
		this.yetcapital = yetcapital;
	}
	
	public BigDecimal getYetinterest() {
		return yetinterest;
	}

	public void setYetinterest(BigDecimal yetinterest) {
		this.yetinterest = yetinterest;
	}
	
	public BigDecimal getWithanakin() {
		return withanakin;
	}

	public void setWithanakin(BigDecimal withanakin) {
		this.withanakin = withanakin;
	}
	
	@Length(min=0, max=50, message="逾期天数长度必须介于 0 和 50 之间")
	public String getOverduedays() {
		return overduedays;
	}

	public void setOverduedays(String overduedays) {
		this.overduedays = overduedays;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getOverdue() {
		return overdue;
	}

	public void setOverdue(Integer overdue) {
		this.overdue = overdue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualDuedate() {
		return actualDuedate;
	}

	public void setActualDuedate(Date actualDuedate) {
		this.actualDuedate = actualDuedate;
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}