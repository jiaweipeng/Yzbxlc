/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.notice.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 通知管理Entity
 * 
 * @author jiaweipeng
 * @version 2017-06-13
 */
public class MtNotice extends DataEntity<MtNotice> {

	private static final long serialVersionUID = 1L;
	private String title; // 标题
	private String content; // 内容
	private String sender; // 发件人
	private Integer userid; // 收件人
	private Integer type; // 通知类型
	private Integer isread; // 是否已读
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsread() {
		return isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
	}

}