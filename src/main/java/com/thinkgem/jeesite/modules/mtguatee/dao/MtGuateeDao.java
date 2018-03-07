/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtguatee.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtguatee.entity.MtGuatee;

/**
 * 担保机构列表DAO接口
 * @author wuhao
 * @version 2017-06-22
 */
@MyBatisDao
public interface MtGuateeDao extends CrudDao<MtGuatee> {
	
}