/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtusermony.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtusermony.entity.MtUsermony;

/**
 * 客户资金列表DAO接口
 * @author wuhao
 * @version 2017-08-19
 */
@MyBatisDao
public interface MtUsermonyDao extends CrudDao<MtUsermony> {
	
}