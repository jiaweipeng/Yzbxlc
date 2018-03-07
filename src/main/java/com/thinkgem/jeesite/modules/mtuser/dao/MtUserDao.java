/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtuser.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;

/**
 * 客户管理DAO接口
 * @author fengtao
 * @version 2017-06-14
 */
@MyBatisDao
public interface MtUserDao extends CrudDao<MtUser> {
	public MtUser findUser(String ipsAcctNo);
}