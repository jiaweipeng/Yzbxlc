/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtlinks.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtlinks.entity.Mtlinks;

/**
 * 友情连接DAO接口
 * @author wuhao
 * @version 2017-06-12
 */
@MyBatisDao
public interface MtlinksDao extends CrudDao<Mtlinks> {
	
}