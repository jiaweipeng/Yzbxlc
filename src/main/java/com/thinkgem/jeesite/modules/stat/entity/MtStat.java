/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * statEntity
 * @author 张琨鹏
 * @version 2017-09-23
 */
public class MtStat extends DataEntity<MtStat> {
	
	private static final long serialVersionUID = 1L;
	private String cookieid;		// cookieid
	private String ua;		// ua
	private String remoteip;		// remoteip
	private String refurl;		// refurl
	private String url;		// url
	private String screenx;		// screenx
	private String screeny;		// screeny
	private String os;		// os
	private String brower;		// brower
	private String title;		// title
	private Date createdate;		// createdate
	
	public MtStat() {
		super();
	}

	public MtStat(String id){
		super(id);
	}

	@Length(min=0, max=255, message="cookieid长度必须介于 0 和 255 之间")
	public String getCookieid() {
		return cookieid;
	}

	public void setCookieid(String cookieid) {
		this.cookieid = cookieid;
	}
	
	@Length(min=0, max=255, message="ua长度必须介于 0 和 255 之间")
	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}
	
	@Length(min=0, max=255, message="remoteip长度必须介于 0 和 255 之间")
	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}
	
	@Length(min=0, max=255, message="refurl长度必须介于 0 和 255 之间")
	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	
	@Length(min=0, max=255, message="url长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=255, message="screenx长度必须介于 0 和 255 之间")
	public String getScreenx() {
		return screenx;
	}

	public void setScreenx(String screenx) {
		this.screenx = screenx;
	}
	
	@Length(min=0, max=255, message="screeny长度必须介于 0 和 255 之间")
	public String getScreeny() {
		return screeny;
	}

	public void setScreeny(String screeny) {
		this.screeny = screeny;
	}
	
	@Length(min=0, max=255, message="os长度必须介于 0 和 255 之间")
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	
	@Length(min=0, max=255, message="brower长度必须介于 0 和 255 之间")
	public String getBrower() {
		return brower;
	}

	public void setBrower(String brower) {
		this.brower = brower;
	}
	
	@Length(min=0, max=255, message="title长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
}