/**
       * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pnuelist.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台收益Entity
 * 
 * @author wuhao
 * @version 2017-06-15
 */
public class MtPnuelist extends DataEntity<MtPnuelist> {
	

	private static final long serialVersionUID = 1L;
	private BigDecimal inmount; // 平台收益金额
	private Integer mtUserId; // 用户id
	private String pid; // 产品id
	private Date creationtime;// 平台收益时间
	private Integer type;// 平台收益类型
	private Integer nper;// 还款期数
	// 查询条件
	private String findtime = null; // 平台收益时间
	private String title = null; // 产品名称查询	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFindtime() {
		if(findtime == null) {
			if(!(createDate==null)) {
				findtime = createDate.toString();
			}
		}
		return findtime;
	}

	public void setFindtime(String findtime) {
		this.findtime = findtime;
	}

	public Integer getNper() {
		return nper;
	}

	public void setNper(Integer nper) {
		this.nper = nper;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public MtPnuelist() {
		super();
	}

	public MtPnuelist(String id) {
		super(id);
	}

	public Integer getMtUserId() {
		return mtUserId;
	}

	public void setMtUserId(Integer mtUserId) {
		this.mtUserId = mtUserId;
	}

	@Length(min = 0, max = 255, message = "产品id长度必须介于 0 和 255 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public BigDecimal getInmount() {
		return inmount;
	}

	public void setInmount(BigDecimal inmount) {
		this.inmount = inmount;
	}

}