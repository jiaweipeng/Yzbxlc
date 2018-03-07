/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtlinks.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 友情连接Entity
 * @author wuhao
 * @version 2017-06-12
 */
public class Mtlinks extends DataEntity<Mtlinks> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String img;		// 图片
	private Integer stats;		// 状态
	private Integer type;//类型    0合作机构   1友情链接 
	private Integer sort;//排序
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Mtlinks() {
		super();
	}

	public Mtlinks(String id){
		super(id);
	}

	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getStats() {
		return stats;
	}

	public void setStats(Integer stats) {
		this.stats = stats;
	}
	
}