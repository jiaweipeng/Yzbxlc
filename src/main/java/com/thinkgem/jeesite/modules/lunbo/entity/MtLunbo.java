/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lunbo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 轮播图Entity
 * @author wuhao
 * @version 2017-06-12
 */
public class MtLunbo extends DataEntity<MtLunbo> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title
	private String img;		// img
	private String status;		// status
	private String url;		// 浏览地址
	private Integer imgIdx;		// 排序
	
	public MtLunbo() {
		super();
	}

	public MtLunbo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="title长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="img长度必须介于 0 和 255 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=11, message="status长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=50, message="浏览地址长度必须介于 0 和 50 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getImgIdx() {
		return imgIdx;
	}

	public void setImgIdx(Integer imgIdx) {
		this.imgIdx = imgIdx;
	}
	
	
	
}