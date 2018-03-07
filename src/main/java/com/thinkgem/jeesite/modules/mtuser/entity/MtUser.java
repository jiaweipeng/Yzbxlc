/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtuser.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户管理Entity
 * @author fengtao
 * @version 2017-06-14
 */
public class MtUser extends DataEntity<MtUser> {
	
	private static final long serialVersionUID = 1L;
	private String userNickName;		// 用户名
	private String userMail;		// 用户邮箱
	private String userPassword;		// 用户密码
	private String userPayPassword;		// 用户交易密码
	private Integer userAgentId;		// 经济人号码
	private String userRealName;		// 真实姓名
	private String userHeadImg;		// 用户头像
	private Integer userStatus;		// 用户状态
	private Integer isAuth;		// 实名认证
	private Integer isBindBank;		// 银行卡认证
	private Integer isBindTel;		// 手机认证
	private Integer isBindMail;		// 邮箱认证
	private Date userLastLoginTime;		// 最后登录时间
	private String userIp;		// 用户Ip
	private String userBrowser;		// 用户浏览器
	private String userSystem;		// 用户系统
	private Date userBrith;		// 出生日期
	private Integer userSex;		// 用户性别
	private String userSosName;		// 紧急联系人
	private String userSosTel;		// 紧急联系人电话
	private Integer userType;		// 用户类型
	private String userIdNumber;		// 身份证号
	private String userTel;		// 用户电话
	private String userAccount;		// 账户余额
	private Integer isVideoAuth;		// 视频认证
	private Integer isSceneAuth;		// 现场认证
	private Integer isInBorrowing;		// 是否借款中
	private String eduBackground;		// 学历
	private Integer maritalStatus;		// 婚姻状况
	private String jobDescription;		// 工作情况
	private String monthIncome;		// 每月收入
	private String censusRegisterAddr;		// 户籍地址
	private String currentAddress;		// 现居地址
	private Date ipsDoTime;//IPS 注册日期
	private String ipsAcctNo;//IPS 虚拟账号
	private String ipsBillNo;//IPS 订单号
	private BigDecimal freezeamout;//用户冻结金额
	private Integer score; // 积分
	private String custNo; // 客户号码
	private String acctNo; // 账户号码
	private String regorderno; // 用户开户订单号
	private String bindorderno; // 用户绑卡订单号
	private Date  beginTime;//开始时间
	private Date  endTime;//结束时间
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getRegorderno() {
		return regorderno;
	}

	public void setRegorderno(String regorderno) {
		this.regorderno = regorderno;
	}

	public String getBindorderno() {
		return bindorderno;
	}

