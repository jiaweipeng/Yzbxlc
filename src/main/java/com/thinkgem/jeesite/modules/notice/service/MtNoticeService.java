/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.article.entity.MtArticle;
import com.thinkgem.jeesite.modules.notice.dao.MtNoticeDao;
import com.thinkgem.jeesite.modules.notice.entity.MtNotice;

/**
 * 通知管理Service
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Service
@Transactional(readOnly = true)
public class MtNoticeService extends CrudService<MtNoticeDao, MtNotice> {

	public MtNotice get(String id) {
		return super.get(id);
	}
	
	public List<MtNotice> findList(MtNotice mtNotice) {
		return super.findList(mtNotice);
	}
	
	public Page<MtNotice> findPage(Page<MtNotice> page, MtNotice mtNotice) {
		return super.findPage(page, mtNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(MtNotice mtNotice) {
		super.save(mtNotice);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtNotice mtNotice) {
		super.delete(mtNotice);
	}
	
}