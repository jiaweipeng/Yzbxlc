/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtguatee.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 担保机构列表Entity
 * @author wuhao
 * @version 2017-06-22
 */
public class MtGuatee extends DataEntity<MtGuatee> {
	
	private static final long serialVersionUID = 1L;
	private String msname;		// 机构名称
	private String msaddress;		// 机构地址
	private String contacts;		// 联系人
	private String number;		// 联系电话
	private String comprofile;		// 公司概况
	private String history;		// 发展历史
	private Integer state;		// 状态:0有效 1无效
	private Integer type;		// 类型 0机构 1个人
	
	public MtGuatee() {
		super();
	}

	public MtGuatee(String id){
		super(id);
	}

	@Length(min=0, max=100, message="机构名称长度必须介于 0 和 100 之间")
	public String getMsname() {
		return msname;
	}

	public void setMsname(String msname) {
		this.msname = msname;
	}
	
	@Length(min=0, max=100, message="机构地址长度必须介于 0 和 100 之间")
	public String getMsaddress() {
		return msaddress;
	}

	public void setMsaddress(String msaddress) {
		this.msaddress = msaddress;
	}
	
	@Length(min=0, max=100, message="联系人长度必须介于 0 和 100 之间")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Length(min=0, max=100, message="联系电话长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=200, message="公司概况长度必须介于 0 和 200 之间")
	public String getComprofile() {
		return comprofile;
	}

	public void setComprofile(String comprofile) {
		this.comprofile = comprofile;
	}
	
	@Length(min=0, max=255, message="发展历史长度必须介于 0 和 255 之间")
	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}