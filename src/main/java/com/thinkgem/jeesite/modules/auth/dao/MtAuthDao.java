/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auth.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.auth.entity.MtAuth;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;

/**
 * 认证管理DAO接口
 * @author shouming
 * @version 2017-06-14
 */
@MyBatisDao
public interface MtAuthDao extends CrudDao<MtAuth> {
	public Page<MtAuth> findListByOut();
}