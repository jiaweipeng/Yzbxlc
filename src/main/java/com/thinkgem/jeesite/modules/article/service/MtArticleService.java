/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.article.entity.MtArticle;
import com.thinkgem.jeesite.modules.article.dao.MtArticleDao;

/**
 * 文章管理Service
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Service
@Transactional(readOnly = true)
public class MtArticleService extends CrudService<MtArticleDao, MtArticle> {

	public MtArticle get(String id) {
		return super.get(id);
	}
	
	public List<MtArticle> findList(MtArticle mtArticle) {
		return super.findList(mtArticle);
	}
	
	public Page<MtArticle> findPage(Page<MtArticle> page, MtArticle mtArticle) {
		return super.findPage(page, mtArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(MtArticle mtArticle) {
		super.save(mtArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtArticle mtArticle) {
		super.delete(mtArticle);
	}
	
}