/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtinvest.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;

/**
 * 投资记录DAO接口
 * @author wuhao
 * @version 2017-06-15
 */
@MyBatisDao
public interface MtInvestDao extends CrudDao<MtInvest> {
	// public MtInvest getPlfment();
	public List<MtInvest> findIdList(String id);
	public Integer updateInvment(String invment, String pid);
	public String  getcountname(Integer productid);//投资总次数
	public MtInvest findInvest(String freezeId);
}