	public void setBindorderno(String bindorderno) {
		this.bindorderno = bindorderno;
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

	public BigDecimal getFreezeamout() {
		return freezeamout;
	}

	public void setFreezeamout(BigDecimal freezeamout) {
		this.freezeamout = freezeamout;
	}
	
	public Date getIpsDoTime() {
		return ipsDoTime;
	}

	public void setIpsDoTime(Date ipsDoTime) {
		this.ipsDoTime = ipsDoTime;
	}

	public String getIpsAcctNo() {
		return ipsAcctNo;
	}

	public void setIpsAcctNo(String ipsAcctNo) {
		this.ipsAcctNo = ipsAcctNo;
	}

	public String getIpsBillNo() {
		return ipsBillNo;
	}

	public void setIpsBillNo(String ipsBillNo) {
		this.ipsBillNo = ipsBillNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MtUser() {
		super();
	}

	public MtUser(String id){
		super(id);
	}

	@Length(min=1, max=50, message="用户名长度必须介于 1 和 50 之间")
	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	
	@Length(min=0, max=50, message="用户邮箱长度必须介于 0 和 50 之间")
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	@Length(min=1, max=50, message="用户密码长度必须介于 1 和 50 之间")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Length(min=1, max=50, message="用户交易密码长度必须介于 1 和 50 之间")
	public String getUserPayPassword() {
		return userPayPassword;
	}

	public void setUserPayPassword(String userPayPassword) {
		this.userPayPassword = userPayPassword;
	}
	
	public Integer getUserAgentId() {
		return userAgentId;
	}

	public void setUserAgentId(Integer userAgentId) {
		this.userAgentId = userAgentId;
	}
	
	@Length(min=1, max=50, message="真实姓名长度必须介于 1 和 50 之间")
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	@Length(min=0, max=200, message="用户头像长度必须介于 0 和 200 之间")
	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
	
	public Integer getIsBindBank() {
		return isBindBank;
	}

	public void setIsBindBank(Integer isBindBank) {
		this.isBindBank = isBindBank;
	}
	
	public Integer getIsBindTel() {
		return isBindTel;
	}

	public void setIsBindTel(Integer isBindTel) {
		this.isBindTel = isBindTel;
	}
	
	public Integer getIsBindMail() {
		return isBindMail;
	}

	public void setIsBindMail(Integer isBindMail) {
		this.isBindMail = isBindMail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
	
	@Length(min=0, max=50, message="用户Ip长度必须介于 0 和 50 之间")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	@Length(min=0, max=50, message="用户浏览器长度必须介于 0 和 50 之间")
	public String getUserBrowser() {
		return userBrowser;
	}

	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}
	
	@Length(min=0, max=50, message="用户系统长度必须介于 0 和 50 之间")
	public String getUserSystem() {
		return userSystem;
	}

	public void setUserSystem(String userSystem) {
		this.userSystem = userSystem;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUserBrith() {
		return userBrith;
	}

	public void setUserBrith(Date userBrith) {
		this.userBrith = userBrith;
	}
	
	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	
	@Length(min=0, max=20, message="紧急联系人长度必须介于 0 和 20 之间")
	public String getUserSosName() {
		return userSosName;
	}

	public void setUserSosName(String userSosName) {
		this.userSosName = userSosName;
	}
	
	@Length(min=0, max=50, message="紧急联系人电话长度必须介于 0 和 50 之间")
	public String getUserSosTel() {
		return userSosTel;
	}

	public void setUserSosTel(String userSosTel) {
		this.userSosTel = userSosTel;
	}
	
	@NotNull(message="用户类型不能为空")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@Length(min=1, max=200, message="身份证号长度必须介于 1 和 200 之间")
	public String getUserIdNumber() {
		return userIdNumber;
	}

	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	
	@Length(min=1, max=50, message="用户电话长度必须介于 1 和 50 之间")
	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public Integer getIsVideoAuth() {
		return isVideoAuth;
	}

	public void setIsVideoAuth(Integer isVideoAuth) {
		this.isVideoAuth = isVideoAuth;
	}
	
	public Integer getIsSceneAuth() {
		return isSceneAuth;
	}

	public void setIsSceneAuth(Integer isSceneAuth) {
		this.isSceneAuth = isSceneAuth;
	}
	
	@NotNull(message="是否借款中不能为空")
	public Integer getIsInBorrowing() {
		return isInBorrowing;
	}

	public void setIsInBorrowing(Integer isInBorrowing) {
		this.isInBorrowing = isInBorrowing;
	}
	
	@Length(min=0, max=50, message="学历长度必须介于 0 和 50 之间")
	public String getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}
	
	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@Length(min=0, max=255, message="工作情况长度必须介于 0 和 255 之间")
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	@Length(min=0, max=50, message="每月收入长度必须介于 0 和 50 之间")
	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	@Length(min=0, max=255, message="户籍地址长度必须介于 0 和 255 之间")
	public String getCensusRegisterAddr() {
		return censusRegisterAddr;
	}

	public void setCensusRegisterAddr(String censusRegisterAddr) {
		this.censusRegisterAddr = censusRegisterAddr;
	}
	
	@Length(min=0, max=255, message="现居地址长度必须介于 0 和 255 之间")
	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
}