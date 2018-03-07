package com.thinkgem.jeesite.modules.stat.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class MtStatDetail extends DataEntity<MtStat> {

	
	private static final long serialVersionUID = 1L;
	public String pv;
	public String uv;
	public String hour;
	public String title;
	public String titlepv;
	public String url;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlepv() {
		return titlepv;
	}
	public void setTitlepv(String titlepv) {
		this.titlepv = titlepv;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	
}
