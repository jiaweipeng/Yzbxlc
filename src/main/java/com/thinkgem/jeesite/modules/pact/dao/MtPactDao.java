/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pact.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pact.entity.MtPact;

/**
 * 合同管理DAO接口
 * @author shouming
 * @version 2017-06-20
 */
@MyBatisDao
public interface MtPactDao extends CrudDao<MtPact> {
	public MtPact findPact(String pid);
}