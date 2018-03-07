/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reflect.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.reflect.entity.MtReflect;

/**
 * 体现成功DAO接口
 * @author wuhao
 * @version 2017-06-14
 */
@MyBatisDao
public interface MtReflectDao extends CrudDao<MtReflect> {
	
}