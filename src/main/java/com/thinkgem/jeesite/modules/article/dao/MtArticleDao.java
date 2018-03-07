/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.article.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.article.entity.MtArticle;

/**
 * 文章管理DAO接口
 * @author jiaweipeng
 * @version 2017-06-13
 */
@MyBatisDao
public interface MtArticleDao extends CrudDao<MtArticle> {
	
}