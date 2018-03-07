/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pnuelist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pnuelist.entity.MtPnuelist;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

/**
 * 平台收益DAO接口
 * @author wuhao
 * @version 2017-06-15
 */
@MyBatisDao
public interface MtPnuelistDao extends CrudDao<MtPnuelist> {
	public List<MtPnuelist> findTotalList(MtPnuelist mtPnuelist);
	public String sumInmount(@Param("time")String time, @Param("type")Integer type);
	public String sumUserInmount(@Param("userid")String userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
} 