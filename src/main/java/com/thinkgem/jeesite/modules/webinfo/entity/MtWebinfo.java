/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网站信息Entity
 * @author wuhao
 * @version 2017-06-29
 */
public class MtWebinfo extends DataEntity<MtWebinfo> {
	
	private static final long serialVersionUID = 1L;
	private String pname;		// 参数
	private String pvalues;		// 参数值
	private String logo;		// logo图片
	private String varname;		// 变量明
	private String type;		// 类型
	
	public MtWebinfo() {
		super();
	}

	public MtWebinfo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="参数长度必须介于 0 和 50 之间")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	

	public String getPvalues() {
		return pvalues;
	}

	public void setPvalues(String pvalues) {
		this.pvalues = pvalues;
	}

	@Length(min=0, max=200, message="logo图片长度必须介于 0 和 200 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=50, message="变量明长度必须介于 0 和 50 之间")
	public String getVarname() {
		return varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}
	
	@Length(min=0, max=10, message="类型长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}